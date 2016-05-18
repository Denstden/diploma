package parser;

import exception.WrongDataQuestionException;
import format.FormatType;
import parser.parsed_data.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {
    private String source;
    private LinkedList<ParsedDatas> parsedDataListList;
    private Iterator<ParsedDatas> listIterator;

    public Parser(String source) {
        this.source = source;
        parsedDataListList = new LinkedList<>();
    }

    public void parse() throws FileNotFoundException, WrongDataQuestionException {
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

    private void parseQuestionType1(Scanner scanner, String folder) throws WrongDataQuestionException, FileNotFoundException {
        String f_pos, f_neg;
        ParsedDataType1 parsedDataType1 = new ParsedDataType1();
        try {
            parsedDataType1.setPreambula(scanner.nextLine());
            parsedDataType1.col_answers = scanner.nextInt();
            parsedDataType1.col_pos_answers = scanner.nextInt();
            parsedDataType1.col_neg_answers = scanner.nextInt();
            f_pos = source+"\\"+folder+"\\" + scanner.next();
            f_neg = source+"\\"+folder+"\\" + scanner.next();
            String type = scanner.next();
            if (type.equals("column")) {
                parsedDataType1.getFormatSettings().setType(FormatType.COLUMN);
                parsedDataType1.getFormatSettings().setCount(0);
            } else if (type.equals("columns")) {
                parsedDataType1.getFormatSettings().setType(FormatType.COLUMNS);
                parsedDataType1.getFormatSettings().setCount(scanner.nextInt());
            } else if (type.equals("row")) {
                parsedDataType1.getFormatSettings().setType(FormatType.ROW);
                parsedDataType1.getFormatSettings().setCount(0);
            } else if (type.equals("rows")) {
                parsedDataType1.getFormatSettings().setType(FormatType.ROWS);
                parsedDataType1.getFormatSettings().setCount(scanner.nextInt());
            }

        } catch (Exception e) {
            throw new WrongDataQuestionException();
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
    private void parseQuestionType2(Scanner scanner, String folder) throws FileNotFoundException, WrongDataQuestionException {
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
                parsedDataType2.setPreambula(globalPreambula+' '+in.nextLine());
                parsedDataType2.correctAnswer = in.nextLine();
                String type = in.nextLine();
                if (type.equals("column")) {
                    parsedDataType2.getFormatSettings().setType(FormatType.COLUMN);
                    parsedDataType2.getFormatSettings().setCount(0);
                } else if (type.equals("row")) {
                    parsedDataType2.getFormatSettings().setType(FormatType.ROW);
                    parsedDataType2.getFormatSettings().setCount(0);
                }

            } catch (Exception e) {
                throw new WrongDataQuestionException();
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
    private void parseQuestionType3(Scanner scanner, String folder) throws FileNotFoundException, WrongDataQuestionException {
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
                parsedDataType3.setPreambula(globalPreambula+' '+in.nextLine());
                parsedDataType3.getFormatSettings().setCount(Integer.valueOf(in.nextLine()));
            } catch (Exception e) {
                throw new WrongDataQuestionException();
            }
            parsedDatas.add(parsedDataType3);
        }
        Collections.shuffle(parsedDatas.parsedDataLinkedList);
        parsedDataListList.add(parsedDatas);
        scanner.close();
        in.close();
    }
    private void parseQuestionType4(Scanner scanner, String folder) throws FileNotFoundException, WrongDataQuestionException {
        String f_pos, f_neg;
        ParsedDataType4 parsedDataType4= new ParsedDataType4();
        try {
            parsedDataType4.setPreambula(scanner.nextLine());
            parsedDataType4.col_answers = scanner.nextInt();
            f_pos = source+"\\"+folder+"\\" + scanner.next();
            f_neg = source+"\\"+folder+"\\" + scanner.next();
            String type = scanner.next();
            if (type.equals("column")) {
                parsedDataType4.getFormatSettings().setType(FormatType.COLUMN);
                parsedDataType4.getFormatSettings().setCount(0);
            } else if (type.equals("columns")) {
                parsedDataType4.getFormatSettings().setType(FormatType.COLUMNS);
                parsedDataType4.getFormatSettings().setCount(scanner.nextInt());
            } else if (type.equals("row")) {
                parsedDataType4.getFormatSettings().setType(FormatType.ROW);
                parsedDataType4.getFormatSettings().setCount(0);
            } else if (type.equals("rows")) {
                parsedDataType4.getFormatSettings().setType(FormatType.ROWS);
                parsedDataType4.getFormatSettings().setCount(scanner.nextInt());
            }

        } catch (Exception e) {
            throw new WrongDataQuestionException();
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
