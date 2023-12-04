package textFileAnalysisCode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Word_Analysis {

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

    public abstract void Line_Analysis(String line);

    public int getWordCount() {return word_count;}

}

class EndsWith extends Word_Analysis {

    public EndsWith(String substring, boolean most_common, boolean min_length, boolean max_length) {
        super(substring, most_common, min_length, max_length);
    }

    public void Line_Analysis(String line) {



    }

}
