package com.emapta.app;

import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		CSVParser parser = new CSVParser();
		List<Map<String, Object>> testList = parser.parseCsvFile("data.csv", ";");
		
		for(Map<String, Object> map : testList) {
			System.out.println("MESSAGE ID: " + map.get("Message Id"));

			System.out.println("BODY: " + map.get("Body"));
			
			System.out.println("DELIVERY STATUS: " + map.get("Delivery Status"));
		}
		

	}

}
