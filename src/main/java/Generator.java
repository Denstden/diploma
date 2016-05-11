import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Generator {
    private String[] args;

    public Generator(String[] args){
        this.args = args;
    }

    public void generate(){
        if (args.length==2) {
            VariantFabric variantFabric;
            String dataFolder;
            String resultFolder;
            int countVariants;
            int countStrPreambula;
            String variantPreambula="";

            int mod = Integer.parseInt(args[0]);
            String configSource = args[1];

            Scanner in = null;
            try {
                in = new Scanner(new File(configSource));
            } catch (FileNotFoundException e) {
                System.out.println("Config file does not found.");
            }
            dataFolder = in.nextLine();
            resultFolder = in.nextLine();
            countVariants = Integer.parseInt(in.nextLine());
            countStrPreambula = Integer.parseInt(in.nextLine());
            for (int i=0;i<countStrPreambula;i++)
                variantPreambula += in.nextLine()+"\r\n";

            if (resultFolder.contains("esult"))
                deleteAllFilesFolder(resultFolder);

            switch (mod){
                case 1:{
                    try {
                        int countQuestionsType1 = Integer.parseInt(in.nextLine());
                        int countQuestionsType2 = Integer.parseInt(in.nextLine());
                        int countQuestionsType3 = Integer.parseInt(in.nextLine());
                        int countQuestionsType4 = Integer.parseInt(in.nextLine());
                        variantFabric = new VariantFabric(dataFolder, variantPreambula, countQuestionsType1, countQuestionsType2, countQuestionsType3, countQuestionsType4);
                        for (int i = 0; i < countVariants; i++) {
                            Variant variant = variantFabric.getVariant();
                            variant.toFile(resultFolder);
                            variant.print();
                        }
                        break;
                    }
                    catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                    catch (WrongDataQuestEx e){
                        System.out.println(e.getMessage());
                    }
                    catch (NoQuestionEx e){
                        System.out.println(e.getMessage());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                case 2: {
                    try {
                        variantFabric = new VariantFabric(dataFolder, variantPreambula, new File(dataFolder).list().length);
                        for (int i = 0; i < countVariants; i++) {
                            Variant variant = variantFabric.gVariant();
                            variant.toFile(resultFolder);
                            variant.print();
                        }
                        break;
                    }
                    catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                    catch (WrongDataQuestEx e){
                        System.out.println(e.getMessage());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }


            }
        }
        else
            System.out.println("Set correct program arguments.\n<mod> <configFileSource>");
    }

    private void deleteAllFilesFolder(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }
}
