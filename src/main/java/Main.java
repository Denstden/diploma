import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import generator.Generator;
import parser.Parser;
import parser.config.test.TestConfigData;
import test.Test;
import util.FolderCleaner;

public class Main {
    /**PROGRAM ARGUMENTS
     * <mod> <path_to_config_xml>
     *     if (mod == 2)
     *          <path_to_result_folder>
     */
    public static void main(String[] args) {
        Parser parser = new Parser();
        if (args.length < 2){
            System.err.println("Program should has at least 2 program arguments.");
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
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
