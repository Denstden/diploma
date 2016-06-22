import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import util.FolderCleaner;
import generator.Generator;
import parser.Parser;
import parser.config.test.TestConfigData;
import test.Test;

public class Main {
    /**PROGRAM ARGUMENTS
     *<mod> <config_file>
     *
     *<config_file>::=<DataSource>
     *                <ResultSource>
     *                <CountVariants>
     *                <CountLinesOfVariantPreambula>
     *                <VariantPreambula>
     *           if mod==1
     *                <CountQuestionsType1>
     *                <CountQuestionsType2>
     *                <CountQuestionsType3>
     *                <CountQuestionsType4>
     */
    public static void main(String[] args) {
        Parser parser = new Parser();
        try {
            TestConfigData testConfigData = parser.parse("config.xml");
            Generator generator = new Generator();
            generator.setTestConfigData(testConfigData);
            Test test = generator.generate();
            System.out.println(test.toString());
            FolderCleaner.deleteAllFilesFolder("newFolder");
            test.toFile("newFolder");
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
