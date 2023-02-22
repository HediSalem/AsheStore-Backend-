package tn.enig.project.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity

public @Data class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private float price;
	private String descreption;
	private String picture;
	private String category;
	private int discount;
	private String color;
	private ArrayList<String> sizes;
	private String status;
	private String brand;
	private int stock;

	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	private List<Cart> carts;

	public Product(Long id, String name, float price, String descreption, String picture, String category, int discount,
			String color, ArrayList<String> sizes, String status, String brand, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.descreption = descreption;
		this.picture = picture;
		this.category = category;
		this.discount = discount;
		this.color = color;
		this.sizes = sizes;
		this.status = status;
		this.brand = brand;
		this.stock = stock;
	}

	public Product() {
		super();
		this.sizes = new ArrayList<>();
	}

}