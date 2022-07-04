package main.java.dao;

import java.util.List;

// TODO: return selected country's id, by Name field.
public interface CountryDAO {
	public List<Integer> readCountryIdFromName(List<String> names);
	public List<String> readCountryNameFromId(List<Integer> ids);
	public String readSingleCountryNameFromId(int id);
	
	public void delete(String name);
	public void update(String name);
	public void create(int id, String name, String code);
	
}
