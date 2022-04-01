package main.java.dao;

import java.util.List;

public interface IndicatorDAO {
	public List<Integer> readIndicatorIdFromName(List<String> names);
	public List<String> readIndicatorNameFromId(List<Integer> ids);
	public String readSingleIndicatorNameFromId(int id);
	public void delete(String name);
	public void update(String name);
	public void create(int id, String name, String code);
	

}
