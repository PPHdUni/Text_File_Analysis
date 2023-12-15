import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Word_Analysis {

    protected boolean most_common, length_analysis;
    protected int word_count;
    protected final Object lock1, lock2, lock3;
    protected ArrayList<String> min_length_words,max_length_words;
    protected HashMap<String,Integer> common_words_map;

    public Word_Analysis(boolean most_common, boolean length_analysis) {

        this.most_common = most_common;
        this.length_analysis = length_analysis;

        this.word_count = 0;

        lock1 = new Object();
        lock2 = new Object();
        lock3 = new Object();

        min_length_words = new ArrayList<>();
        max_length_words = new ArrayList<>();
        common_words_map = new HashMap<>();
    }

    public abstract boolean isValid(String word);

    public abstract String toString();

    public int getWordCount() {return word_count;}

    public ArrayList<String> getMinLengthArray() {return min_length_words;}

    public ArrayList<String> getMaxLengthArray() {return max_length_words;}

    public HashMap<String,Integer> getCommonWordsMap() {return common_words_map;}

    public ArrayList<String> getCommonWordsList() {

        ArrayList<String> common_words_array = new ArrayList<>();
        Set<String> keyList = common_words_map.keySet();

        for(String key : keyList) {
            if(common_words_array.isEmpty()) {
                common_words_array.add(key);
            }

            if(common_words_map.get(key)==common_words_map.get(common_words_array.get(0)) &&
                    !common_words_array.contains(key)) {
                common_words_array.add(key);
            }

            if(common_words_map.get(key)>common_words_map.get(common_words_array.get(0))) {
                common_words_array.clear();
                common_words_array.add(key);
            }
        }

        return common_words_array;
    }

    public void Line_Analysis(String line) {

        String[] words;
        final int max_num_threads = 10;

        line = line.replaceAll("\\.", "");
        line = line.replaceAll(",", "");
        line = line.toLowerCase();
        line = line.replaceAll("n't", " not");
        line = line.replaceAll(".’", "");
        words = line.split(" ");

        ExecutorService exeThreadPool = Executors.newFixedThreadPool(max_num_threads);

        for (String word : words) {
            Thread thread = new AnalysisThread(word);
            exeThreadPool.execute(thread);
        }
        exeThreadPool.shutdown();
        while (!exeThreadPool.isTerminated()) ;

    }

    protected void LengthAnalysis(String word) {

//        System.out.println("Début du sommeil");
//        try {
//            Thread.sleep(2000);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Fin du sommeil");

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

    public class AnalysisThread extends Thread {

        private final String word;

        public AnalysisThread(String word) {
            this.word = word;
        }

        public void run() {

            if (isValid(word)) {

                synchronized (lock1) {
                    word_count++;
                }

                if(length_analysis) {
                    synchronized (lock2) {
                        LengthAnalysis(word);
                    }
                }

                if(most_common) {
                    synchronized (lock3) {
                        CommonWordAnalysis(word);
                    }
                }

            }
        }


    }

}
