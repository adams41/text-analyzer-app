import { Component } from '@angular/core';
import { TextAnalysisService } from './services/text-analysis.service';

type AnalysisResult = { [key: string]: number };

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'text-analyzer-frontend';
  inputText = '';
  analysisMode = 'vowels';
  onlineMode = false;
  analysisResult: AnalysisResult | null = null;
  previousResults: AnalysisResult[] = []; 

  constructor(private textAnalysisService: TextAnalysisService) {}

  analyzeText() {
    const text = this.inputText;
    if (this.onlineMode) {
      this.textAnalysisService.analyzeTextOnline(this.analysisMode, text).subscribe(
        response => {
          this.analysisResult = response;
          this.handleAnalysisResponse();
        },
        error => {
          alert(error);
        }
      );
    } else {
      this.analysisResult = this.textAnalysisService.analyzeTextLocally(this.analysisMode, text);
      this.handleAnalysisResponse();
    }
  }

  handleAnalysisResponse() {
    if (!this.analysisResult || Object.keys(this.analysisResult).length === 0) {
      alert('No results found. Please enter valid text.');
    } else {
      this.previousResults.push(this.analysisResult);
    }
  }

  toggleOnlineMode(event: Event) {
    this.onlineMode = (event.target as HTMLInputElement)?.checked ?? false;
  }

  clearEntries() {
    this.inputText = '';  
    this.analysisResult = null;  
    this.previousResults = []; 
  }
}