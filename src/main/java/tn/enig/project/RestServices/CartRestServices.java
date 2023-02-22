package tn.enig.project.RestServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enig.project.Dao.ICart;
import tn.enig.project.Entities.Cart;
import tn.enig.project.dto.CartDTO;
import tn.enig.project.dto.mapper.CartMapper;

@RestController
public class CartRestServices {

	@Autowired
	private ICart cartDao;

	@Autowired
	private CartMapper cartMapper;

	@GetMapping("/carts")
	public List<CartDTO> getAll() {
		Iterable<Cart> allCarts = cartDao.findAll();
		return StreamSupport.stream(allCarts.spliterator(), false).map(cartMapper::convertCartToDTO)
				.collect(Collectors.toList());

	}

	@PostMapping("/carts")
	public CartDTO addCart(@RequestBody CartDTO cartDTO) {
		if (cartDTO == null) {
			return null;
		}
		Cart cart = cartMapper.convertCartDTOToEntity(cartDTO);
		cart.setId(null);
		cart = cartDao.save(cart);
		return cartMapper.convertCartToDTO(cart);
	}

	@GetMapping("/carts/{id}")
	public CartDTO getById(@PathVariable Long id) {
		Optional<Cart> cart = cartDao.findById(id);
		if (cart.isEmpty()) {
			return null;
		}
		return cartMapper.convertCartToDTO(cart.get());
	}

	@DeleteMapping("/carts/{id}")
	public void deleteCart(@PathVariable Long id) {
		cartDao.deleteById(id);
	}
}
