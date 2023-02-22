package tn.enig.project.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
	
	private Long id;
	private String item;
	private float total;
	private Long personId;
	private List<Long> productsIds;
	private List<Long> paymentsIds;
	
}
