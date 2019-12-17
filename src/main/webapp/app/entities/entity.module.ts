import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'dls-persons',
        loadChildren: () => import('./dls-persons/dls-persons.module').then(m => m.DlssyncDlsPersonsModule)
      },
      {
        path: 'dls-requests',
        loadChildren: () => import('./dls-requests/dls-requests.module').then(m => m.DlssyncDlsRequestsModule)
      },
      {
        path: 'dls-req-exams',
        loadChildren: () => import('./dls-req-exams/dls-req-exams.module').then(m => m.DlssyncDlsReqExamsModule)
      },
      {
        path: 'dls-req-trainings',
        loadChildren: () => import('./dls-req-trainings/dls-req-trainings.module').then(m => m.DlssyncDlsReqTrainingsModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class DlssyncEntityModule {}
