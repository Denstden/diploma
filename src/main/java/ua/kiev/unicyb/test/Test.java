package ua.kiev.unicyb.test;

import java.io.IOException;
import java.util.List;

import ua.kiev.unicyb.exception.CreatingFileException;
import ua.kiev.unicyb.variant.Variant;

public class Test {
	private List<Variant> variants;

	public List<Variant> getVariants() {
		return variants;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}

	public String toString() {
		String res = "";
		for (Variant variant : variants) {
			res += variant.toString();
		}
		return res;
	}

	public void toFile(String pathToFolder) throws IOException, CreatingFileException {
		for (Variant variant : variants) {
			variant.toFile(pathToFolder);
		}
	}

	public void printCorrectAnswersToFile(String pathToFolder) throws IOException, CreatingFileException {
		for (Variant variant : variants){
			variant.printCorrectAnswersToFile(pathToFolder);
		}
	}
}
