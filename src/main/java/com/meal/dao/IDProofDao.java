package com.meal.dao;

import java.util.List;

import com.meal.model.IDProof;

public interface IDProofDao {

	public void save(IDProof idProof);
	public IDProof get(long id);
	public List<IDProof> getAll();
	public void update(IDProof idProof);
	public void delete(IDProof idProof);

}
