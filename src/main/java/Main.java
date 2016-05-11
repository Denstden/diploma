import generator.Generator;

public class Main {
    /**PROGRAM ARGUMENTS
     *<mod> <config_file>
     *
     *<config_file>::=<DataSource>
     *                <ResultSource>
     *                <CountVariants>
     *                <CountLinesOfVariantPreambula>
     *                <VariantPreambula>
     *           if mod==1
     *                <CountQuestionsType1>
     *                <CountQuestionsType2>
     *                <CountQuestionsType3>
     *                <CountQuestionsType4>
     */
    public static void main(String[] args) {
        Generator generator = new Generator(args);
        generator.generate();
    }
}
