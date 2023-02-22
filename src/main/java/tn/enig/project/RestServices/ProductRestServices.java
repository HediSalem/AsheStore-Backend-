package tn.enig.project.RestServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enig.project.dto.ProductDTO;
import tn.enig.project.services.ProductService;

@RestController
public class ProductRestServices {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<ProductDTO> getAll() {
		return productService.getAllProducts();

	}

	@PostMapping("/products/all")
	public ProductDTO addProducts(@RequestBody List<ProductDTO> newProducts) {
		if(newProducts == null){
			return null;
		}
		for(ProductDTO product : newProducts){
			productService.addProduct(product);
		}
		return null;
	}

	@PostMapping("/products")
	public ProductDTO addProduct(@RequestBody ProductDTO newProduct) {
		return productService.addProduct(newProduct);
	}

	@GetMapping("/products/{id}")
	public ProductDTO getProductById(@PathVariable Long id) {
		return productService.getProduct(id);
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
}
