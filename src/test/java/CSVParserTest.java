import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.emapta.app.CSVParser;

@RunWith(Parameterized.class)
public class CSVParserTest {
	
	@Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[] {
        		getTestMap() 
           });
    }
    
    public static String[] columns = new String[] {"Message Id", "Body", "Delivery Status"};
    
    @Parameter 
    public Map<String, Object> fInput;
   
    public static Map<String, Object> getTestMap(){
    	Map<String, Object> obj = new HashMap<>(); 
    	obj.put(columns[0], 1);
    	obj.put(columns[1], "\"test body\"");
    	obj.put(columns[2], "\"status\"");   	
    	return obj;
    }

	@Test
    public void testCsvLineParser() {
        CSVParser tester = new CSVParser(); 
        String testString = "1;\"test body\";\"status\"";
        
        // assert statements
        assertEquals("Parsed object message id should be equal", fInput.get("Message Id"), tester.parseLine(testString, ";", columns).get("Message Id"));
        assertEquals("Parsed object body should be equal", fInput.get("Body"), tester.parseLine(testString, ";", columns).get("Body"));
        assertEquals("Parsed object delivery status should be equal", fInput.get("Delivery Status"), tester.parseLine(testString, ";", columns).get("Delivery Status"));
    }
	
	@Test
    public void testNegativeScenario() {
		try {
			CSVParser tester = new CSVParser(); 
	        tester.parseCsvFile("randomfilenamewhatever.csv", ";");
		}
		catch (Exception e) {
			fail();
		}
    }
}
