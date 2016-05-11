class WrongDataQuestEx extends Exception{

    @Override
    public String getMessage() {
        return "Wrong data question.";
    }
}


class NoQuestionEx extends Exception{

    @Override
    public  String getMessage(){return "Unable to generate the required number of questions.";}
}


enum format_type {
    ROW,COLUMN,ROWS,COLUMNS
}


class FormatSettings {
    private format_type type;
    private int count;
    public void setType(format_type t){
        this.type=t;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public format_type getType(){
        return type;
    }

    public int getCount(){
        return count;
    }

}

