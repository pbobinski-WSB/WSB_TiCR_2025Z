// src/app/components/async-message-fetcher/async-message-fetcher.component.ts

import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // <-- KROK 1: ZAIMPORTUJ

@Component({
  selector: 'app-async-message-fetcher',
  standalone: true, // <-- Upewnij się, że to tu jest
  imports: [
    CommonModule // <-- KROK 2: DODAJ DO TABLICY `imports`
  ],
  templateUrl: './async-message-fetcher.component.html',
})
export class AsyncMessageFetcherComponent {
  public message?: string;
  public error?: string;

  constructor(private http: HttpClient) {}

  fetchMessage() {
    this.message = undefined;
    this.error = undefined;
    this.http.get<any>('https://jsonplaceholder.typicode.com/todos/1').subscribe({
      next: (data) => this.message = data.title,
      error: () => this.error = 'Nie udało się pobrać wiadomości',
    });
  }
}