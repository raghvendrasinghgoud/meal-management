package com.meal.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.AddressDao;
import com.meal.model.Address;

@Repository
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
private Logger log=Logger.getLogger("AddressDaoImpl");


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		System.out.println("hibernate template setting in userdaoimpl");
		this.hibernateTemplate = hibernateTemplate;
	}



	public AddressDaoImpl() {
		super();
	}



	@Override
	@Transactional
	public void save(Address obj) {
		log.info(obj.getClass().getName()+" is saving..."+hibernateTemplate.toString());
		hibernateTemplate.save(obj);
		log.info(obj.getClass().getName()+" saved successfully.");
		
	}



	@Override
	public Address get(long id) {
		Address address=hibernateTemplate.get(Address.class, id);
		log.info(address.toString());
		return address;
	}



	@Override
	public List<Address> getAll() {
		return (List<Address>) hibernateTemplate.find("from Address");
	}



	@Override
	@Transactional
	public void update(Address obj) {
		hibernateTemplate.update(obj);
		
	}



	@Override
	@Transactional
	public void delete(Address obj) {
		hibernateTemplate.delete(obj);
		
	}
	
	

}
