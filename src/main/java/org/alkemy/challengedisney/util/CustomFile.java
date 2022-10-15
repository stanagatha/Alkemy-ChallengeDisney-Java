package org.alkemy.challengedisney.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class CustomFile {
	private final static String IMAGE_PATH = "/src/main/resources/static/images/";
	
	public static String saveImageToDisk(MultipartFile multipartFile) throws IllegalStateException, IOException {
		
		String imageName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
		String finalPath = System.getProperty("user.dir") + IMAGE_PATH + imageName;				  

		if(!multipartFile.isEmpty()){
			File file = new File(finalPath);
				FileUtils.touch(file);
			    // Transfiero el archivo multipart al disco.
				multipartFile.transferTo(file);
				return "/images/" + imageName;
		}
		return "";
	}
}
