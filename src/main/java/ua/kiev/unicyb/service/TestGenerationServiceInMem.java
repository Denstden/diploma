package ua.kiev.unicyb.service;

import org.xml.sax.SAXException;
import ua.kiev.unicyb.dto.request.VariantAnswersDto;
import ua.kiev.unicyb.dto.response.QuestionCheckedDto;
import ua.kiev.unicyb.dto.response.VariantCheckedDto;
import ua.kiev.unicyb.exception.UnsupportedQuestionTypeException;
import ua.kiev.unicyb.generator.TestGenerator;
import ua.kiev.unicyb.parser.Parser;
import ua.kiev.unicyb.parser.config.test.TestConfigData;
import ua.kiev.unicyb.parser.config.variant.Estimation;
import ua.kiev.unicyb.question.AbstractQuestion;
import ua.kiev.unicyb.question.EssayQuestion;
import ua.kiev.unicyb.test.Test;
import ua.kiev.unicyb.variant.Variant;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

/**
 * Created by storo on 6/7/2017.
 */
public class TestGenerationServiceInMem {
    private static final TestGenerator testGenerator = new TestGenerator();
    private static final Parser parser = new Parser();
    private static Test test;
    private static Iterator<Variant> variantIterator;

    public void addConfig(String configPath) throws ParserConfigurationException, SAXException, IOException {
        parser.parse(configPath);
    }

    public Test generateNewTestFromConfig(String configName) throws ParserConfigurationException, SAXException, IOException, UnsupportedQuestionTypeException {
        TestConfigData testConfigData = parser.getByName(configName);
        testGenerator.setTestConfigData(testConfigData);
        test = testGenerator.generate();
        variantIterator = test.getVariants().iterator();
        return test;
    }

    public Variant getNextVariant() throws UnsupportedQuestionTypeException {
        if (variantIterator.hasNext()) {
            return variantIterator.next();
        } else {
            test = testGenerator.generate();
            variantIterator = test.getVariants().iterator();
            return variantIterator.next();
        }
    }

    public VariantCheckedDto checkAnswers(VariantAnswersDto answers) {
        Variant variant = findVariantByName(answers.getVariantName());
        Double variantMark = variant.getMark();

        Map<String, List<String>> answersClient = answers.getAnswers();
        AbstractQuestion question;
        List<String> answersFromClient;
        String[] correctAnswers;
        Estimation estimation;
        Integer countOfVariantOfAnswers;

        Double result = 0d;

        VariantCheckedDto variantCheckedDto = new VariantCheckedDto();
        variantCheckedDto.setMaxMark(variantMark);

        List<QuestionCheckedDto> questionCheckedDtos = new ArrayList<>();

        for (int i = 0; i < variant.getQuestionList().size(); i++) {
            String key = String.valueOf(i + 1);
            if (answersClient.containsKey(key)) {
                question = variant.getQuestionList().get(i);
                answersFromClient = answersClient.get(key);
                correctAnswers = question.getCorrectAnswers().getAnswers();
                System.out.println("Correct for " + i + ": " + Arrays.toString(correctAnswers));
                System.out.println("From client: " + answersFromClient);
                estimation = question.getEstimation();
                countOfVariantOfAnswers = question.getVariantsOfAnswers().length;

                QuestionCheckedDto questionCheckedDto;
                if (!question.getClass().equals(EssayQuestion.class)) {
                    questionCheckedDto = checkQuestion(countOfVariantOfAnswers, answersFromClient, correctAnswers, estimation);
                    result += questionCheckedDto.getMark();
                } else {
                    questionCheckedDto = new QuestionCheckedDto();
                }
                questionCheckedDto.setName(key);
                questionCheckedDto.setMaxMark(estimation.getMark());
                if (Math.abs(questionCheckedDto.getMark()-(questionCheckedDto.getMaxMark())) > 0.0000001d) {
                    questionCheckedDto.setHint(question.getHint());
                }
                questionCheckedDtos.add(questionCheckedDto);
            }
        }
        variantCheckedDto.setMark(result);
        variantCheckedDto.setQuestions(questionCheckedDtos);
        return variantCheckedDto;
    }

    private QuestionCheckedDto checkQuestion(Integer countOfVariantOfAnswers, List<String> answersFromClient, String[] correctAnswers, Estimation estimation) {
        if (estimation.getStrategy().equals(Estimation.EstimationStrategy.ALL_NOTHING)) {
            return checkAllNothing(answersFromClient, correctAnswers, estimation.getMark());
        } else if (estimation.getStrategy().equals(Estimation.EstimationStrategy.EVENLY)){
            return checkEvenly(countOfVariantOfAnswers, answersFromClient, correctAnswers, estimation.getMark());
        } else {
            return checkWithFines(countOfVariantOfAnswers, answersFromClient, correctAnswers, estimation.getMark());
        }
    }

    private QuestionCheckedDto checkAllNothing(List<String> answersFromClient, String[] correctAnswers, Double mark) {
        QuestionCheckedDto questionCheckedDto = new QuestionCheckedDto();

        boolean isEquals = new HashSet<>(answersFromClient).equals(new HashSet<>(Arrays.asList(correctAnswers)));
        questionCheckedDto.setMark(isEquals ? mark : 0d);

        Map<String, Boolean> answers = new LinkedHashMap<>();
        for (String answer: answersFromClient) {
            answers.put(answer, isEquals);
        }
        questionCheckedDto.setAnswers(answers);

        return questionCheckedDto;
    }

    private QuestionCheckedDto checkEvenly(Integer countOfVariantOfAnswers, List<String> answersFromClient, String[] correctAnswers, Double mark) {
        QuestionCheckedDto  questionCheckedDto = new QuestionCheckedDto();

        Double part = mark / countOfVariantOfAnswers;
        Double result = 0d;

        Map<String, Boolean> answers = new LinkedHashMap<>();
        Set<String> correct = new HashSet<>(Arrays.asList(correctAnswers));
        for (String answer : answersFromClient) {
            if (correct.contains(answer)) {
                answers.put(answer, true);
                result += part;
            } else {
                answers.put(answer, false);
                result -= part;
            }
        }
        questionCheckedDto.setAnswers(answers);

        questionCheckedDto.setMark(result);
        return questionCheckedDto;
    }

    private QuestionCheckedDto checkWithFines(Integer countOfVariantOfAnswers, List<String> answersFromClient, String[] correctAnswers, Double mark) {
        QuestionCheckedDto  questionCheckedDto = new QuestionCheckedDto();

        Double part = mark / countOfVariantOfAnswers;
        Double result = 0d;

        Map<String, Boolean> answers = new LinkedHashMap<>();
        Set<String> correct = new HashSet<>(Arrays.asList(correctAnswers));
        for (String answer : answersFromClient) {
            if (correct.contains(answer)) {
                answers.put(answer, true);
                result += part;
            } else {
                answers.put(answer, false);
            }
        }
        questionCheckedDto.setAnswers(answers);

        questionCheckedDto.setMark(result);
        return questionCheckedDto;
    }

    private Variant findVariantByName(String name) {
        for (Variant variant : test.getVariants()) {
            if (variant.getName().equals(name)) {
                return variant;
            }
        }
        return null;
    }

    public List<String> getConfigs() {
        return parser.getConfigs();
    }
}
