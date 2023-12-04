package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import textFileAnalysisCode.StartsWith;
import textFileAnalysisCode.Word_Analysis;

public class AnalysisTests {

    Word_Analysis analysis;

    @BeforeAll
    static void preparation() {
        Word_Analysis analysis = new StartsWith("fu", false, false, false);
    }

    @Test
    void startsWithTests() {

        analysis.Line_Analysis("fume fury lary apple full fuse fuze flame june");
        assertEquals(analysis.getWordCount(), 5, "Échec du test de StartsWith");

    }

}
