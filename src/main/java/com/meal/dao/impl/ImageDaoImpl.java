package com.meal.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.meal.dao.ImageDao;
import com.meal.model.Image;

@Repository
public class ImageDaoImpl implements ImageDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
private Logger log=Logger.getLogger("ImageDaoImpl");


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}



	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		System.out.println("hibernate template setting in userdaoimpl");
		this.hibernateTemplate = hibernateTemplate;
	}



	public ImageDaoImpl() {
		super();
	}



	@Override
	@Transactional
	public void save(Image obj) {
		log.info(obj.getClass().getName()+" is saving..."+hibernateTemplate.toString());
		hibernateTemplate.save(obj);
		log.info(obj.getClass().getName()+" saved successfully.");
		
	}



	@Override
	public Image get(long id) {
		Image image=hibernateTemplate.get(Image.class, id);
		log.info(image.toString());
		return image;
	}



	@Override
	public List<Image> getAll() {
		return (List<Image>) hibernateTemplate.find("from Image");
	}



	@Override
	@Transactional
	public void update(Image obj) {
		hibernateTemplate.update(obj);
		
	}



	@Override
	@Transactional
	public void delete(Image obj) {
		hibernateTemplate.delete(obj);
		
	}

}
