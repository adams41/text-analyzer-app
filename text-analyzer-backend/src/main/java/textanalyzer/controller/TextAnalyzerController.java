package textanalyzer.controller;

import textanalyzer.service.TextAnalyzerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import textanalyzer.util.Mode;

import java.util.Map;

@RestController
@RequestMapping("/api/analyze")
public class TextAnalyzerController {

    private static final Logger logger = LoggerFactory.getLogger(TextAnalyzerController.class);

    private final TextAnalyzerService textAnalyzerService;

    public TextAnalyzerController(TextAnalyzerService textAnalyzerService) {
        this.textAnalyzerService = textAnalyzerService;
    }

    @PostMapping
    public Map<Character, Integer> analyzeText(@RequestParam(required = true) String mode, @RequestParam(required = true) String text) {

        Mode analysisMode;

        logger.info("POST REST API called");

        if (mode.equalsIgnoreCase("vowels")) {
            analysisMode = textanalyzer.util.Mode.VOWELS;
        } else if (mode.equalsIgnoreCase("consonants")) {
            analysisMode = Mode.CONSONANTS;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Parameter");
        }
        return textAnalyzerService.analyzeText(analysisMode, text);
    }
}





