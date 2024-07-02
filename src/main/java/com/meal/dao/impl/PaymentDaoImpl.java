package com.meal.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.PaymentDao;
import com.meal.model.Contact;
import com.meal.model.Payment;

@Repository
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Transactional
	@Override
	public void save(Payment payment) {
		hibernateTemplate.save(payment);

	}

	@Override
	public Payment get(long id) {
		return hibernateTemplate.get(Payment.class, id);
	}	

	@Override
	public List<Payment> getAll() {
		List<Payment> payments=(List<Payment>)hibernateTemplate.find("from Payment");
		return payments;
	}

	@Transactional
	@Override
	public void update(Payment payment) {
		hibernateTemplate.update(payment);

	}

	@Transactional
	@Override
	public void delete(Payment payment) {
		hibernateTemplate.delete(payment);

	}

}
