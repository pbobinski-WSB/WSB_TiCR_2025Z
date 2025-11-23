import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CounterComponent } from './counter.component';

describe('CounterComponent', () => {
  let fixture: ComponentFixture<CounterComponent>;
  let component: CounterComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CounterComponent]
    }).compileComponents();
    
    fixture = TestBed.createComponent(CounterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('licznik powinien zwiększać wartość po kliknięciu', () => {
    const button = fixture.nativeElement.querySelector('button');
    button.click();
    // Po każdej akcji musimy jawnie uruchomić detekcję zmian, aby HTML się zaktualizował
    fixture.detectChanges();
    const p = fixture.nativeElement.querySelector('p');
    expect(p.textContent).toContain('Wartość licznika: 1');
  });
});