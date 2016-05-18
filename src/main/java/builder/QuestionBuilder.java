package builder;

import exception.WrongDataQuestionException;
import parser.parsed_data.ParsedDataType1;
import parser.parsed_data.ParsedDataType2;
import parser.parsed_data.ParsedDataType3;
import parser.parsed_data.ParsedDataType4;
import question.QuestionType1;
import question.QuestionType2;
import question.QuestionType3;
import question.QuestionType4;

import java.util.Random;

public class QuestionBuilder {

    public QuestionType1 getQuestion(ParsedDataType1 parsedData) throws WrongDataQuestionException {
        QuestionType1 question = new QuestionType1();

        question.setQuestion(parsedData.getPreambula());
        question.setFormatSettings(parsedData.getFormatSettings());

        int col_answers = parsedData.col_answers;
        int col_neg_answers = parsedData.col_neg_answers;
        int col_pos_answers = parsedData.col_pos_answers;
        String[] answ_pos = parsedData.answ_pos;
        String[] answ_neg = parsedData.answ_neg;

        if (col_answers!=col_neg_answers+col_pos_answers)
            throw new WrongDataQuestionException();

        String [] answers=new String[col_answers];
        shuffleArray(answ_pos);
        shuffleArray(answ_neg);
        int i = 0, j = 0;

        while (i < col_pos_answers && j < answ_pos.length) {
            if (answ_pos[j] != null)
                answers[i++] = answ_pos[j++];
            else
                j++;
        }

        if (i == col_pos_answers) {
            j = 0;

            while (i < col_answers && j < answ_neg.length) {
                if (answ_neg[j] != null)
                    answers[i++] = answ_neg[j++];
                else
                    j++;
            }

            if (i != col_answers)
                throw new WrongDataQuestionException();

            shuffleArray(answers);
        }
        else
            throw  new WrongDataQuestionException();
        question.setAnswers(answers);
        return  question;
    }

    public QuestionType2 getQuestion(ParsedDataType2 parsedData){

        QuestionType2 question = new QuestionType2();

        question.setQuestion(parsedData.getPreambula());
        question.setFormatSettings(parsedData.getFormatSettings());
        question.setAnswers(parsedData.answers);

        return question;
    }

    public QuestionType3 getQuestion(ParsedDataType3 parsedData){

        QuestionType3 question = new QuestionType3();

        question.setQuestion(parsedData.getPreambula());
        question.setFormatSettings(parsedData.getFormatSettings());

        return question;
    }

    public QuestionType4 getQuestion(ParsedDataType4 parsedData) throws WrongDataQuestionException {
        QuestionType4 question = new QuestionType4();

        question.setQuestion(parsedData.getPreambula());
        question.setFormatSettings(parsedData.getFormatSettings());

        int col_answers = parsedData.col_answers;
        String answ_pos = parsedData.answ_pos;
        String[] answ_neg = parsedData.answ_neg;

        String [] answers=new String[col_answers];
        shuffleArray(answ_neg);

        int i = 0, j = 0;

        answers[i++] = answ_pos;

        while (i < col_answers && j < answ_neg.length) {
            if (answ_neg[j] != null)
                answers[i++] = answ_neg[j++];
            else
                j++;
        }

        if (i!=col_answers)
            throw  new WrongDataQuestionException();
        shuffleArray(answers);

        question.setAnswers(answers);

        return question;
    }

    private void swap(String[] a, int i, int change) {
        String temp = a[i];
        a[i] = a[change];
        a[change] = temp;
    }

    private void shuffleArray(String[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

}
