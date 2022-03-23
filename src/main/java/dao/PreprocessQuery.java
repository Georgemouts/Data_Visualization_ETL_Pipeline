package main.java.dao;

import java.util.List;

public class PreprocessQuery {
	String select, from;
	
	public PreprocessQuery() {
		this.select = "SELECT *";
		this.from = "FROM ";
	}
	
	
	public String createSelect_FromPrefix(List<String> selectStr, List<String> fromStr){
		if (fromStr.isEmpty())
			perrPreprocess("fromStr");
		
		if (!selectStr.isEmpty())
			this.select = "SELECT ";
		
		
		// for this project selectStr is always single-indexed "value".
		constructStatement("SELECT", selectStr);
		// for this project fromStr is always single-indexed "indicates".
		constructStatement("FROM", fromStr);
	
		
		return this.select + this.from;
	}
	
	public void constructStatement(String typeOfStatement, List<String> flags) {
		if (typeOfStatement.equals("SELECT")) {
			for (String fl: flags)
				this.select += fl + " ";
			//System.out.println(this.select);
		}
		else
			if (typeOfStatement.equals("FROM")) {
				for (String fl: flags)
					this.from += fl + " ";
				//System.out.println(this.from);
			}
			else 
				if (typeOfStatement.equals("WHERE")) {
					for (String fl: flags)
						continue;
				}
				
		
	}
	
	public void perrPreprocess(String type) {
		System.out.println("Preprocess query error: " + type + " cannot be empty.");
		System.exit(0);
	}
}

