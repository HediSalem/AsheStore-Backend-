package tn.enig.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PersonDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("Client_firstName")
	private String firstName;
	
	@JsonProperty("Client_lastName")
	private String lastName;
	
	@JsonProperty("Client_email")
	private String  email;
	
	@JsonProperty("Client_password")
	private String  password;
	
	@JsonProperty("Client_telephone")
	private String  telephone;
	
	@JsonProperty("Client_type")
	private String  type;
	
	@JsonProperty("Client_certificate")
	private String  certif;
	
	@JsonProperty("Client_address")
	private String  address;
	
	@JsonProperty("Cart")
	private CartDTO  cart;
}
