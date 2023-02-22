package tn.enig.project.RestServices;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.enig.project.dto.OrderDTO;
import tn.enig.project.dto.ProductDTO;
import tn.enig.project.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderRestServices {

	@Autowired
	public OrderService orderService;

	@GetMapping("{id}")
	public OrderDTO getOder(@PathVariable("id") Long id) {
		return orderService.getOder(id);
	}

	@GetMapping("/user/{id}")
	public List<OrderDTO> getAllUserOrders(@PathVariable("id") Long userId) {
		return orderService.getAllUserOrders(userId);
	}

	@PostMapping
	public void addOrder(@RequestBody OrderDTO orderDTO) {
		orderService.addOrder(orderDTO);
	}

	@PostMapping("/product/user/{id}")
	public void addProductToOrder(@PathVariable("id") Long userId, @RequestBody ProductDTO productDTO) {
		orderService.addProductToOrder(userId, productDTO);
	}

	@GetMapping("/user/allOrder/{id}/{startDate}/{endDate}")
	public List<OrderDTO> getAllUserOrdersByDate(@PathVariable("id") long userId,
			@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate) {
		return orderService.getAllUserOrdersByDate(userId, startDate, endDate);
	}

	@GetMapping("/all")
	public List<OrderDTO> getAllOrders() {
		return orderService.getAllOrders();
	}

	@DeleteMapping("/product/{productId}/user/{id}")
	public void removeProductFromOrder(@PathVariable("id") Long userId, @PathVariable("productId") Long productId) {
		orderService.removeProductFromOrder(userId, productId);
	}

}
