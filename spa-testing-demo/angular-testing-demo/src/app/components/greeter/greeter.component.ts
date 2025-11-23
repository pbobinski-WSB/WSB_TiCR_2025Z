
import { Component, Input } from '@angular/core';
@Component({
  selector: 'app-greeter',
  templateUrl: './greeter.component.html',
})
export class GreeterComponent {
  @Input() name: string = 'Gościu';
}