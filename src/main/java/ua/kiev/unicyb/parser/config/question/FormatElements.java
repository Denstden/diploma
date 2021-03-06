package ua.kiev.unicyb.parser.config.question;

public class FormatElements {
	private static final Integer DEF_COUNT = 1;

	private FormatStrategy formatStrategy;

	private Integer count;

	public enum FormatStrategy {
		ROWS,
		COLUMNS
	}

	public static Integer getDefCount() {
		return DEF_COUNT;
	}

	public FormatStrategy getFormatStrategy() {
		return formatStrategy;
	}

	public void setFormatStrategy(FormatStrategy formatStrategy) {
		this.formatStrategy = formatStrategy;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "FormatElements{" +
				"count=" + count +
				", formatStrategy=" + formatStrategy +
				'}';
	}
}
