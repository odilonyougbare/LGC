import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { LgcSharedModule } from 'app/shared/shared.module';
import { LgcCoreModule } from 'app/core/core.module';
import { LgcAppRoutingModule } from './app-routing.module';
import { LgcHomeModule } from './home/home.module';
import { LgcEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import {CommonModule} from "@angular/common";
import {TableModule} from "primeng/table";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    LgcSharedModule,
    LgcCoreModule,
    LgcHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    LgcEntityModule,
    LgcAppRoutingModule,
    CommonModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class LgcAppModule {}
