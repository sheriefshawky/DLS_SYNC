import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { DlsReqExamsComponent } from './dls-req-exams.component';
import { DlsReqExamsDetailComponent } from './dls-req-exams-detail.component';
import { DlsReqExamsUpdateComponent } from './dls-req-exams-update.component';
import { DlsReqExamsDeleteDialogComponent } from './dls-req-exams-delete-dialog.component';
import { dlsReqExamsRoute } from './dls-req-exams.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(dlsReqExamsRoute)],
  declarations: [DlsReqExamsComponent, DlsReqExamsDetailComponent, DlsReqExamsUpdateComponent, DlsReqExamsDeleteDialogComponent],
  entryComponents: [DlsReqExamsDeleteDialogComponent]
})
export class DlssyncDlsReqExamsModule {}
