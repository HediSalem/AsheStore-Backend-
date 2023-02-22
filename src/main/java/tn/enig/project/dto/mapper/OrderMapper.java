package tn.enig.project.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import tn.enig.project.Entities.PaymentHistory;
import tn.enig.project.dto.OrderDTO;

@Component
public class OrderMapper {

	@Autowired
	public ProductMapper productMapper;

	public OrderDTO convertPaymentHistoryToOrderDTO(PaymentHistory paymentHistory) {
		if (paymentHistory == null) {
			return null;
		}

		return new Gson().fromJson(paymentHistory.getOrderDetails(), OrderDTO.class);

	}

}
