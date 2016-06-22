package question.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import question.format.FormatSettings;
import question.format.FormatType;
import parser.config.question.question_types.QuestionRadioButtonConfigData;
import question.RadioButtonQuestion;

/**
 * @Author Denys Storozhenko.
 */
public class RadioButtonQuestionBuilder extends AbstractQuestionBuilder {
	@Override
	public RadioButtonQuestion build() {
		RadioButtonQuestion radioButtonQuestion = new RadioButtonQuestion();
		radioButtonQuestion.setQuestion(globalPreambula+" "+configData.getPreambula());

		FormatSettings formatSettings = new FormatSettings();
		formatSettings.setType(FormatType.valueOf(configData.getFormatElements().getFormatStrategy().name()));
		formatSettings.setCount(configData.getFormatElements().getCount());
		radioButtonQuestion.setFormatSettings(formatSettings);

		radioButtonQuestion.setAnswers(buildRadioButtonAnswers((QuestionRadioButtonConfigData)configData));
		return radioButtonQuestion;
	}

	private String[] buildRadioButtonAnswers(QuestionRadioButtonConfigData configData) {
		Integer count = configData.getCountAnswers();
		String[] answers = new String[count];

		List<String> answersList = new ArrayList<>();
		List<String> correctAnswers = configData.getCorrectAnswers();
		Collections.shuffle(correctAnswers);
		answersList.add(correctAnswers.get(0));

		List<String> inCorrectAnswers = configData.getIncorrectAnswers();
		Collections.shuffle(inCorrectAnswers);
		for (int i=0;i<count-1;i++){
			answersList.add(inCorrectAnswers.get(i%inCorrectAnswers.size()));
		}

		Collections.shuffle(answersList);
		return answersList.toArray(answers);
	}
}
