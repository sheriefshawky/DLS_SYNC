import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { DlsExamsComponent } from './dls-exams.component';
import { DlsExamsDetailComponent } from './dls-exams-detail.component';
import { DlsExamsUpdateComponent } from './dls-exams-update.component';
import { DlsExamsDeleteDialogComponent } from './dls-exams-delete-dialog.component';
import { dlsExamsRoute } from './dls-exams.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(dlsExamsRoute)],
  declarations: [DlsExamsComponent, DlsExamsDetailComponent, DlsExamsUpdateComponent, DlsExamsDeleteDialogComponent],
  entryComponents: [DlsExamsDeleteDialogComponent]
})
export class DlssyncDlsExamsModule {}
