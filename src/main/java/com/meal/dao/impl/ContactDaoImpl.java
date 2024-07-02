package com.meal.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.ContactDao;
import com.meal.model.Contact;

@Repository
public class ContactDaoImpl implements ContactDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
private Logger log=Logger.getLogger("ContactDaoImpl");


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		System.out.println("hibernate template setting in userdaoimpl");
		this.hibernateTemplate = hibernateTemplate;
	}



	public ContactDaoImpl() {
		super();
	}



	@Override
	@Transactional
	public void save(Contact obj) {
		log.info(obj.getClass().getName()+" is saving..."+hibernateTemplate.toString());
		hibernateTemplate.save(obj);
		log.info(obj.getClass().getName()+" saved successfully.");
		
	}



	@Override
	public Contact get(long id) {
		Contact contact=hibernateTemplate.get(Contact.class, id);
		log.info(contact.toString());
		return contact;
	}



	@Override
	public List<Contact> getAll() {
		return (List<Contact>) hibernateTemplate.find("from Contact");
	}



	@Override
	@Transactional
	public void update(Contact obj) {
		hibernateTemplate.update(obj);
		
	}



	@Override
	@Transactional
	public void delete(Contact obj) {
		hibernateTemplate.delete(obj);
		
	}
	

}
