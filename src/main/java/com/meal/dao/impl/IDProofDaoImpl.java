package com.meal.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.IDProofDao;
import com.meal.model.IDProof;

@Repository
public class IDProofDaoImpl implements IDProofDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
private Logger log=Logger.getLogger("IDProofDaoImpl");


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		System.out.println("hibernate template setting in userdaoimpl");
		this.hibernateTemplate = hibernateTemplate;
	}



	public IDProofDaoImpl() {
		super();
	}



	@Override
	@Transactional
	public void save(IDProof obj) {
		log.info(obj.getClass().getName()+" is saving..."+hibernateTemplate.toString());
		hibernateTemplate.save(obj);
		log.info(obj.getClass().getName()+" saved successfully.");
		
	}



	@Override
	public IDProof get(long id) {
		IDProof idProof=hibernateTemplate.get(IDProof.class, id);
		log.info(idProof.toString());
		return idProof;
	}



	@Override
	public List<IDProof> getAll() {
		return (List<IDProof>) hibernateTemplate.find("from IDProof");
	}



	@Override
	@Transactional
	public void update(IDProof obj) {
		hibernateTemplate.update(obj);
		
	}



	@Override
	@Transactional
	public void delete(IDProof obj) {
		hibernateTemplate.delete(obj);
		
	}
	
	

}
