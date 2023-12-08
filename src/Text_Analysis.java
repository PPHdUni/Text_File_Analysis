public class Text_Analysis {

    public static void main (String[] args) {

        Word_Analysis analysis = new StartsWith("fu", true, true);

        System.out.println(analysis.getWordCount());

        analysis.Line_Analysis("fume fury lary apple full fuse fuze fun furious fume fur fullest fur");

        System.out.println(analysis.getWordCount());

        System.out.println(analysis.getMinLengthArray());

        System.out.println(analysis.getMaxLengthArray());

        System.out.println(analysis.getCommonWordsMap());

        System.out.println(analysis.getCommonWordsList());

    }

}
