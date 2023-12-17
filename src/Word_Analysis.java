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

    //Constructeur pour la classe Word_Analysis
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

    //Retourne le nombre de mots qui répond aux critères
    public int getWordCount() {return word_count;}

    //Retourne la liste des mots valides les moins longs
    public ArrayList<String> getMinLengthArray() {return min_length_words;}

    //Retourne la liste des mots valides les plus longs
    public ArrayList<String> getMaxLengthArray() {return max_length_words;}

    //Retourne une carte de l'occurrence des mots valides
    public HashMap<String,Integer> getCommonWordsMap() {return common_words_map;}

    //Retourne la liste des mots les plus communs
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

    //Cette fonction sépare une ligne (représenter par le param "line") en
    //plusieurs mots à être vérifier par des threads.
    //Il peut avoir un maximum de 10 threads par execution de cette fonction
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

        for (String word : words) { //Création des threads, qui sont limités à un nombre max de 10
            Thread thread = new AnalysisThread(word);
            exeThreadPool.execute(thread);
        }
        exeThreadPool.shutdown();
        while (!exeThreadPool.isTerminated()) ;

    }

    //Cette fonction détermine si un mot est plus ou moins long que ceux précédent
    //et ajuste la liste correspondante
    protected void LengthAnalysis(String word) {

        if(min_length_words.isEmpty() && max_length_words.isEmpty()) { //Si les listes sont vides, le mot y sont ajouté automatiquement
            min_length_words.add(word);
            max_length_words.add(word);
        }

        if(word.length()==min_length_words.get(0).length() && !min_length_words.contains(word)) { //Si le mot est aussi long que ceux dans la liste, celui-ci est seulement ajouté
            min_length_words.add(word);
        }
        if(word.length()<min_length_words.get(0).length()) { //Si le mot est moins long que ceux dans la liste, celle-ci est réinitialiser avec le mot ajouté
            min_length_words.clear();
            min_length_words.add(word);
        }

        if(word.length()==max_length_words.get(0).length() && !max_length_words.contains(word)) { //Si le mot est aussi long que ceux dans la liste, celui-ci est seulement ajouté
            max_length_words.add(word);
        }
        if(word.length()>max_length_words.get(0).length()) { //Si le mot est plus long que ceux dans la liste, celle-ci est réinitialiser avec le mot ajouté
            max_length_words.clear();
            max_length_words.add(word);
        }

    }

    //Cette fonction sert à ajuster la HashMap d'occurence de mot au fur et à mesure que le programme fonctionne
    protected void CommonWordAnalysis(String word) {

        if(!common_words_map.containsKey(word)) {
            common_words_map.put(word,1);
        }
        else {
            int mapValue = common_words_map.get(word);
            common_words_map.replace(word,mapValue+1);
        }

    }

    //Classe intérieur pour les threads crée par la fonction Line_Analysis
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
