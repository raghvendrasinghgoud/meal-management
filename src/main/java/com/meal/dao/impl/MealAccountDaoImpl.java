package com.meal.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.MealAccountDao;
import com.meal.model.MealAccount;

@Repository
public class MealAccountDaoImpl implements MealAccountDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private Logger log=Logger.getLogger("MealAccountDaoImpl");


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		System.out.println("hibernate template setting in userdaoimpl");
		this.hibernateTemplate = hibernateTemplate;
	}



	public MealAccountDaoImpl() {
		super();
	}



	@Override
	@Transactional
	public void save(MealAccount obj) {
		log.info(obj.getClass().getName()+" is saving..."+hibernateTemplate.toString());
		hibernateTemplate.save(obj);
		log.info(obj.getClass().getName()+" saved successfully.");
		
	}



	@Override
	public MealAccount get(long id) {
		MealAccount ma=hibernateTemplate.get(MealAccount.class, id);
		log.info(ma.toString());
		return ma;
	}



	@Override
	public List<MealAccount> getAll() {
		return (List<MealAccount>) hibernateTemplate.find("from MealAccount");
			
	}



	@Override
	@Transactional
	public void update(MealAccount obj) {
		hibernateTemplate.update(obj);
		
	}



	@Override
	@Transactional
	public void delete(MealAccount obj) {
		hibernateTemplate.delete(obj);
		
	}

}
