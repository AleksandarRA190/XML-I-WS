import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { HotelsComponent } from './hotels/hotels.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserReservationsComponent } from './user-reservations/user-reservations.component';
import { HomeComponent } from './home/home.component';
import { UserEditProfileComponent } from './user-edit-profile/user-edit-profile.component';
import { ConfirmationPageComponent } from './confirmation-page/confirmation-page.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { PostCommentComponent } from './post-comment/post-comment.component';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login',component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'accomodations', component: HotelsComponent },
  {path: 'profile', component: UserProfileComponent },
  {path: 'edit', component:UserEditProfileComponent},
  {path: 'activate/:id', component:ConfirmationPageComponent},
  {path: 'changePassword', component:ChangePasswordComponent},
  {path: 'postComment/:id', component:PostCommentComponent},
  {path: 'reservations', component:UserReservationsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
