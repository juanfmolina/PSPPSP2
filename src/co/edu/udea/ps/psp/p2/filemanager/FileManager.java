package co.edu.udea.ps.psp.p2.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class FileManager {

	private static final String VALID_EXTENSION = "java";
	private static final char EXTENSION_SEPARATOR = '.';

	public String getAllLinesOfDirectory(String path) throws FileNotFoundException{
		File folder = new File(path);
		File[] files = folder.listFiles();
		String contentOfFile="";
		if (files==null) {
			throw new FileNotFoundException();
		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				if (validateExtension(files[i].getName())) {

					BufferedReader reader = new BufferedReader(new FileReader(files[i]));

					String line = null;
					try {
						while ((line = reader.readLine()) != null) {
							if (line != null && !line.equals("")) {
								contentOfFile+=line;
							}

						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							if (reader != null) {
								reader.close();
							}
						} catch (IOException e) {
						}
					}
				}
			}else if (files[i].isDirectory()) {
				contentOfFile+= getAllLinesOfDirectory(files[i].getAbsolutePath());
			}

		}

		return contentOfFile;
	}

	public boolean validateExtension(String name) {
		int dot = name.lastIndexOf(EXTENSION_SEPARATOR);
		String extensionOfFile = name.substring(dot + 1);
		if (VALID_EXTENSION.equalsIgnoreCase(extensionOfFile)) {
			return true;
		}
		return false;

	}

}
