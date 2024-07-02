package com.meal.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.RoleDao;
import com.meal.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private Logger log=Logger.getLogger("RoleDaoImpl");


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		System.out.println("hibernate template setting in userdaoimpl");
		this.hibernateTemplate = hibernateTemplate;
	}



	public RoleDaoImpl() {
		super();
	}



	@Override
	@Transactional
	public void save(Role obj) {
		log.info(obj.getClass().getName()+" is saving..."+hibernateTemplate.toString());
		hibernateTemplate.save(obj);
		log.info(obj.getClass().getName()+" saved successfully.");
		
	}



	@Override
	public Role get(long id) {
		Role role=hibernateTemplate.get(Role.class, id);
		log.info(role.toString());
		return role;
	}



	@Override
	public List<Role> getAll() {
		return (List<Role>) hibernateTemplate.find("from Role");
	}



	@Override
	@Transactional
	public void update(Role obj) {
		hibernateTemplate.update(obj);
		
	}



	@Override
	@Transactional
	public void delete(Role obj) {
		hibernateTemplate.delete(obj);
		
	}

	
}
