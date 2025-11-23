import { Component } from '@angular/core';
@Component({
  selector: 'app-counter',
  templateUrl: './counter.component.html',
})
export class CounterComponent {
  public count = 0;
  public increment(): void { this.count++; }
}