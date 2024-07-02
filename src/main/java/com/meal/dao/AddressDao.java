package com.meal.dao;

import java.util.List;

import com.meal.model.Address;

public interface AddressDao {

	public void save(Address address);
	public Address get(long id);
	public List<Address> getAll();
	public void update(Address address);
	public void delete(Address address);

}
