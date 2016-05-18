package parser.parsed_data;

import format.FormatSettings;

public abstract class ParsedData{
    private String preambula;
    private FormatSettings formatSettings;
    public abstract void print();

    public String getPreambula() {
        return preambula;
    }

    public void setPreambula(String preambula) {
        this.preambula = preambula;
    }

    public FormatSettings getFormatSettings() {
        return formatSettings;
    }

    public void setFormatSettings(FormatSettings formatSettings) {
        this.formatSettings = formatSettings;
    }
}