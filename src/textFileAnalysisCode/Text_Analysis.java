package textFileAnalysisCode;

import java.util.ArrayList;

public class Text_Analysis {

    public static void main (String[] args) {

        Word_Analysis analysis = new StartsWith("fu", false, false, false);

        System.out.println(analysis.getWordCount());

        analysis.Line_Analysis("fume fury lary apple full fuse fuze");

        System.out.println(analysis.getWordCount());

    }

}
