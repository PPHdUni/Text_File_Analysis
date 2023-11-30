abstract class Word_Analysis {

    private String substring;
    private boolean most_common, min_length, max_length;

    public Word_Analysis(String substring, boolean param1, boolean param2, boolean param3) {

        this.substring = substring;

    }

}

class StartsWith extends Word_Analysis {

    int word_count;

    public StartsWith(String substring, boolean param1, boolean param2, boolean param3) {
        super(substring, param1, param2, param3);


    }

    public class StartsWithThread extends Thread {

        private String word;

        public StartsWithThread(String word) {this.word = word;}

        public void run() {

            word_count++;

        }

    }

}

class EndsWith extends Word_Analysis {

    public EndsWith(String substring, boolean param1, boolean param2, boolean param3) {
        super(substring, param1, param2, param3);


    }

}
