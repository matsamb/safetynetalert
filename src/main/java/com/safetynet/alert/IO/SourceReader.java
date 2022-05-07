package com.safetynet.alert.IO;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface SourceReader {

	String jsonToString();
	
}
