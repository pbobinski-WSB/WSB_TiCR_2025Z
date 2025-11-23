// src/app/app.component.ts

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

// --- KROK 1: UPEWNIJ SIĘ, ŻE TE IMPORTY ISTNIEJĄ ---
import { GreeterComponent } from './components/greeter/greeter.component';
import { CounterComponent } from './components/counter/counter.component';
import { AsyncMessageFetcherComponent } from './components/async-message-fetcher/async-message-fetcher.component';

@Component({
  selector: 'app-root',
  standalone: true,
  // --- KROK 2: DODAJ KOMPONENTY DO TABLICY `imports` ---
  imports: [
    CommonModule,
    RouterOutlet,
    // Dodaj tutaj swoje komponenty, oddzielając je przecinkami
    GreeterComponent,
    CounterComponent,
    AsyncMessageFetcherComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-testing-demo';
}