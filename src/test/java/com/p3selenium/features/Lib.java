package com.p3selenium.features;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Lib {

	private String user;
	private List<String> al1=null;
	public String fetchDataFromCSV() {
		try
		{
		String path = System.getProperty("user.dir");
		path = path + "\\src\\test\\java\\csv\\users.csv";
		System.out.println(path);
		al1 = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(path));
		String userData = br.readLine();
		while (userData != null) {
			String userArray[] = userData.split(",");
			for (String item1 : userArray) {
				al1.add(item1);
			}
			userData = br.readLine();

		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		user=al1.get(0);
		return user;
	}
}
