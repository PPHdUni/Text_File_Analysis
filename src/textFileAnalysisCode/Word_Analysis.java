package textFileAnalysisCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Word_Analysis {

    protected String substring;
    protected boolean most_common, length_analysis;
    protected int word_count;
    protected final Object lock1, lock2;
    protected ArrayList<String> min_length_words,max_length_words;
    protected HashMap<String,Integer> common_words_map;

    public Word_Analysis(String substring, boolean most_common, boolean length_analysis) {

        this.substring = substring;
        this.most_common = most_common;
        this.length_analysis = length_analysis;

        this.word_count = 0;

        lock1 = new Object();
        lock2 = new Object();

        min_length_words = new ArrayList<>();
        max_length_words = new ArrayList<>();
        common_words_map = new HashMap<>();
    }

    public int getWordCount() {return word_count;}

    public  ArrayList<String> getMinLengthArray() {return min_length_words;}

    public  ArrayList<String> getMaxLengthArray() {return max_length_words;}

    public  HashMap<String,Integer> getCommonWordsMap() {return common_words_map;}

    public abstract void Line_Analysis(String line);

    protected void LengthAnalysis(String word) {

        System.out.println("Début du sommeil");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fin du sommeil");

        if(min_length_words.isEmpty() && max_length_words.isEmpty()) {
            min_length_words.add(word);
            max_length_words.add(word);
        }

        if(word.length()==min_length_words.get(0).length() && !min_length_words.contains(word)) {
            min_length_words.add(word);
        }
        if(word.length()<min_length_words.get(0).length()) {
            min_length_words.clear();
            min_length_words.add(word);
        }

        if(word.length()==max_length_words.get(0).length() && !max_length_words.contains(word)) {
            max_length_words.add(word);
        }
        if(word.length()>max_length_words.get(0).length()) {
            max_length_words.clear();
            max_length_words.add(word);
        }

    }

    protected void CommonWordAnalysis(String word) {

        if(!common_words_map.containsKey(word)) {
            common_words_map.put(word,1);
        }
        else {
            int mapValue = common_words_map.get(word);
            common_words_map.replace(word,mapValue+1);
        }

    }

}

class EndsWith extends Word_Analysis {

    public EndsWith(String substring, boolean most_common, boolean length_analysis) {
        super(substring, most_common, length_analysis);
    }

    public void Line_Analysis(String line) {



    }

}
