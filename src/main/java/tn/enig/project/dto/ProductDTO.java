package tn.enig.project.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDTO {
	
	@JsonProperty("productname")
	private String productName;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("price")
	private float price;
	
	@JsonProperty("discount")
	private int discount;
	
	@JsonProperty("color")
	private String color;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("size")
	private List<String> size;
	
	@JsonProperty("selected_size")
	private String selected_size;
	
	@JsonProperty("product_status")
	private String productStatus;
	
	@JsonProperty("product_stock")
	private int productStock;
	
	@JsonProperty("product_selected_qty")
	private int productSelectedQuantity;
	
	@JsonProperty("product_image")
	private String productImage;
	
	@JsonProperty("brand")
	private String brand;
	
	@JsonProperty("product_details")
	private List<String> productsDetails;
	
	@JsonProperty("related_cats")
	private List<Long> carts;
}
