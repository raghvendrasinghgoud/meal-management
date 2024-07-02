package com.meal.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.ConsumedMealDao;
import com.meal.model.Address;
import com.meal.model.ConsumedMeal;

@Repository
public class ConsumedMealDaoImpl implements ConsumedMealDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Transactional
	@Override
	public void save(ConsumedMeal consumedMeal) {
		hibernateTemplate.save(consumedMeal);
		
	}

	@Override
	public ConsumedMeal get(long id) {
		
		return hibernateTemplate.get(ConsumedMeal.class, id);
	}

	@Override
	public List<ConsumedMeal> getAll() {
		List<ConsumedMeal> consumedMeals=(List<ConsumedMeal>)hibernateTemplate.find("from CosumedMeal");
		return consumedMeals;
	}

	@Transactional
	@Override
	public void update(ConsumedMeal consumedMeal) {
		hibernateTemplate.update(consumedMeal);
		
	}

	@Transactional
	@Override
	public void delete(ConsumedMeal consumedMeal) {
		hibernateTemplate.delete(consumedMeal);
		
	}

	
}
