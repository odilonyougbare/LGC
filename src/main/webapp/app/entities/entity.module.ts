import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.LgcClientModule),
      },
      {
        path: 'agence',
        loadChildren: () => import('./agence/agence.module').then(m => m.LgcAgenceModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class LgcEntityModule {}
