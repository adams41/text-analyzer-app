import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

type AnalysisResult = { [key: string]: number };

@Injectable({
  providedIn: 'root'
})

export class TextAnalysisService {
  constructor(private http: HttpClient) {}

  analyzeTextOnline(mode: string, text: string): Observable<AnalysisResult> {
    return this.http.post<AnalysisResult>('http://localhost:8080/api/analyze', null, {
      params: { mode: mode, text: text }
    }).pipe(
      catchError(error => {
        throw 'Error: ' + error.error;
      })
    );
  }

  analyzeTextLocally(mode: string, text: string): AnalysisResult {
    const result: AnalysisResult = {};
    const characters = mode === 'vowels' ? 'aeiouyAEIOUY' : 'bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ';
    
    for (const char of text) {
      if (characters.includes(char)) {
        result[char.toUpperCase()] = (result[char.toUpperCase()] || 0) + 1;
      }
    }
    return result;
  }
}