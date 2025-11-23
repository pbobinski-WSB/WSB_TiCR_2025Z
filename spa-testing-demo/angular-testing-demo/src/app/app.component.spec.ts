// src/app/app.component.spec.ts

import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { HttpClientTestingModule } from '@angular/common/http/testing'; // <-- KROK 1: ZAIMPORTUJ

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      // `AppComponent` jest komponentem standalone, więc importujemy go bezpośrednio.
      // Ponieważ `AppComponent` używa w swoim szablonie innych komponentów,
      // które z kolei potrzebują `HttpClient`, musimy dostarczyć mocka.
      imports: [
        AppComponent,
        HttpClientTestingModule // <-- KROK 2: DODAJ DO TABLICY `imports`
      ],
    }).compileComponents();
  });

  // Ten test powinien teraz przejść
  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  // Ten test powinien teraz przejść
  it(`should have the 'angular-testing-demo' title`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('angular-testing-demo');
  });

  // Ten test może wymagać modyfikacji, bo domyślnie szablon się zmienił,
  // ale błąd `NullInjectorError` zniknie.
  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    // Zmieńmy to, żeby testować to, co faktycznie jest w naszym nowym szablonie
    expect(compiled.querySelector('h1')?.textContent).toContain('Witaj, Studencie!');
  });
});