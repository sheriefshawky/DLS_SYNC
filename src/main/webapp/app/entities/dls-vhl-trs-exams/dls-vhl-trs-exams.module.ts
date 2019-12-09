import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { DlsVhlTrsExamsComponent } from './dls-vhl-trs-exams.component';
import { DlsVhlTrsExamsDetailComponent } from './dls-vhl-trs-exams-detail.component';
import { DlsVhlTrsExamsUpdateComponent } from './dls-vhl-trs-exams-update.component';
import { DlsVhlTrsExamsDeleteDialogComponent } from './dls-vhl-trs-exams-delete-dialog.component';
import { dlsVhlTrsExamsRoute } from './dls-vhl-trs-exams.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(dlsVhlTrsExamsRoute)],
  declarations: [
    DlsVhlTrsExamsComponent,
    DlsVhlTrsExamsDetailComponent,
    DlsVhlTrsExamsUpdateComponent,
    DlsVhlTrsExamsDeleteDialogComponent
  ],
  entryComponents: [DlsVhlTrsExamsDeleteDialogComponent]
})
export class DlssyncDlsVhlTrsExamsModule {}
