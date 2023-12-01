abstract class Word_Analysis {

    protected String substring;
    protected boolean most_common, min_length, max_length;
    protected int word_count;

    public Word_Analysis(String substring, boolean most_common, boolean min_length, boolean max_length) {

        this.substring = substring;
        this.most_common = most_common;
        this.min_length = min_length;
        this.max_length = max_length;
        this.word_count = 0;

    }

    abstract void Line_Analysis(String line);

}

class StartsWith extends Word_Analysis {

    private int word_count;

    public StartsWith(String substring, boolean most_common, boolean min_length, boolean max_length) {
        super(substring, most_common, min_length, max_length);
    }

    public void Line_Analysis(String line) {

        String[] words;
        int number_of_words,
                number_of_threads,
                index = 0;
        StartsWithThread sw_thread;

        line = line.replaceAll("\\.", "");
        line = line.toLowerCase();
        words = line.split(" ");

        number_of_words = words.length;

        while(0 < number_of_words) {
            number_of_threads = Math.min(10, number_of_words);
            sw_thread = new StartsWithThread(words[index]);

            for(int j=1; j <= number_of_threads; j++ ) {
                sw_thread = new StartsWithThread(words[index]);
                index++;
                sw_thread.start();
            }

            number_of_words -= number_of_threads;
            sw_thread.join();
        }
    }

    public class StartsWithThread extends Thread {

        private String word;

        public StartsWithThread(String word) {this.word = word;}

        public void run() {

            if(word.startsWith(substring)) {



            }
        }
    }
}

class EndsWith extends Word_Analysis {

    public EndsWith(String substring, boolean most_common, boolean min_length, boolean max_length) {
        super(substring, most_common, min_length, max_length);
    }

    public void Line_Analysis(String line) {



    }

}
