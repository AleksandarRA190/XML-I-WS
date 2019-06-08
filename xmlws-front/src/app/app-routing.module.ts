import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { HotelsComponent } from './hotels/hotels.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserReservationsComponent } from './user-reservations/user-reservations.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login',component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'accomodations', component: HotelsComponent },
  {path: 'profile', component: UserProfileComponent },
  {path: 'reservations', component: UserReservationsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
