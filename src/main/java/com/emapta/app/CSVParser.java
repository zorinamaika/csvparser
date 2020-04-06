package com.emapta.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
* CSV Parser is a simple library that parses a CSV file with a list of messages
*
* @author  Z. Abad
* 
* @version 1.0
* 
* @since   2020-03-13 
* 
*/
public class CSVParser {
		
	private static final Logger LOGGER = Logger.getLogger(CSVParser.class.getName());
	
	/**
	 * 
	 * @param fileName
	 * @param delimiter
	 * @return
	 */
	public List<Map<String, Object>> parseCsvFile(String fileName, String delimiter) {
        List<Map<String, Object>> outputArray = new ArrayList<>();
        String[] csvColumns;
        
        try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName)))
        {
            String line = "";
     
            //Read the first line - assign to columns
            String firstLine = fileReader.readLine();
            csvColumns = firstLine.split(delimiter);
            
            //Read the file line by line
            while ((line = fileReader.readLine()) != null) 
            {
                Map<String, Object> rowMap = parseLine(line, delimiter, csvColumns);
                outputArray.add(rowMap);
            }
        } 
        catch (IOException ex) {
        	LOGGER.log(Level.SEVERE, "Exception occured: " + ex.getMessage());
        }
        return outputArray;
	}
	
	/**
	 * 
	 * @param inputLine
	 * @param delimiter
	 * @param csvColumns
	 * @return
	 */
	public Map<String, Object> parseLine(String inputLine, String delimiter, String[] csvColumns) {
		Map<String, Object> outputMap = new HashMap<>();
		
    	//Get all tokens available in line
        String[] tokens = inputLine.split(delimiter);
        
        int i = 0 ;
        for(String token : tokens)
        {   
            if(token.startsWith("\"") && token.endsWith("\"")) {
            	outputMap.put(csvColumns[i], token);
            } else {
            	outputMap.put(csvColumns[i], Integer.valueOf(token));
            }
        	i++;
        }

		return outputMap;
	}
}
