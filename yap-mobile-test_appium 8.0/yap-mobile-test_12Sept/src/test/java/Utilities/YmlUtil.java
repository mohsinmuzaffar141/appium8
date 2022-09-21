package Utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.yaml.snakeyaml.Yaml;

import Utilities.JsonReader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class YmlUtil {

	public String getDataYML(String fileName, String firstlevel, String secondlevel) throws IOException {
		Map<String, Object> dataFile = getDataByYMLFile(fileName);
		Map<String, Object> dataA = (Map<String, Object>) dataFile.get(firstlevel);
		String value = (String) dataA.get(secondlevel);
		return value;
	}
	
	public String getDataYML(String fileName, String firstlevel) throws FileNotFoundException {
		Map<String, Object> dataT  = getDataByYMLFile(fileName);
		Map<String, Object> dataA = (Map<String, Object>) dataT.get(firstlevel);
		return dataA.toString();
	}

	public Map<String, Object> getDataByYMLFile(String fileName) throws FileNotFoundException {
		InputStream inputStream = new FileInputStream(new File(fileName));
		Yaml yaml = new Yaml();
		Map<String, Object> dataT = yaml.load(inputStream);
		return dataT;
	}

	public void updateOldPasscode(String filePath, String oldPW, String newPW) throws IOException {
		ObjectMapper objectMapper = new YAMLMapper();
		Map<String, Object> fileData = objectMapper.readValue(new File(filePath),
				new TypeReference<Map<String, Object>>() { });
		Map<String, Object> updatedPW = (Map<String, Object>) fileData.get(newPW);
		Object updatedPWObj= updatedPW.get("pw");
		Map<String, Object> address = (Map<String, Object>) fileData.get(oldPW);
		address.put("pw", updatedPWObj);
		objectMapper.writeValue(new File(filePath), fileData);
	}


}
