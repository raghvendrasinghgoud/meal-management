package com.meal.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.MealDao;
import com.meal.model.Meal;

@Repository
public class MealDaoImpl implements MealDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private Logger log=Logger.getLogger("MealDaoImpl");


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		System.out.println("hibernate template setting in userdaoimpl");
		this.hibernateTemplate = hibernateTemplate;
	}



	public MealDaoImpl() {
		super();
	}



	@Override
	@Transactional
	public void save(Meal obj) {
		log.info(obj.getClass().getName()+" is saving..."+hibernateTemplate.toString());
		hibernateTemplate.save(obj);
		log.info(obj.getClass().getName()+" saved successfully.");
		
	}



	@Override
	public Meal get(long id) {
		Meal meal=hibernateTemplate.get(Meal.class, id);
		log.info(meal.toString());
		return meal;
	}



	@Override
	public List<Meal> getAll() {
		return (List<Meal>) hibernateTemplate.find("from Meal");
	}



	@Override
	@Transactional
	public void update(Meal obj) {
		hibernateTemplate.update(obj);
		
	}



	@Override
	@Transactional
	public void delete(Meal obj) {
		hibernateTemplate.delete(obj);
		
	}

}
