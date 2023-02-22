/**
 * 
 */
package tn.enig.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enig.project.Dao.IProduct;
import tn.enig.project.Entities.Product;
import tn.enig.project.dto.ProductDTO;
import tn.enig.project.dto.mapper.ProductMapper;

/**
 * @author Asma
 *
 */

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private IProduct productDAO;
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<ProductDTO> getAllProducts() {
		Iterable<Product> allProducts = productDAO.findAll();
		List<Product> productsList = new ArrayList<>();
		allProducts.forEach(productsList::add);
		return productsList.stream().map(productMapper::convertProductToDTO).collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProduct(Long productId) {
		if(productId == null) {
			return null;
		}
		Optional<Product> product = productDAO.findById(productId);
		if(product.isEmpty()) {
			return null;
		}
		return productMapper.convertProductToDTO(product.get());
	}

	@Override
	public ProductDTO addProduct(ProductDTO productDTO) {
		if(productDTO == null) {
			return null;
		}
		Product product = productMapper.convertProductDTOToEntity(productDTO);
		product.setId(null);
		product = productDAO.save(product);
		return productMapper.convertProductToDTO(product);
	}

	@Override
	public ProductDTO editProduct(Long oldProductId, ProductDTO productDTO) {
		if(productDTO == null) {
			return null;
		}
		
		Product newProduct = productMapper.convertProductDTOToEntity(productDTO);
		newProduct.setId(oldProductId);
		
		newProduct = productDAO.save(newProduct);
		return productMapper.convertProductToDTO(newProduct);
	}

	@Override
	public void deleteProduct(Long productId) {
		productDAO.deleteById(productId);
		
	}

}
