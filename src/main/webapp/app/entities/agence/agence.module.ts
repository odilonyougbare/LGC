import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LgcSharedModule } from 'app/shared/shared.module';
import { AgenceComponent } from './agence.component';
import { AgenceDetailComponent } from './agence-detail.component';
import { AgenceUpdateComponent } from './agence-update.component';
import { AgenceDeleteDialogComponent } from './agence-delete-dialog.component';
import { agenceRoute } from './agence.route';
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";

@NgModule({
  imports: [LgcSharedModule, RouterModule.forChild(agenceRoute), ButtonModule, RippleModule],
  declarations: [AgenceComponent, AgenceDetailComponent, AgenceUpdateComponent, AgenceDeleteDialogComponent],
  entryComponents: [AgenceDeleteDialogComponent],
})
export class LgcAgenceModule {}
