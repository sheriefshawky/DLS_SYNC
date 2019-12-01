import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { DlsPersonsComponent } from './dls-persons.component';
import { DlsPersonsDetailComponent } from './dls-persons-detail.component';
import { DlsPersonsUpdateComponent } from './dls-persons-update.component';
import { DlsPersonsDeleteDialogComponent } from './dls-persons-delete-dialog.component';
import { dlsPersonsRoute } from './dls-persons.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(dlsPersonsRoute)],
  declarations: [DlsPersonsComponent, DlsPersonsDetailComponent, DlsPersonsUpdateComponent, DlsPersonsDeleteDialogComponent],
  entryComponents: [DlsPersonsDeleteDialogComponent]
})
export class DlssyncDlsPersonsModule {}
