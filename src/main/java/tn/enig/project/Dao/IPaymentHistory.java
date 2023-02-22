package tn.enig.project.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.enig.project.Entities.PaymentHistory;

@Repository
public interface IPaymentHistory extends CrudRepository<PaymentHistory, Long> {
	
	@Query("select p from PaymentHistory p where p.date <= :startDate and p.date >= :endDate and p.cart.id = :cartId")
	ArrayList<PaymentHistory> findHistoryByStartAndEndDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("cartId") Long cartId);

}
