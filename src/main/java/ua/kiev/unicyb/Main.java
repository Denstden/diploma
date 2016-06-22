package ua.kiev.unicyb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import ua.kiev.unicyb.exception.CreatingFileException;
import ua.kiev.unicyb.exception.DeleteFileException;
import ua.kiev.unicyb.exception.EmptyFolderException;
import ua.kiev.unicyb.generator.Generator;
import ua.kiev.unicyb.parser.Parser;
import ua.kiev.unicyb.parser.config.test.TestConfigData;
import ua.kiev.unicyb.test.Test;
import ua.kiev.unicyb.util.FolderCleaner;

public class Main {
	/**
	 * PROGRAM ARGUMENTS
	 * <mod> <path_to_config_xml>
	 * if (mod == 2)
	 * <path_to_result_folder>
	 */
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		Parser parser = new Parser();
		if (args.length < 2) {
			logger.warn("Program should has at least 2 program arguments.");
			return;
		}
		try {
			TestConfigData testConfigData = parser.parse(args[1]);
			Generator generator = new Generator();
			generator.setTestConfigData(testConfigData);
			Test test = generator.generate();
			System.out.println(test.toString());
			if (Integer.valueOf(args[0]) == 1 && args.length >= 3) {
				FolderCleaner.deleteAllFilesFolder(args[2]);
				test.toFile(args[2]);
			}
		} catch (IOException | SAXException | ParserConfigurationException | EmptyFolderException | DeleteFileException | CreatingFileException e) {
			logger.error(e.getStackTrace());
		}
	}
}
