import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AnalysisTests {

    static Word_Analysis analysis;

    @BeforeAll
    static void preparation() {

    }

    @Test
    void startsWithTests() {
        analysis = new StartsWith("fu", true, true);
        for(int i=1; i<=10; i++) {
            analysis.Line_Analysis("fume fury lary apple full fuse fuze fun furious fume fur fullest flame june fur");
            assertEquals(11*i, analysis.getWordCount(), "Échec du test word_count de StartsWith");

            ArrayList<String> expect_min_list = new ArrayList<>(Arrays.asList("fun", "fur"));
            Collections.sort(expect_min_list);
            ArrayList<String> actual_min_list = analysis.getMinLengthArray();
            Collections.sort(actual_min_list);
            assertEquals(expect_min_list, actual_min_list, "Échec du test min_length de StartsWith");

            ArrayList<String> expect_max_list = new ArrayList<>(Arrays.asList("furious", "fullest"));
            Collections.sort(expect_max_list);
            ArrayList<String> actual_max_list = analysis.getMaxLengthArray();
            Collections.sort(actual_max_list);
            assertEquals(expect_max_list, actual_max_list, "Échec du test max_length de StartsWith");

            ArrayList<String> expect_common_list = new ArrayList<>(Arrays.asList("fume", "fur"));
            Collections.sort(expect_common_list);
            ArrayList<String> actual_common_list = analysis.getCommonWordsList();
            Collections.sort(actual_common_list);
            assertEquals(expect_common_list,actual_common_list,"Échec du test#1 most_common_words de StartsWith");
            assertEquals(2*i,analysis.getCommonWordsMap().get("fume"),"Échec du test#2 most_common_words de StartsWith");
            assertEquals(2*i,analysis.getCommonWordsMap().get("fur"),"Échec du test#3 most_common_words de StartsWith");
        }
    }

    @Test
    void EndsWithTests() {
        analysis = new EndsWith("le", true, true);
        for (int i = 1; i <= 10; i++) {
            analysis.Line_Analysis("lame little kale apple lemur apple expendable handle unbearable expendable isle apple");
            assertEquals(10 * i, analysis.getWordCount(), "Échec du test word_count de EndsWith");

            ArrayList<String> expect_min_list = new ArrayList<>(Arrays.asList("kale", "isle"));
            Collections.sort(expect_min_list);
            ArrayList<String> actual_min_list = analysis.getMinLengthArray();
            Collections.sort(actual_min_list);
            assertEquals(expect_min_list, actual_min_list, "Échec du test min_length de EndsWith");

            ArrayList<String> expect_max_list = new ArrayList<>(Arrays.asList("expendable", "unbearable"));
            Collections.sort(expect_max_list);
            ArrayList<String> actual_max_list = analysis.getMaxLengthArray();
            Collections.sort(actual_max_list);
            assertEquals(expect_max_list, actual_max_list, "Échec du test max_length de EndsWith");

            ArrayList<String> expect_common_list = new ArrayList<>(Arrays.asList("apple"));
            Collections.sort(expect_common_list);
            ArrayList<String> actual_common_list = analysis.getCommonWordsList();
            Collections.sort(actual_common_list);
            assertEquals(expect_common_list, actual_common_list, "Échec du test#1 most_common_words de EndsWith");
            assertEquals(3 * i, analysis.getCommonWordsMap().get("apple"), "Échec du test#2 most_common_words de EndsWith");
            assertEquals(2 * i, analysis.getCommonWordsMap().get("expendable"), "Échec du test#3 most_common_words de EndsWith");
        }
    }
}
