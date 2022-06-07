import { NgModule } from '@angular/core';
import { LgcSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import {ButtonModule} from "primeng/button";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import { DynamicDialogModule} from "primeng/dynamicdialog";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {BrowserModule} from "@angular/platform-browser";
import {CommonModule} from "@angular/common";
import {InputTextModule} from "primeng/inputtext";
import {KeyFilterModule} from "primeng/keyfilter";
import {ConfirmDialogModule} from "primeng/confirmdialog";


@NgModule({
  imports: [LgcSharedLibsModule, ButtonModule, TableModule, ToastModule,ConfirmDialogModule, DynamicDialogModule, InputTextModule, KeyFilterModule],
  declarations: [FindLanguageFromKeyPipe, AlertComponent, AlertErrorComponent, LoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [LoginModalComponent],
  exports: [
    LgcSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
  ],
})
export class LgcSharedModule {}
