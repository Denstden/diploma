package question;

public class EssayQuestion extends AbstractQuestion {

	@Override
	public void print() {
		System.out.print(toString());
	}

	@Override
	public String toString() {
		String s = "";
		s += preamble + "\r\n\t";
		for (int i = 0; i < formatSettings.getCount(); i++)
			s += "\r\n";
		return s;
	}
}
