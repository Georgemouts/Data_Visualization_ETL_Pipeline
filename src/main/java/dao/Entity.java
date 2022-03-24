package main.java.dao;



public abstract class Entity {
	private int id;
	private String name;
	private String code;
	
	abstract public int getId();
	abstract public String getName();
	abstract public String getCode();
	
	abstract public void setId(int id);
	abstract public void setName(String name);
	abstract public void setCode(String code);
}
