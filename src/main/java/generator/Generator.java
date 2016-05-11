package generator;

import exception.NoQuestionException;
import exception.WrongDataQuestionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import variant.Variant;
import variant.VariantFabric;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Generator {
    private static final Logger logger = LogManager.getLogger(Generator.class);
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
                logger.log(Level.ERROR,"Config file does not found.");
            }
            dataFolder = in.nextLine();
            resultFolder = in.nextLine();
            countVariants = Integer.parseInt(in.nextLine());
            countStrPreambula = Integer.parseInt(in.nextLine());
            for (int i=0;i<countStrPreambula;i++)
                variantPreambula += in.nextLine()+"\r\n";

            if (resultFolder.contains("esult"))
                FolderCleaner.deleteAllFilesFolder(resultFolder);


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
                        logger.log(Level.ERROR,e.getMessage());
                    }
                    catch (WrongDataQuestionException e){
                        logger.log(Level.ERROR,e.getMessage());
                    }
                    catch (NoQuestionException e){
                        logger.log(Level.ERROR,e.getMessage());
                    }
                    catch (IOException e){
                        logger.log(Level.ERROR,e.getMessage());
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
                        logger.log(Level.ERROR,e.getMessage());
                    }
                    catch (WrongDataQuestionException e){
                        logger.log(Level.ERROR,e.getMessage());
                    }
                    catch (IOException e){
                        logger.log(Level.ERROR,e.getMessage());
                    }
                }


            }
        }
        else
            logger.log(Level.ERROR,"Set correct program arguments.\n<mod> <configFileSource>");
    }

}
