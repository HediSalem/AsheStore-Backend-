package tn.enig.project.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tn.enig.project.Dao.ICart;
import tn.enig.project.Dao.IPaymentHistory;
import tn.enig.project.Dao.IPerson;
import tn.enig.project.Dao.IProduct;
import tn.enig.project.Entities.Cart;
import tn.enig.project.Entities.PaymentHistory;
import tn.enig.project.Entities.Person;
import tn.enig.project.Entities.Product;
import tn.enig.project.dto.OrderDTO;
import tn.enig.project.dto.ProductDTO;
import tn.enig.project.dto.mapper.OrderMapper;
import tn.enig.project.dto.mapper.ProductMapper;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	public IPaymentHistory paymentHistoryDAO;

	@Autowired
	public ICart cartDAO;

	@Autowired
	public IPerson personDAO;

	@Autowired
	public OrderMapper orderMapper;

	@Autowired
	public ProductMapper productMapper;
	
	@Autowired
	public IProduct productDAO;

	@Override
	public OrderDTO getOder(Long id) {
		if (id == null) {
			return null;
		}
		Optional<PaymentHistory> optionalOrder = paymentHistoryDAO.findById(id);
		if (optionalOrder.isPresent()) {
			return orderMapper.convertPaymentHistoryToOrderDTO(optionalOrder.get());
		}
		return null;
	}

	@Override
	public List<OrderDTO> getAllUserOrders(Long userId) {
		Optional<Person> optinalUser = personDAO.findById(userId);
		if (optinalUser.isEmpty()) {
			return new ArrayList<>();
		}
		Cart userCart = optinalUser.get().getCart();

		if (userCart == null) {
			return new ArrayList<>();
		}
		return userCart.getPayments().stream().map(orderMapper::convertPaymentHistoryToOrderDTO).collect(Collectors.toList());

	}

	@Override
	public void addOrder(OrderDTO orderDTO) {
		if (orderDTO == null) {
			return;
		}
		Optional<Person> optinalUser = personDAO.findById(orderDTO.getUserId());
		if (optinalUser.isEmpty()) {
			return;
		}
		Cart cart = optinalUser.get().getCart();
		if (cart == null) {
			cart = new Cart();
			cart.setPerson(optinalUser.get());
			if (orderDTO.getProducts() != null) {
				cart.setProducts(
						orderDTO.getProducts().stream().map(productMapper::convertProductDTOToEntity).collect(Collectors.toList()));
			}

			cartDAO.save(cart);
		}

	}

	@Override
	public List<OrderDTO> getAllUserOrdersByDate(long userId, Date startDate, Date endDate) {
		Optional<Person> optinalUser = personDAO.findById(userId);
		if (optinalUser.isEmpty()) {
			return new ArrayList<>();
		}
		List<PaymentHistory> payments = paymentHistoryDAO.findHistoryByStartAndEndDate(startDate, endDate, userId);
		if (payments == null || payments.isEmpty()) {
			return new ArrayList<>();
		}
		return payments.stream().map(orderMapper::convertPaymentHistoryToOrderDTO).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		List<OrderDTO> allOrders = new ArrayList<>();
		Iterable<Cart> allCarts = cartDAO.findAll();
		allCarts.forEach(cart -> {
			List<PaymentHistory> allCartHistory = cart.getPayments();
			allOrders.addAll(allCartHistory.stream().map(orderMapper::convertPaymentHistoryToOrderDTO).collect(Collectors.toList()));
		});

		return allOrders;
	}

	@Override
	public void addProductToOrder(Long userId, ProductDTO productDTO) {
		if (userId == null || productDTO == null) {
			return;
		}
		Optional<Person> optinalUser = personDAO.findById(userId);
		if (optinalUser.isEmpty()) {
			return;
		}
		List<Product> orderProducts = new ArrayList<>();
		Cart cart = optinalUser.get().getCart();
		if (cart == null) {
			cart = new Cart();
			cart.setPerson(optinalUser.get());
		}
		orderProducts.add(productMapper.convertProductDTOToEntity(productDTO));
		cart.setProducts(orderProducts);
		cartDAO.save(cart);
	}

	@Override
	public void removeProductFromOrder(Long userId, Long productId) {
		if (userId == null || productId == null) {
			return;
		}
		Optional<Person> optinalUser = personDAO.findById(userId);
		if (optinalUser.isEmpty()) {
			return;
		}
		Cart cart = optinalUser.get().getCart();
		if (cart != null) {
			List<Product> orderProducts = cart.getProducts();
			Product foundProduct = null;
			for (Product product : orderProducts) {
				if (product.getId().equals(productId)) {
					foundProduct = product;
					break;
				}
			}
			if (foundProduct != null) {
				orderProducts.remove(foundProduct);
				cart.setProducts(orderProducts);
				cartDAO.save(cart);
			}
		}

	}

	@Override
	public void finishOrder(Long id) {
		OrderDTO orderDTO = getOder(id);
		List<ProductDTO> allProducts =  orderDTO.getProducts();
		// calcul total + %
		Float total = 0f;
		for(ProductDTO productDTO : allProducts) {
			total+= productDTO.getPrice() -(productDTO.getPrice() * productDTO.getDiscount() / 100);
			Product productToUpdate = productMapper.convertProductDTOToEntity(productDTO);
			productToUpdate.setStock(productDTO.getProductStock() - 1);
			productDAO.save(productToUpdate);
		}
		
		orderDTO.setTotal(total);
			
		// add in payment history and remove products from cart
		PaymentHistory paymentHistory = new PaymentHistory();
		paymentHistory.setId(id);
		paymentHistory.setDate(orderDTO.getDate());
		paymentHistory.setOrderDetails(new Gson().toJson(orderDTO));
		paymentHistoryDAO.save(paymentHistory);
		Cart cart= paymentHistory.getCart();
		cart.setProducts(null);
		cartDAO.save(cart);
		
		
	}

}
