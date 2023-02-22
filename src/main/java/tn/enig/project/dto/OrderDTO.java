package tn.enig.project.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import tn.enig.project.Entities.Product;

@Data
public class OrderDTO {
	
	private Long id;
	private Long userId;
	private List<ProductDTO> products;
	private Date date;
	private float total;
	

}
