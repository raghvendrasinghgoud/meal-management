package com.meal.dao;

import java.util.List;

import com.meal.model.Image;

public interface ImageDao {

	public void save(Image image);
	public Image get(long id);
	public List<Image> getAll();
	public void update(Image image);
	public void delete(Image image);

}
