package ua.kiev.unicyb.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import ua.kiev.unicyb.dto.VariantAnswersDto;
import ua.kiev.unicyb.exception.UnsupportedQuestionTypeException;
import ua.kiev.unicyb.generator.Generator;
import ua.kiev.unicyb.parser.Parser;
import ua.kiev.unicyb.parser.config.test.TestConfigData;
import ua.kiev.unicyb.parser.config.variant.Estimation;
import ua.kiev.unicyb.question.AbstractQuestion;
import ua.kiev.unicyb.test.Test;
import ua.kiev.unicyb.variant.Variant;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

/**
 * Created by storo on 6/1/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/")
public class Controller {
    private Test test;

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public ResponseEntity<Integer> generateTest() throws ParserConfigurationException, SAXException, IOException, UnsupportedQuestionTypeException {
        TestConfigData testConfigData = Parser.parse("data/config.xml");
        Generator generator = new Generator();
        generator.setTestConfigData(testConfigData);
        test = generator.generate();
        return new ResponseEntity<>(test.getVariants().size(), HttpStatus.OK);
    }

    @RequestMapping(value = "/variant", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Variant> getVariant (@RequestParam Integer id) throws ParserConfigurationException, SAXException, IOException, UnsupportedQuestionTypeException {
        Variant variant = test.getVariants().get(id);
        return new ResponseEntity<>(variant, HttpStatus.OK);
    }

    @RequestMapping(value = "/check", method =  RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Double> checkVariantAnswers(@RequestBody VariantAnswersDto answers) {
        Variant variant = findVariantByName(answers.getVariantName());
        Map<String, List<String>> answers1 = answers.getAnswers();
        AbstractQuestion question;
        List<String> answersFromClient;
        String[] correctAnswers;
        Estimation estimation;
        Integer countOfVariantOfAnswers;

        Double result = 0d;

        Double questionMark;
        int key;
        for (int i = 0; i < variant.getQuestionList().size(); i++) {
            key = i + 1;
            if (answers1.containsKey(String.valueOf(key))) {
                question = variant.getQuestionList().get(i);
                answersFromClient = answers1.get(String.valueOf(key));
                correctAnswers = question.getCorrectAnswers().getAnswers();
                estimation = question.getEstimation();
                countOfVariantOfAnswers = question.getVariantsOfAnswers().length;
                questionMark = estimateQuestion(countOfVariantOfAnswers, answersFromClient, correctAnswers, estimation);
                result += questionMark;
            }
        }
//        System.out.println(answers.getAnswers());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private Double estimateQuestion(Integer countOfVariantOfAnswers, List<String> answersFromClient, String[] correctAnswers, Estimation estimation) {
        if (estimation.getStrategy().equals(Estimation.EstimationStrategy.ALL_NOTHING)) {
            return checkAllNothing(answersFromClient, correctAnswers, estimation.getMark());
        } else {
            return checkEvenly(countOfVariantOfAnswers, answersFromClient, correctAnswers, estimation.getMark());
        }
    }

    private Double checkAllNothing(List<String> answersFromClient, String[] correctAnswers, Double mark) {
        boolean isEquals = new HashSet<>(answersFromClient).equals(new HashSet<>(Arrays.asList(correctAnswers)));
        return isEquals ? mark : 0;
    }

    private Double checkEvenly(Integer countOfVariantOfAnswers, List<String> answersFromClient, String[] correctAnswers, Double mark) {
        double part = mark / countOfVariantOfAnswers;
        Double result = 0d;
        Set<String> correct = new HashSet<>(Arrays.asList(correctAnswers));
        for (String answer : answersFromClient) {
            if (correct.contains(answer)) {
                result += part;
            } else {
                result -= part;
            }
        }
        return result;
    }

    private Variant findVariantByName(String name) {
        for (Variant variant : test.getVariants()) {
            if (variant.getName().equals(name)) {
                return variant;
            }
        }
        return null;
    }
}
