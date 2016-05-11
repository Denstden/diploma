import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

abstract class ParsedData{
    public String preambula;
    public FormatSettings formatSettings;
    public abstract void print();
}


class ParsedDataType1 extends ParsedData{
    public int col_answers;
    public int col_pos_answers;
    public int col_neg_answers;
    public String[] answ_pos;
    public String[] answ_neg;

    public ParsedDataType1(){
        formatSettings= new FormatSettings();
    }

    @Override
    public void print() {
        System.out.println("PRINT PARSED DATA TYPE 1");
        System.out.println(preambula+"\n"+col_answers+"\n"+col_pos_answers+"\n"+col_neg_answers);
        for (String s:answ_pos){
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s:answ_neg){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}


class ParsedDataType2 extends ParsedData{
    public String correctAnswer;
    public String[] answers;

    public ParsedDataType2(){formatSettings = new FormatSettings();}

    @Override
    public void print(){
        System.out.println("PRINT PARSED DATA TYPE 2");
        System.out.println(preambula);
        System.out.println();
    }
}


class ParsedDataType3 extends ParsedData{

    public ParsedDataType3(){formatSettings = new FormatSettings();}

    @Override
    public void print() {
        System.out.println("PRINT PARSED DATA TYPE 3");
        System.out.println(preambula);
        System.out.println();
    }
}


class ParsedDataType4 extends ParsedData{
    public int col_answers;
    public String answ_pos;
    public String[] answ_neg;

    public ParsedDataType4(){formatSettings = new FormatSettings();}

    @Override
    public void print() {
        System.out.println("PRINT PARSED DATA TYPE 4");
        System.out.println(preambula+"\n"+col_answers);
        System.out.println(answ_pos);
        for (String s:answ_neg){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}


class AnswersReaderType1{
    private String fname;
    private String[] answers;

    public AnswersReaderType1(String filename)throws FileNotFoundException {
        fname=filename;
        read_answers();
    }

    private void read_answers() throws FileNotFoundException{
        Scanner in = new Scanner(new File(fname));
        ArrayList<String> list = new ArrayList<>();
        while (in.hasNext()){
            list.add(in.nextLine());
        }
        answers = new String[list.size()];
        int i=0;
        for (String s:list)
            answers[i++]=s;
        in.close();
    }

    public String[] getAnswers(){
        return answers;
    }
}


class ParsedDatas{
    public LinkedList<ParsedData> parsedDataLinkedList;

    public ParsedDatas(){
        parsedDataLinkedList = new LinkedList<>();
    }

    public void  add(ParsedData parsedData){
        parsedDataLinkedList.add(parsedData);
    }
}


class Parser {
    private String source;
    private LinkedList<ParsedDatas> parsedDataListList;
    private Iterator<ParsedDatas> listIterator;

    public Parser(String source) {
        this.source = source;
        parsedDataListList = new LinkedList<>();
    }

    public void parse() throws FileNotFoundException, WrongDataQuestEx {
        Scanner in;
        String folderList[] = new File(source).list();
        for (String folder:folderList) {
            String list[] = new File(source+"\\"+folder).list();
            for (String fileName:list){
                if (fileName.equals("q.txt")){
                    in = new Scanner(new File(source+"\\"+folder+"\\"+fileName));
                    if (in.hasNext()) {
                        String typeQuestion = in.nextLine();
                        //comment to question
                        while(typeQuestion.contains("//"))
                            typeQuestion = in.nextLine();
                        if (typeQuestion.equals("type1")) {
                            parseQuestionType1(in,folder);
                        }
                        else if (typeQuestion.equals("type2")){
                            parseQuestionType2(in, folder);
                        }
                        else if (typeQuestion.equals("type3")){
                            parseQuestionType3(in, folder);
                        }
                        else if (typeQuestion.equals("type4")){
                            parseQuestionType4(in, folder);
                        }
                    }
                }
            }
        }
        listIterator = parsedDataListList.iterator();
    }

    private void parseQuestionType1(Scanner scanner, String folder) throws WrongDataQuestEx, FileNotFoundException {
        String f_pos, f_neg;
        ParsedDataType1 parsedDataType1 = new ParsedDataType1();
        try {
            parsedDataType1.preambula = scanner.nextLine();
            parsedDataType1.col_answers = scanner.nextInt();
            parsedDataType1.col_pos_answers = scanner.nextInt();
            parsedDataType1.col_neg_answers = scanner.nextInt();
            f_pos = source+"\\"+folder+"\\" + scanner.next();
            f_neg = source+"\\"+folder+"\\" + scanner.next();
            String type = scanner.next();
            if (type.equals("column")) {
                parsedDataType1.formatSettings.setType(format_type.COLUMN);
                parsedDataType1.formatSettings.setCount(0);
            } else if (type.equals("columns")) {
                parsedDataType1.formatSettings.setType(format_type.COLUMNS);
                parsedDataType1.formatSettings.setCount(scanner.nextInt());
            } else if (type.equals("row")) {
                parsedDataType1.formatSettings.setType(format_type.ROW);
                parsedDataType1.formatSettings.setCount(0);
            } else if (type.equals("rows")) {
                parsedDataType1.formatSettings.setType(format_type.ROWS);
                parsedDataType1.formatSettings.setCount(scanner.nextInt());
            }

        } catch (Exception e) {
            throw new WrongDataQuestEx();
        }
        AnswersReaderType1 answersReader = new AnswersReaderType1(f_pos);
        parsedDataType1.answ_pos = answersReader.getAnswers();
        AnswersReaderType1 answersReader1 = new AnswersReaderType1(f_neg);
        parsedDataType1.answ_neg = answersReader1.getAnswers();

        ParsedDatas parsedDatas = new ParsedDatas();
        parsedDatas.add(parsedDataType1);
        parsedDataListList.add(parsedDatas);

        scanner.close();
    }
    private void parseQuestionType2(Scanner scanner, String folder) throws FileNotFoundException, WrongDataQuestEx {
        String globalPreambula = "";
        String pathToInfo = scanner.nextLine();
        if (scanner.hasNext()) {
            globalPreambula = pathToInfo;
            pathToInfo = scanner.nextLine();
        }
        ParsedDataType2 parsedDataType2;
        ParsedDatas parsedDatas = new ParsedDatas();
        Scanner in = new Scanner(new File(source+"\\"+folder+"\\"+pathToInfo));
        while (in.hasNext()){
            parsedDataType2 = new ParsedDataType2();
            try {
                parsedDataType2.preambula = globalPreambula+' '+in.nextLine();
                parsedDataType2.correctAnswer = in.nextLine();
                String type = in.nextLine();
                if (type.equals("column")) {
                    parsedDataType2.formatSettings.setType(format_type.COLUMN);
                    parsedDataType2.formatSettings.setCount(0);
                } else if (type.equals("row")) {
                    parsedDataType2.formatSettings.setType(format_type.ROW);
                    parsedDataType2.formatSettings.setCount(0);
                }

            } catch (Exception e) {
                throw new WrongDataQuestEx();
            }
            parsedDataType2.answers = new String[2];
            parsedDataType2.answers[0] = "Yes";
            parsedDataType2.answers[1] = "No";
            parsedDatas.add(parsedDataType2);
        }
        Collections.shuffle(parsedDatas.parsedDataLinkedList);
        parsedDataListList.add(parsedDatas);
        scanner.close();
        in.close();
    }
    private void parseQuestionType3(Scanner scanner, String folder) throws FileNotFoundException, WrongDataQuestEx {
        String globalPreambula = "";
        String pathToInfo = scanner.nextLine();
        if (scanner.hasNext()) {
            globalPreambula = pathToInfo;
            pathToInfo = scanner.nextLine();
        }
        ParsedDatas parsedDatas = new ParsedDatas();
        ParsedDataType3 parsedDataType3;
        Scanner in = new Scanner(new File(source+"\\"+folder+"\\"+pathToInfo));
        while (in.hasNext()){
            //parsedDatas = new ParsedDatas();
            parsedDataType3 = new ParsedDataType3();
            try {
                parsedDataType3.preambula = globalPreambula+' '+in.nextLine();
                parsedDataType3.formatSettings.setCount(Integer.valueOf(in.nextLine()));
            } catch (Exception e) {
                throw new WrongDataQuestEx();
            }
            parsedDatas.add(parsedDataType3);
        }
        Collections.shuffle(parsedDatas.parsedDataLinkedList);
        parsedDataListList.add(parsedDatas);
        scanner.close();
        in.close();
    }
    private void parseQuestionType4(Scanner scanner, String folder) throws FileNotFoundException, WrongDataQuestEx {
        String f_pos, f_neg;
        ParsedDataType4 parsedDataType4= new ParsedDataType4();
        try {
            parsedDataType4.preambula = scanner.nextLine();
            parsedDataType4.col_answers = scanner.nextInt();
            f_pos = source+"\\"+folder+"\\" + scanner.next();
            f_neg = source+"\\"+folder+"\\" + scanner.next();
            String type = scanner.next();
            if (type.equals("column")) {
                parsedDataType4.formatSettings.setType(format_type.COLUMN);
                parsedDataType4.formatSettings.setCount(0);
            } else if (type.equals("columns")) {
                parsedDataType4.formatSettings.setType(format_type.COLUMNS);
                parsedDataType4.formatSettings.setCount(scanner.nextInt());
            } else if (type.equals("row")) {
                parsedDataType4.formatSettings.setType(format_type.ROW);
                parsedDataType4.formatSettings.setCount(0);
            } else if (type.equals("rows")) {
                parsedDataType4.formatSettings.setType(format_type.ROWS);
                parsedDataType4.formatSettings.setCount(scanner.nextInt());
            }

        } catch (Exception e) {
            throw new WrongDataQuestEx();
        }

        Scanner scannerf = new Scanner(new File(f_pos));
        parsedDataType4.answ_pos = scannerf.nextLine();
        AnswersReaderType1 answersReader1 = new AnswersReaderType1(f_neg);
        parsedDataType4.answ_neg = answersReader1.getAnswers();

        ParsedDatas parsedDatas = new ParsedDatas();
        parsedDatas.add(parsedDataType4);
        parsedDataListList.add(parsedDatas);

        scanner.close();
        scannerf.close();
    }

    public void setIter(){
        listIterator=parsedDataListList.iterator();
    }

    public void shuffle(){
        Collections.shuffle(parsedDataListList);
    }

    public ParsedData getNextParsedData(){
        if (listIterator.hasNext()){
            Random random = new Random();
            ParsedDatas parsedDatas = listIterator.next();
            return parsedDatas.parsedDataLinkedList.get(random.nextInt(parsedDatas.parsedDataLinkedList.size()));
        }
        return null;
    }
    public ParsedDataType1 getNextParsedDataType1(){
        ParsedDatas parsedDatas;
        ParsedData data;
        while (listIterator.hasNext()){
            parsedDatas = listIterator.next();
            if (parsedDatas!=null ){
                data = parsedDatas.parsedDataLinkedList.get(0);
                if (data!=null){
                    if (data.getClass().equals(new ParsedDataType1().getClass())){
                        return (ParsedDataType1)data;
                    }
                }
            }
        }
        return null;
    }
    public ParsedDataType2 getNextParsedDataType2() {
        ParsedDatas parsedDatas;
        ParsedData data;
        while (listIterator.hasNext()){
            parsedDatas = listIterator.next();
            if (parsedDatas!=null ){
                data = parsedDatas.parsedDataLinkedList.get(0);
                if (data!=null){
                    if (data.getClass().equals(new ParsedDataType2().getClass())){
                        Random random = new Random();
                        return (ParsedDataType2)parsedDatas.parsedDataLinkedList.get(random.nextInt(parsedDatas.parsedDataLinkedList.size()));
                    }
                }
            }
        }
        return null;
    }
    public ParsedDataType3 getNextParsedDataType3() {
        ParsedDatas parsedDatas;
        ParsedData data;
        while (listIterator.hasNext()){
            parsedDatas = listIterator.next();
            if (parsedDatas!=null ){
                data = parsedDatas.parsedDataLinkedList.get(0);
                if (data!=null){
                    if (data.getClass().equals(new ParsedDataType3().getClass())){
                        Random random = new Random();
                        return (ParsedDataType3)parsedDatas.parsedDataLinkedList.get(random.nextInt(parsedDatas.parsedDataLinkedList.size()));
                    }
                }
            }
        }
        return null;
    }
    public ParsedDataType4 getNextParsedDataType4() {
        ParsedDatas parsedDatas;
        ParsedData data;
        while (listIterator.hasNext()){
            parsedDatas = listIterator.next();
            if (parsedDatas!=null ){
                data = parsedDatas.parsedDataLinkedList.get(0);
                if (data!=null){
                    if (data.getClass().equals(new ParsedDataType4().getClass())){
                        return (ParsedDataType4)data;
                    }
                }
            }
        }
        return null;
    }
}