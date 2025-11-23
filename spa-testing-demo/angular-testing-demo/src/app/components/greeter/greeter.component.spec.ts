// src/app/components/greeter/greeter.component.spec.ts

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { GreeterComponent } from './greeter.component';

describe('GreeterComponent', () => {
  let component: GreeterComponent;
  let fixture: ComponentFixture<GreeterComponent>;
  let compiledHtml: HTMLElement;

  // beforeEach jest uruchamiane przed każdym testem w tym bloku `describe`
  beforeEach(async () => {
    // Konfigurujemy środowisko testowe dla naszego komponentu
    await TestBed.configureTestingModule({
      imports: [ GreeterComponent ] // W standalone components, importujemy je tutaj
    }).compileComponents();
    
    // Tworzymy instancję komponentu
    fixture = TestBed.createComponent(GreeterComponent);
    // Pobieramy dostęp do instancji klasy komponentu
    component = fixture.componentInstance;
    // Pobieramy dostęp do wyrenderowanego elementu HTML
    compiledHtml = fixture.nativeElement;
  });

  it('powinien zostać poprawnie stworzony', () => {
    expect(component).toBeTruthy();
  });

  it('powinien witać "Gościa", gdy właściwość "name" nie jest podana', () => {
    // ARRANGE
    // Nie ustawiamy żadnych właściwości, więc komponent użyje swojej wartości domyślnej.
    
    // ACT
    // Uruchamiamy mechanizm detekcji zmian, aby Angular wyrenderował szablon
    fixture.detectChanges();

    // ASSERT
    // Znajdujemy element h1 w naszym wyrenderowanym HTML i sprawdzamy jego zawartość
    const h1 = compiledHtml.querySelector('h1');
    expect(h1?.textContent).toContain('Witaj, Gościu!');
  });

  it('powinien witać po imieniu, gdy właściwość "name" jest podana', () => {
    // ARRANGE
    // Ustawiamy wartość właściwości @Input bezpośrednio na instancji komponentu
    component.name = 'Anna';

    // ACT
    // Uruchamiamy detekcję zmian, aby szablon zaktualizował się o nową wartość
    fixture.detectChanges();

    // ASSERT
    const h1 = compiledHtml.querySelector('h1');
    expect(h1?.textContent).toContain('Witaj, Anna!');
  });
});