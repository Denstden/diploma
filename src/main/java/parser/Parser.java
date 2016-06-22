package parser;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import parser.config.question.FormatElements;
import parser.config.question.QuestionConfig;
import parser.config.question.QuestionConfigData;
import parser.config.question.QuestionData;
import parser.config.question.question_types.AbstractQuestionConfigData;
import parser.config.question.question_types.QuestionCheckboxConfigData;
import parser.config.question.question_types.QuestionEssayConfigData;
import parser.config.question.question_types.QuestionRadioButtonConfigData;
import parser.config.question.question_types.QuestionYesNoConfigData;
import parser.config.test.TestConfig;
import parser.config.test.TestConfigData;
import parser.config.variant.Estimation;
import parser.config.variant.VariantConfig;

public class Parser {

	public TestConfigData parse(String fileName) throws IOException, SAXException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);
		DocumentBuilder dombuilder = factory.newDocumentBuilder();
		org.w3c.dom.Document w3cDocument = dombuilder.parse(fileName);
		DOMBuilder jdomBuilder = new DOMBuilder();
		Document jdomDocument = jdomBuilder.build(w3cDocument);
		Element root = jdomDocument.getRootElement();

		Element testConfigElement = root.getChild("test");
		TestConfig testConfig = new TestConfig();
		Element resultSourceElement = testConfigElement.getChild("result_source");
		testConfig.setResultSource(resultSourceElement.getChildText("path"));
		testConfig.setCountOfVariants(Integer.valueOf(testConfigElement.getChildText("count_variants")));
		List<String> testHeader = new ArrayList<>();
		Element testHeaderElement = testConfigElement.getChild("test_header");
		for (Element line : testHeaderElement.getChildren()) {
			testHeader.add(line.getText());
		}
		testConfig.setHeader(testHeader);

		Element variantConfigElement = root.getChild("variant");
		VariantConfig variantConfig = new VariantConfig();
		List<String> variantHeader = new ArrayList<>();
		Element variantHeaderElement = variantConfigElement.getChild("variant_header");
		for (Element line : variantHeaderElement.getChildren()) {
			variantHeader.add(line.getText());
		}
		variantConfig.setHeader(variantHeader);

		List<String> variantFooter = new ArrayList<>();
		Element variantFooterElement = variantConfigElement.getChild("variant_footer");
		for (Element line : variantFooterElement.getChildren()) {
			variantFooter.add(line.getText());
		}
		variantConfig.setFooter(variantFooter);

		variantConfig.setPoints(Double.valueOf(variantConfigElement.getChildText("points")));

		List<QuestionData> questions = new ArrayList<>();
		for (Element element : variantConfigElement.getChild("questions").getChildren()) {
			QuestionData questionData = new QuestionData();
			List<QuestionConfig> questionConfigs = new ArrayList<>();
			for (Element sourceElement : element.getChild("sources").getChildren()) {
				QuestionConfig questionConfig = new QuestionConfig();
				questionConfig.setQuestionConfigData(/*sourceElement.getChildText("question_source")*/
						QuestionDomParser.parse(sourceElement.getChildText("question_source")));
				if (sourceElement.getChild("count_questions") == null) {
					questionConfig.setCountOfQuestions(QuestionConfig.getDefCountOfQuestions());
				} else {
					questionConfig.setCountOfQuestions(Integer.valueOf(sourceElement.getChildText("count_questions")));
				}
				questionConfigs.add(questionConfig);
			}
			questionData.setQuestionConfigs(questionConfigs);
			Estimation estimation = new Estimation();
			Element estimationElement = element.getChild("estimation");
			estimation.setMark(Double.valueOf(estimationElement.getChildText("mark")));
			estimation.setStrategy(
					Estimation.EstimationStrategy.valueOf(estimationElement.getChildText("strategy").toUpperCase()));
			questionData.setEstimation(estimation);
			questions.add(questionData);
		}
		variantConfig.setQuestions(questions);

		TestConfigData testConfigData = new TestConfigData();
		testConfigData.setTestConfig(testConfig);
		testConfigData.setVariantConfig(variantConfig);

		return testConfigData;
	}

	private static class QuestionDomParser {
		private static QuestionConfigData parse(String fileName)
				throws ParserConfigurationException, IOException, SAXException {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setNamespaceAware(true);
			DocumentBuilder dombuilder = factory.newDocumentBuilder();
			org.w3c.dom.Document w3cDocument = dombuilder.parse(fileName);
			DOMBuilder jdomBuilder = new DOMBuilder();
			Document jdomDocument = jdomBuilder.build(w3cDocument);
			Element root = jdomDocument.getRootElement();

			QuestionConfigData questionConfigData = new QuestionConfigData();

			List<AbstractQuestionConfigData> configDatas = new ArrayList<>();

			for (Element element : root.getChildren()) {
				switch (element.getName()) {
				case "global_preambula":
					questionConfigData.setGlobalPreamble(element.getText().trim());
					break;
				case "question_checkbox":
					configDatas.add(parseQuestionCheckboxConfigData(element));
					break;
				case "question_radiobutton":
					configDatas.add(parseQuestionRadioButtonConfigData(element));
					break;
				case "question_esse":
					configDatas.add(parseQuestionEssayConfigData(element));
					break;
				case "question_yes_no":
					configDatas.add(parseQuestionYesNoConfigData(element));
					break;
				}
			}
			questionConfigData.setQuestionConfigDatas(configDatas);

			return questionConfigData;
		}

		private static QuestionRadioButtonConfigData parseQuestionRadioButtonConfigData(Element element)
				throws IOException, SAXException, ParserConfigurationException {
			QuestionRadioButtonConfigData configData = new QuestionRadioButtonConfigData();
			for (Element questionElement : element.getChildren()) {
				switch (questionElement.getName()) {
				case "hashtag":
					configData.setHashTag(questionElement.getText());
					break;
				case "preambula":
					configData.setPreamble(questionElement.getText());
					break;
				case "count_answers":
					configData.
							setCountAnswers(Integer.valueOf(questionElement.getText()));
					break;
				case "source_correct_answers":
					configData.
							setCorrectAnswers(AnswersDomParser.parseAnswers(questionElement.getText()));
					break;
				case "source_incorrect_answers":
					configData.
							setIncorrectAnswers(AnswersDomParser.parseAnswers(questionElement.getText()));
					break;
				case "format_elements": {
					configData.setFormatElements(parseFormatElements(questionElement));
					break;
				}
				}
			}
			return configData;
		}

		private static QuestionYesNoConfigData parseQuestionYesNoConfigData(Element element) {
			QuestionYesNoConfigData configData = new QuestionYesNoConfigData();
			for (Element questionElement : element.getChildren()) {
				switch (questionElement.getName()) {
				case "answer":
					configData.setAnswer(questionElement.getText());
					break;
				case "hashtag":
					configData.setHashTag(questionElement.getText());
					break;
				case "preambula":
					configData.setPreamble(questionElement.getText().trim());
					break;
				case "format_elements": {
					configData.setFormatElements(parseFormatElements(questionElement));
					break;
				}
				}
			}
			return configData;
		}

		private static QuestionEssayConfigData parseQuestionEssayConfigData(Element element) {
			QuestionEssayConfigData configData = new QuestionEssayConfigData();
			for (Element questionElement : element.getChildren()) {
				switch (questionElement.getName()) {
				case "hashtag":
					configData.setHashTag(questionElement.getText());
					break;
				case "preambula":
					configData.setPreamble(questionElement.getText().trim());
					break;
				case "format_elements": {
					configData.setFormatElements(parseFormatElements(questionElement));
					break;
				}
				}
			}
			return configData;
		}

		private static QuestionCheckboxConfigData parseQuestionCheckboxConfigData(Element element)
				throws IOException, SAXException, ParserConfigurationException {
			QuestionCheckboxConfigData configData = new QuestionCheckboxConfigData();
			for (Element questionElement : element.getChildren()) {
				switch (questionElement.getName()) {
				case "hashtag":
					configData.setHashTag(questionElement.getText());
					break;
				case "preambula":
					configData.setPreamble(questionElement.getText());
					break;
				case "count_answers":
					configData.
							setCountAnswers(Integer.valueOf(questionElement.getText()));
					break;
				case "count_correct_answers":
					configData.
							setCountCorrectAnswers(Integer.valueOf(questionElement.getText()));
					break;
				case "count_incorrect_answers":
					configData.
							setCountIncorrectAnswers(Integer.valueOf(questionElement.getText()));
					break;
				case "source_correct_answers":
					configData.
							setCorrectAnswers(AnswersDomParser.parseAnswers(questionElement.getText()));
					break;
				case "source_incorrect_answers":
					configData.
							setIncorrectAnswers(AnswersDomParser.parseAnswers(questionElement.getText()));
					break;
				case "format_elements": {
					configData.setFormatElements(parseFormatElements(questionElement));
					break;
				}
				}
			}
			return configData;
		}

		private static FormatElements parseFormatElements(Element element) {
			FormatElements formatElements = new FormatElements();
			formatElements.setFormatStrategy(
					FormatElements.FormatStrategy.valueOf(element.getChildText("strategy").toUpperCase()));
			if (element.getChild("count") == null) {
				formatElements.setCount(FormatElements.getDefCount());
			} else {
				formatElements.setCount(Integer.valueOf(element.getChildText("count")));
			}
			return formatElements;
		}

		private static class AnswersDomParser {
			private static List<String> parseAnswers(String fileName)
					throws ParserConfigurationException, IOException, SAXException {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

				factory.setNamespaceAware(true);
				DocumentBuilder dombuilder = factory.newDocumentBuilder();
				org.w3c.dom.Document w3cDocument = dombuilder.parse(fileName);
				DOMBuilder jdomBuilder = new DOMBuilder();
				Document jdomDocument = jdomBuilder.build(w3cDocument);
				Element root = jdomDocument.getRootElement();

				List<String> answers = new ArrayList<>();
				for (Element answer : root.getChildren()) {
					if (answer.getName().equals("answer")) {
						String ans = "";
						for (Element line : answer.getChildren()) {
							if (line.getName().equals("line")) {
								ans += line.getText();
							}
						}
						answers.add(ans);
					}
				}
				return answers;
			}
		}
	}
}
