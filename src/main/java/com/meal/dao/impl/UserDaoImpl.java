package com.meal.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.UserDao;
import com.meal.model.User;

@Repository
public class UserDaoImpl implements UserDao{

	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private Logger log=Logger.getLogger("UserDaoImpl");
	
	
	
	
	public UserDaoImpl() {
	}



	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		System.out.println("hibernate template setting in userdaoimpl");
		this.hibernateTemplate = hibernateTemplate;
	}

	public User findUserByEmail(String email) {
	    List<User> users=(List<User>)hibernateTemplate.find("from User u where u.contact.email=?0",email);
		return users.get(0);
	}
	
	@Override
	@Transactional
	public void save(User user) {
		log.info("user is saving..."+hibernateTemplate.toString());
		hibernateTemplate.save(user);
		log.info("user saved successfully.");

	}

	@Override
	@Transactional
	public User get(long id) {
		User user=hibernateTemplate.get(User.class, id);
		log.info(user.toString());
		return user;
	}

	@Override
	@Transactional
	public List<User> getAll() {
		
		return (List<User>) hibernateTemplate.find("from User");
	}

	@Override
	@Transactional
	public void update(User user) {
		hibernateTemplate.update(user);

	}

	@Override
	@Transactional
	public void delete(User user) {
		hibernateTemplate.delete(user);

	}
	
	@Override
	@Transactional
	public int getNextID() {
	
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		//session.beginTransaction();
				SQLQuery query=session.createSQLQuery("select auto_increment from information_schema.TABLES where table_schema='mealmanagement' and table_name='user'");
				List<BigInteger> list=query.getResultList();
				System.out.println("size"+list.size()+" val="+list.get(list.size()-1));
				int nextID=list.get(list.size()-1).intValue();
			
				
	 return nextID;
	}

}
