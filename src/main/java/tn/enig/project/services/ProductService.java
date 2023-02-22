package tn.enig.project.services;

import java.util.List;

import tn.enig.project.Entities.Product;
import tn.enig.project.dto.ProductDTO;

public interface ProductService {

	/**
	 * 
	 * @return
	 */
	public List<ProductDTO> getAllProducts();
	
	public ProductDTO getProduct(Long productId);
	
	public ProductDTO addProduct(ProductDTO productDTO);
	
	public ProductDTO editProduct(Long oldProductId, ProductDTO productDTO);
	
	public void deleteProduct(Long productId);
	
	
	
	
}
