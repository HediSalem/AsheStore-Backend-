package tn.enig.project.dto.mapper;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.enig.project.Dao.IPaymentHistory;
import tn.enig.project.Dao.IPerson;
import tn.enig.project.Dao.IProduct;
import tn.enig.project.Entities.Cart;
import tn.enig.project.Entities.PaymentHistory;
import tn.enig.project.Entities.Person;
import tn.enig.project.Entities.Product;
import tn.enig.project.dto.CartDTO;

@Component
public class CartMapper {

	@Autowired
	public IProduct productDAO;

	@Autowired
	public IPerson personDAO;

	@Autowired
	public IPaymentHistory paymentHistoryDAO;

	public CartDTO convertCartToDTO(Cart cart) {
		if (cart == null) {
			return null;
		}
		CartDTO cartDTO = new CartDTO();
		cartDTO.setId(cart.getId());
		cartDTO.setItem(cart.getItem());
		if (cart.getPayments() != null) {
			cartDTO.setPaymentsIds(cart.getPayments().stream().map(PaymentHistory::getId).collect(Collectors.toList()));
		}
		if (cart.getPerson() != null) {
			cartDTO.setPersonId(cart.getPerson().getId());
		}
		cartDTO.setTotal(cart.getTotal());
		if (cart.getProducts() != null) {
			cartDTO.setProductsIds(cart.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
		}

		return cartDTO;
	}

	public Cart convertCartDTOToEntity(CartDTO cartDTO) {
		if (cartDTO == null) {
			return null;
		}
		Cart cart = new Cart();
		cart.setId(cartDTO.getId());
		cart.setItem(cartDTO.getItem());
		cart.setTotal(cartDTO.getTotal());
		if (cartDTO.getProductsIds() != null) {
			cart.setProducts(cartDTO.getProductsIds().stream().map(productDAO::findById).map(Optional::get)
					.collect(Collectors.toList()));
		}

		if (cartDTO.getPersonId() != null) {
			Optional<Person> person = personDAO.findById(cartDTO.getPersonId());
			person.ifPresent(personval -> {
				cart.setPerson(personval);
			});
		}
		if (cartDTO.getPaymentsIds() != null) {
			cart.setPayments(cartDTO.getPaymentsIds().stream().map(paymentHistoryDAO::findById).map(Optional::get)
					.collect(Collectors.toList()));
		}

		return cart;

	}
}
