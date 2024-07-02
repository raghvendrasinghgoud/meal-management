package com.meal.dao;

import java.util.List;

import com.meal.model.Contact;

public interface ContactDao {

	public void save(Contact contact);
	public Contact get(long id);
	public List<Contact> getAll();
	public void update(Contact contact);
	public void delete(Contact contact);

}
