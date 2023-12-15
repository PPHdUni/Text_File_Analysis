import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Text_Analysis {

    private ArrayList<Word_Analysis> analysisCommandList;

    public Text_Analysis(ArrayList<Word_Analysis> analysisCommands) {
        this.analysisCommandList = analysisCommands;
    }

    public static void main (String[] args) {

        ArrayList<Word_Analysis> commandList = new ArrayList<>();
        commandList.add(new StartsWith("co", true, true));
        commandList.add(new EndsWith("er", true, true));

        ArrayList<Word_Analysis> hybridList = new ArrayList<>(Arrays.asList(
                new StartsWith("d", true, true),
                new EndsWith("s", true, true)));
        commandList.add(new Hybrid(true,true,hybridList));

        Text_Analysis text_analysis = new Text_Analysis(commandList);
        File text_file = new File("src/analysis_test.txt");
        text_analysis.readFile(text_file);
        text_analysis.display();

    }

    public void readFile(File chosenFile) {

        Scanner fileScanner;
        String line;
        Word_Analysis analysisCommand;

        try {
            fileScanner = new Scanner(chosenFile);
        }
        catch (FileNotFoundException exception) {
            System.out.println("Error: File not found");
            return;
        }

        while (fileScanner.hasNextLine()) {

            line = fileScanner.nextLine();
            for (int i = 0; i<analysisCommandList.size(); i++) {
                analysisCommand = analysisCommandList.get(i);
                analysisCommand.Line_Analysis(line);
                analysisCommandList.set(i,analysisCommand);
            }

        }

    }

    public void display() {

        for(Word_Analysis analysis: analysisCommandList){

            System.out.println("Data for "+analysis.toString());

            System.out.println("Word count: "+analysis.getWordCount());

            System.out.println("Minimum length words: "+analysis.getMinLengthArray());

            System.out.println("Maximum length words: "+analysis.getMaxLengthArray());

            System.out.println("Map of the ocurrence of words: "+analysis.getCommonWordsMap());

            System.out.println("Most common words: "+analysis.getCommonWordsList());

            System.out.println();

        }
    }

    public ArrayList<Word_Analysis> getCommandList() {return analysisCommandList;}

}
