import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LgcSharedModule } from 'app/shared/shared.module';
import { ClientComponent } from './client.component';
import { ClientDetailComponent } from './client-detail.component';
import { ClientUpdateComponent } from './client-update.component';
import { ClientDeleteDialogComponent } from './client-delete-dialog.component';
import { clientRoute } from './client.route';
import {ButtonModule} from "primeng/button";
import {TableModule} from "primeng/table";
import {CommonModule} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastModule} from "primeng/toast";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {RippleModule} from "primeng/ripple";
import {DialogModule} from "primeng/dialog";
import {InputTextModule} from "primeng/inputtext";
import {ToolbarModule} from "primeng/toolbar";
import {FileUploadModule} from "primeng/fileupload";

@NgModule({
  imports: [LgcSharedModule, RouterModule.forChild(clientRoute), ButtonModule, TableModule, ToastModule, ConfirmDialogModule, RippleModule, DialogModule, InputTextModule, ToolbarModule, FileUploadModule],
  declarations: [ClientComponent, ClientDetailComponent, ClientUpdateComponent, ClientDeleteDialogComponent],
  entryComponents: [ClientDeleteDialogComponent],
})
export class LgcClientModule {}
