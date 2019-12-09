import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'dls-exams',
        loadChildren: () => import('./dls-exams/dls-exams.module').then(m => m.DlssyncDlsExamsModule)
      },
      {
        path: 'dls-trs-types',
        loadChildren: () => import('./dls-trs-types/dls-trs-types.module').then(m => m.DlssyncDlsTrsTypesModule)
      },
      {
        path: 'dls-vehicle-types',
        loadChildren: () => import('./dls-vehicle-types/dls-vehicle-types.module').then(m => m.DlssyncDlsVehicleTypesModule)
      },
      {
        path: 'dls-vhl-trs-exams',
        loadChildren: () => import('./dls-vhl-trs-exams/dls-vhl-trs-exams.module').then(m => m.DlssyncDlsVhlTrsExamsModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class DlssyncEntityModule {}
