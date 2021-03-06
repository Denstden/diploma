package ua.kiev.unicyb.question.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.apache.commons.lang3.tuple.Pair;
import ua.kiev.unicyb.question.answers.QuestionAnswers;
import ua.kiev.unicyb.question.format.FormatSettings;
import ua.kiev.unicyb.question.format.FormatType;
import ua.kiev.unicyb.parser.config.question.question_types.QuestionCheckboxConfigData;
import ua.kiev.unicyb.question.CheckboxQuestion;

/**
 * @Author Denys Storozhenko.
 */
public class CheckboxQuestionBuilder extends AbstractQuestionBuilder{

	@Override
	public CheckboxQuestion build() {
		CheckboxQuestion checkboxQuestion = new CheckboxQuestion();
		checkboxQuestion.setQuestion(globalPreamble +" "+configData.getPreamble());

		FormatSettings formatSettings = new FormatSettings();
		formatSettings.setType(FormatType.valueOf(configData.getFormatElements().getFormatStrategy().name()));
		formatSettings.setCount(configData.getFormatElements().getCount());
		checkboxQuestion.setFormatSettings(formatSettings);
		checkboxQuestion.setHint(configData.getHint());

		Pair<String[], String[]> result = buildCheckboxAnswers((QuestionCheckboxConfigData)configData);
		checkboxQuestion.setVariantsOfAnswers(result.getKey());
		QuestionAnswers questionAnswers = new QuestionAnswers();
		questionAnswers.setAnswers(result.getValue());
		checkboxQuestion.setCorrectAnswers(questionAnswers);

		checkboxQuestion.setEstimation(estimation);
		return checkboxQuestion;
	}

	private Pair<String[], String[]> buildCheckboxAnswers(QuestionCheckboxConfigData configData){
		Integer count = configData.getCountAnswers();
		String[] answers = new String[count];

		List<String> answersList = new ArrayList<>();

		List<String> correctAnswers = configData.getCorrectAnswers();
		Collections.shuffle(correctAnswers);
		for (int i=0;i<configData.getCountCorrectAnswers();i++){
			answersList.add(correctAnswers.get(i%correctAnswers.size()));
		}
		String[] correctAnswersResult = new String[answersList.size()];
		answersList.toArray(correctAnswersResult);

		List<String> inCorrectAnswers = configData.getIncorrectAnswers();
		Collections.shuffle(inCorrectAnswers);
		for (int i=0;i<configData.getCountIncorrectAnswers();i++){
			answersList.add(inCorrectAnswers.get(i%inCorrectAnswers.size()));
		}

		Collections.shuffle(answersList);
		return Pair.of(answersList.toArray(answers), correctAnswersResult);
	}
}
