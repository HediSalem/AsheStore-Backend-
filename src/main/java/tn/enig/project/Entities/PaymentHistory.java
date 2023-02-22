package tn.enig.project.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public @Data class  PaymentHistory {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String orderDetails;
	
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	
	public PaymentHistory( Date date) {
		super();
		this.date = date;
	}

	


	public PaymentHistory() {
		super();
		// TODO Auto-generated constructor stub
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentHistory other = (PaymentHistory) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	@Override
	public String toString() {
		return "PaymentHistory [id=" + id + ", date=" + date + "]";
	}
	
	

}
