package com.meal.dao;

import java.util.List;

import com.meal.model.Contact;
import com.meal.model.Payment;

public interface PaymentDao {

	public void save(Payment payment);
	public Payment get(long id);
	public List<Payment> getAll();
	public void update(Payment payment);
	public void delete(Payment payment);
	
}
