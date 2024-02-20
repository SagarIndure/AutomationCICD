package devberry.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//Reading the JSON data present in the Json file
		String jsonContent = FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);
	
		//Converting the Json data into HashMap
		// use the Jackson DataBind dependency which is present in mvnRepository to convert the json into HashMap
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data =   mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		
		return data;
	}

}
