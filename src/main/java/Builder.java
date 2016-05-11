import java.util.Random;

class Builder {

    public ConcreteQuestionType1 getQuestion(ParsedDataType1 parsedData) throws WrongDataQuestEx {
        ConcreteQuestionType1 question = new ConcreteQuestionType1();

        question.setQuestion(parsedData.preambula);
        question.setFormatSettings(parsedData.formatSettings);

        int col_answers = parsedData.col_answers;
        int col_neg_answers = parsedData.col_neg_answers;
        int col_pos_answers = parsedData.col_pos_answers;
        String[] answ_pos = parsedData.answ_pos;
        String[] answ_neg = parsedData.answ_neg;

        if (col_answers!=col_neg_answers+col_pos_answers)
            throw new WrongDataQuestEx();

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
                throw new WrongDataQuestEx();

            shuffleArray(answers);
        }
        else
            throw  new WrongDataQuestEx();
        question.setAnswers(answers);
        return  question;
    }

    public ConcreteQuestionType2 getQuestion(ParsedDataType2 parsedData){

        ConcreteQuestionType2 question = new ConcreteQuestionType2();

        question.setQuestion(parsedData.preambula);
        question.setFormatSettings(parsedData.formatSettings);
        question.setAnswers(parsedData.answers);

        return question;
    }

    public ConcreteQuestionType3 getQuestion(ParsedDataType3 parsedData){

        ConcreteQuestionType3 question = new ConcreteQuestionType3();

        question.setQuestion(parsedData.preambula);
        question.setFormatSettings(parsedData.formatSettings);

        return question;
    }

    public ConcreteQuestionType4 getQuestion(ParsedDataType4 parsedData) throws WrongDataQuestEx {
        ConcreteQuestionType4 question = new ConcreteQuestionType4();

        question.setQuestion(parsedData.preambula);
        question.setFormatSettings(parsedData.formatSettings);

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
            throw  new WrongDataQuestEx();
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
