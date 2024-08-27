package textanalyzer.service;

import textanalyzer.util.Mode;

import java.util.Map;
public interface TextAnalyzerService {

    Map<Character, Integer> analyzeText (Mode mode, String text);

}
