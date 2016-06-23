package ua.kiev.unicyb.question;

public class CheckboxQuestion extends AbstractQuestion {

    @Override
    public String toString() {
        String s = "";
        s += preamble + "\r\n\t";
        switch (formatSettings.getType()) {
        case COLUMNS: {
            int i;
            for (i = 1; i < variantsOfAnswers.length + 1; i++) {
                s += "(_) " + variantsOfAnswers[i - 1] + getNSpaces(DEF_MAX_ANSWER_LENGTH - variantsOfAnswers[i - 1].length()) + "\t";
                if (i % formatSettings.getCount() == 0 && i != variantsOfAnswers.length) {
                    s += "\r\n\t";
                }
            }
            break;
        }
        case ROWS: {
            int k = variantsOfAnswers.length / formatSettings.getCount();
            int i;
            for (i = 1; i < variantsOfAnswers.length + 1; i++) {
                s += "(_) " + variantsOfAnswers[i - 1] + getNSpaces(DEF_MAX_ANSWER_LENGTH - variantsOfAnswers[i - 1].length()) + "\t";
                if (i % k == 0 && i != variantsOfAnswers.length) {
                    s += "\r\n\t";
                }
            }
            break;
        }
        }
        s += "\r\n";
        return s;
    }

    private String getNSpaces(int n) {
        String res = "";
        for (int i = 0; i < n; i++) {
            res += " ";
        }
        return res;
    }
}
