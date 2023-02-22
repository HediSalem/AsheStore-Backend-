package tn.enig.project.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.enig.project.Dao.ICart;
import tn.enig.project.Entities.Cart;
import tn.enig.project.Entities.Product;
import tn.enig.project.dto.ProductDTO;

@Component
public class ProductMapper {

	@Autowired
	private ICart cartDAO;

	public ProductDTO convertProductToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductName(product.getName());
		productDTO.setId(product.getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setBrand(product.getBrand());
		productDTO.setCategory(product.getCategory());
		productDTO.setColor(product.getColor());
		productDTO.setDiscount(product.getDiscount());
		List<String> details = new ArrayList<>();
		details.add(product.getDescreption());
		productDTO.setProductsDetails(details);
		productDTO.setProductImage(product.getPicture());
		productDTO.setProductSelectedQuantity(1);
		productDTO.setProductStatus(product.getStatus());
		productDTO.setProductStock(product.getStock());
		productDTO.setSelected_size("small");
		productDTO.setSize(product.getSizes());
		if (product.getCarts() != null) {
			productDTO.setCarts(product.getCarts().stream().map(Cart::getId).collect(Collectors.toList()));
		}

		return productDTO;

	}

	public Product convertProductDTOToEntity(ProductDTO productDTO) {
		if (productDTO == null) {
			return null;
		}
		Product product = new Product();
		product.setBrand(productDTO.getBrand());
		product.setCategory(productDTO.getCategory());
		product.setColor(productDTO.getColor());
		product.setDescreption(String.join(",", productDTO.getProductsDetails()));
		product.setDiscount(productDTO.getDiscount());
		product.setId(productDTO.getId());
		product.setName(productDTO.getProductName());
		product.setPicture(productDTO.getProductImage());
		product.setPrice(productDTO.getPrice());
		product.setSizes(new ArrayList<>(productDTO.getSize()));
		product.setStatus(productDTO.getProductStatus());
		product.setStock(productDTO.getProductStock());
		if (productDTO.getCarts() != null) {
			product.setCarts(productDTO.getCarts().stream().map(cartDAO::findById).map(Optional::get)
					.collect(Collectors.toList()));
		}
		return product;

	}

}
