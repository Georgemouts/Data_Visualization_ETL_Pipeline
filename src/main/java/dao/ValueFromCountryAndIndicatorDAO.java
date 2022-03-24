package main.java.dao;

import java.util.List;
import java.util.Map;

public interface ValueFromCountryAndIndicatorDAO {
	public Map<List<Integer>, Double> readValueFromCountryAndIndicator(List<Integer> id_country, List<Integer> id_ind, List<Integer> years);
	public void delete(int id_country, int id_ind, int year);
	public void update(int id_country, int id_ind, int year);
	public void create(int id_country, int id_ind, int year, int value);
}
