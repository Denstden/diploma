package ua.kiev.unicyb.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import ua.kiev.unicyb.dto.request.ConfigDto;
import ua.kiev.unicyb.dto.request.VariantAnswersDto;
import ua.kiev.unicyb.dto.response.VariantCheckedDto;
import ua.kiev.unicyb.exception.UnsupportedQuestionTypeException;
import ua.kiev.unicyb.service.TestGenerationServiceInMem;
import ua.kiev.unicyb.test.Test;
import ua.kiev.unicyb.variant.Variant;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by storo on 6/1/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/")
public class Controller {
    private TestGenerationServiceInMem testGenerationService = new TestGenerationServiceInMem();

    @PostConstruct
    public void addConfigs() throws IOException, SAXException, ParserConfigurationException {
        testGenerationService.addConfig("data/config.xml");
        testGenerationService.addConfig("data/config2.xml");
    }

    @RequestMapping(value = "/configList", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getConfigs() {
        List<String> configs = testGenerationService.getConfigs();
        return new ResponseEntity<>(configs, HttpStatus.OK);
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public ResponseEntity<Integer> generateTest(@RequestBody ConfigDto configDto) throws ParserConfigurationException, SAXException, IOException, UnsupportedQuestionTypeException {
        Test test = testGenerationService.generateNewTestFromConfig(configDto.getConfigName());
        return new ResponseEntity<>(test.getVariants().size(), HttpStatus.OK);
    }

    @RequestMapping(value = "/variant", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Variant> getVariant() throws ParserConfigurationException, SAXException, IOException, UnsupportedQuestionTypeException {
        Variant variant = testGenerationService.getNextVariant();
        return new ResponseEntity<>(variant, HttpStatus.OK);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<VariantCheckedDto> checkVariantAnswers(@RequestBody VariantAnswersDto answers) {
        VariantCheckedDto checkedDto = testGenerationService.checkAnswers(answers);
        return new ResponseEntity<>(checkedDto, HttpStatus.OK);
    }
}
