import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AsyncMessageFetcherComponent } from './async-message-fetcher.component';

describe('AsyncMessageFetcherComponent', () => {
  let fixture: ComponentFixture<AsyncMessageFetcherComponent>;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AsyncMessageFetcherComponent, HttpClientTestingModule] // Używamy specjalnego modułu do mockowania HTTP
    }).compileComponents();
    
    fixture = TestBed.createComponent(AsyncMessageFetcherComponent);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('powinien wyświetlić wiadomość po udanym pobraniu', () => {
    fixture.nativeElement.querySelector('button').click();
    // Oczekujemy, że zostanie wykonane jedno zapytanie GET
    const req = httpTestingController.expectOne('https://jsonplaceholder.typicode.com/todos/1');
    // Symulujemy odpowiedź serwera
    req.flush({ title: 'Testowa wiadomość' });
    fixture.detectChanges();
    const p = fixture.nativeElement.querySelector('[data-testid="message-display"]');
    expect(p.textContent).toBe('Testowa wiadomość');
  });

  it('powinien wyświetlić błąd, gdy pobieranie zawiedzie', () => {
    fixture.nativeElement.querySelector('button').click();
    const req = httpTestingController.expectOne('https://jsonplaceholder.typicode.com/todos/1');
    // Symulujemy błąd sieciowy
    req.error(new ProgressEvent('network error'));
    fixture.detectChanges();
    const p = fixture.nativeElement.querySelector('[role="alert"]');
    expect(p.textContent).toBe('Nie udało się pobrać wiadomości');
  });
});