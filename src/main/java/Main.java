import new_builder.TestBuilder;
import new_parser2.ConfigDomParser;
import new_parser2.config.test.TestConfigData;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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

        ConfigDomParser parser = new ConfigDomParser();
        try {
            TestConfigData testConfigData = parser.parse("config.xml");
            //System.out.println(testConfigData);
            TestBuilder testBuilder = new TestBuilder();
            testBuilder.setTestConfigData(testConfigData);
            testBuilder.build();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
