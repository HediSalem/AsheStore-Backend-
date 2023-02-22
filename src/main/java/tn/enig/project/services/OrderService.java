package tn.enig.project.services;

import java.util.Date;
import java.util.List;

import tn.enig.project.dto.OrderDTO;
import tn.enig.project.dto.ProductDTO;

public interface OrderService {

	
	public OrderDTO getOder(Long id);
	
	public List<OrderDTO> getAllUserOrders(Long userId);
	
	public void addOrder(OrderDTO orderDTO);
	
	public void addProductToOrder(Long userId,  ProductDTO productDTO);
	
	public List<OrderDTO> getAllUserOrdersByDate(long userId, Date startDate, Date endDate);
	
	public List<OrderDTO> getAllOrders();
	
	public void removeProductFromOrder(Long userId, Long productId);
	
	public void finishOrder(Long id);
	
}
