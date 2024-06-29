import { Component, OnInit } from '@angular/core'
import { WebSocketService } from './services/websocketOrders.service'
import { catchError, throwError } from 'rxjs'

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(private wsService: WebSocketService) {}

  ngOnInit(): void {
    this.wsService.connect()
  }
}
