package com.meal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private double size;
	public Image(long id, String name, double size) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
	}
	public Image(String name, double size) {
		super();
		this.name = name;
		this.size = size;
	}
	public Image() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", size=" + size + "]";
	}
	
	
	
	
}
