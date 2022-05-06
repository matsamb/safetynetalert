package com.safetynet.alert.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.safetynet.alert.factory.SafetynetalertFactory;

@Service
public class FileReaderIO implements SourceReader {

	static final Logger fileReaderLogger = LogManager.getLogger("FileReaderLogger");
	
	@Autowired
	SafetynetalertFactory fileReaderFactory;
	
	@Override
	public String jsonToString() {
		List<String> jsonAsListOfStrings = null;
		Resource resource = fileReaderFactory.loadSafetynetAlertDataWithClassPathResource();
		try (BufferedReader finalReader = new BufferedReader(
				new InputStreamReader(resource.getInputStream(), "UTF-8"))) {
			jsonAsListOfStrings = finalReader.lines().collect(Collectors.toList());
			fileReaderLogger.info("file reader service get file content correctly");
		} catch (IOException e) {
			e.printStackTrace();
			fileReaderLogger.error("file reader service can not fetch file content");
		}

		String fJsonString = String.join("", jsonAsListOfStrings);
		fileReaderLogger.trace("json file content turned into a java string");
		String jsonString = fJsonString.replaceAll("\\[\\]", "\\[\"N_A\"\\]");

		return jsonString;
	}

}
