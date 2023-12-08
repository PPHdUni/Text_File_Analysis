import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartsWith extends Word_Analysis {

    public StartsWith(String substring, boolean most_common, boolean length_analysis) {
        super(substring, most_common, length_analysis);
    }

    public void Line_Analysis(String line) {

        String[] words;
        final int max_num_threads = 10;

        line = line.replaceAll("\\.", "");
        line = line.toLowerCase();
        words = line.split(" ");

        ExecutorService exeThreadPool = Executors.newFixedThreadPool(max_num_threads);

        for (String word : words) {
            Thread sw_thread = new StartsWithThread(word);
            exeThreadPool.execute(sw_thread);
        }
        exeThreadPool.shutdown();
        while (!exeThreadPool.isTerminated()) ;

    }

    public class StartsWithThread extends Thread {

        private final String word;

        public StartsWithThread(String word) {
            this.word = word;
        }

        public void run() {

            if (word.startsWith(substring)) {

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
