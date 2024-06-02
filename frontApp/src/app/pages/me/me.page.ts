import { Component, OnInit } from '@angular/core'
import { Router, RouterLink } from '@angular/router'
import { AuthService } from '../../services/auth.service'
import { AppComponent } from '../../app.component'
import { IonicModule } from '@ionic/angular'
import { CurrencyPipe, DatePipe, NgForOf, NgIf } from '@angular/common'
import { UserResponseDto } from '../../models/user.entity'
import { UserService } from '../../services/user.service'

@Component({
  selector: 'app-login',
  templateUrl: './me.page.html',
  styleUrls: ['./me.page.scss'],
  standalone: true,
  imports: [IonicModule, RouterLink, NgIf, CurrencyPipe, DatePipe, NgForOf],
})
export class MePage implements OnInit {
  user: UserResponseDto | undefined

  constructor(
    private router: Router,
    protected authService: AuthService,
    private appComponent: AppComponent,
    private userService: UserService,
  ) {}

  ngOnInit(): void {
    this.userService.meDetails().subscribe((data) => {
      this.user = data
    })
    if (!this.user) throw new Error('Prueba más tarde')
  }
}