package com.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meal.dao.PaymentDao;
import com.meal.model.Payment;

@Service
public class PaymentService {

	@Autowired
	PaymentDao paymentDao;
	@Autowired
	MealAccountService mealAccountService;
	
	public void savePayment(Payment payment) {
		
		//update meal account
		mealAccountService.updateCurrentBalance(payment.getUser().getMealAccount(), payment);
		
		paymentDao.save(payment);
	}
	
	public Payment getPayment(long id) {
		
		return paymentDao.get(id);
		
	}
	
	public void updatePayment(Payment payment) {
		paymentDao.update(payment);
	}
	
	public void deletePayment(Payment payment) {
		double amount=0-payment.getAmount();
		payment.setAmount(amount);
		//update meal account
				mealAccountService.updateCurrentBalance(payment.getUser().getMealAccount(), payment);
		
		paymentDao.delete(payment);
	}
}
