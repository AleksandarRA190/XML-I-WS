import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient } from '@angular/common/http'; 
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
import { ConversationComponent } from './conversation/conversation.component';
import { OneConversationComponent } from './conversation/one-conversation/one-conversation.component';
import { CommunicationService } from './communication.service';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    HotelsComponent,
    UserProfileComponent,
    UserReservationsComponent,
    HomeComponent,
    UserEditProfileComponent,
    ConfirmationPageComponent,
    ChangePasswordComponent,
    PostCommentComponent,
    ConversationComponent,
    OneConversationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    CommunicationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
