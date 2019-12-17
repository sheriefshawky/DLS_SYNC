import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { DlsReqTrainingsComponent } from './dls-req-trainings.component';
import { DlsReqTrainingsDetailComponent } from './dls-req-trainings-detail.component';
import { DlsReqTrainingsUpdateComponent } from './dls-req-trainings-update.component';
import { DlsReqTrainingsDeleteDialogComponent } from './dls-req-trainings-delete-dialog.component';
import { dlsReqTrainingsRoute } from './dls-req-trainings.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(dlsReqTrainingsRoute)],
  declarations: [
    DlsReqTrainingsComponent,
    DlsReqTrainingsDetailComponent,
    DlsReqTrainingsUpdateComponent,
    DlsReqTrainingsDeleteDialogComponent
  ],
  entryComponents: [DlsReqTrainingsDeleteDialogComponent]
})
export class DlssyncDlsReqTrainingsModule {}
