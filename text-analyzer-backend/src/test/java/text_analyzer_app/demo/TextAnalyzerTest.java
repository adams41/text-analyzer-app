package text_analyzer_app.demo;

import textanalyzer.service.TextAnalyzerService;
import textanalyzer.util.Mode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import textanalyzer.TextAnalyzerApplication;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TextAnalyzerApplication.class)
@ExtendWith(SpringExtension.class)
class TextAnalyzerTest {

    @Autowired
    private TextAnalyzerService textAnalyzerService;


    @Test
     void testAnalyzeTextVowels() {
        String input = "Text";
        Map<Character, Integer> result = textAnalyzerService.analyzeText(Mode.VOWELS, input);

        assertEquals(1, result.get('E'));
        assertEquals(0, result.getOrDefault('D', 0));
        assertEquals(0, result.getOrDefault('L', 0));
        assertEquals(0, result.getOrDefault('S', 0));
    }

    @Test
     void testAnalyzeTextConsonants() {
        String input = "Text";
        Map<Character, Integer> result = textAnalyzerService.analyzeText(Mode.CONSONANTS, input);

        assertEquals(1, result.get('X'));
        assertEquals(0, result.getOrDefault('E', 0));
        assertEquals(0, result.getOrDefault('A', 0));
    }
}

