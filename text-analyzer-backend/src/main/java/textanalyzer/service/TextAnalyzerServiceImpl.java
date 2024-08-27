package textanalyzer.service;

import textanalyzer.util.Mode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TextAnalyzerServiceImpl implements TextAnalyzerService{

    @Override
    public Map<Character, Integer> analyzeText(Mode mode, String input) {
        Map<Character, Integer> result = new HashMap<>();
        char[] chars = input.toCharArray();

        for (char c : chars) {
            char upperChar = Character.toUpperCase(c);
            if ((mode.equals(Mode.VOWELS) && isVowel(c)) ||
                    (mode.equals(Mode.CONSONANTS) && !isVowel(c) && Character.isLetter(c))) {
                result.put(upperChar, result.getOrDefault(upperChar, 0) + 1);
            }
        }

        return result;
    }

    private boolean isVowel(char c) {
        return "AEIOUYaeiouy".indexOf(c) != -1;
    }
}
