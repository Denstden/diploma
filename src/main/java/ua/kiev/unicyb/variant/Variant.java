package ua.kiev.unicyb.variant;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ua.kiev.unicyb.exception.CreatingFileException;
import ua.kiev.unicyb.question.AbstractQuestion;

public class Variant {
	private String name;

	private String preamble;

	private List<AbstractQuestion> questionList;

	public Variant() {
	}

	public Variant(String name, String preamble, List<AbstractQuestion> questionList) {
		this.name = name;
		this.preamble = preamble;
		this.questionList = questionList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreamble() {
		return preamble;
	}

	public void setPreamble(String preamble) {
		this.preamble = preamble;
	}

	public List<AbstractQuestion> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<AbstractQuestion> questionList) {
		this.questionList = questionList;
	}

	public void print() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		String res = "";
		res += "Варіант " + name + "\r\n" + preamble;
		int i = 1;
		for (AbstractQuestion q : questionList) {
			res += i + ". ";
			i++;
			res += q.toString();
		}
		return res;
	}

	public void toFile(String pathToFolder) throws IOException, CreatingFileException {
		File file = new File(pathToFolder + "\\" + "Variant_" + name + ".txt");
		if (!file.exists() && !file.createNewFile()) {
			throw new CreatingFileException();
		}
		PrintWriter out = new PrintWriter(file.getAbsoluteFile());
		out.print(toString());
		out.close();
	}
}
