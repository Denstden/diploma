package ua.kiev.unicyb.question;

public class RadioButtonQuestion extends AbstractQuestion {

	@Override
	public String toString() {
		String s = "";
		s += preamble + "\r\n\t";
		switch (formatSettings.getType()) {
		case COLUMNS: {
			int i;
			for (i = 1; i < variantsOfAnswers.length + 1; i++) {
				s += "(_) " + variantsOfAnswers[i - 1] + "\t";
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
				s += "(_) " + variantsOfAnswers[i - 1] + "\t";
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
}
