import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { DlsRequestsComponent } from './dls-requests.component';
import { DlsRequestsDetailComponent } from './dls-requests-detail.component';
import { DlsRequestsUpdateComponent } from './dls-requests-update.component';
import { DlsRequestsDeleteDialogComponent } from './dls-requests-delete-dialog.component';
import { dlsRequestsRoute } from './dls-requests.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(dlsRequestsRoute)],
  declarations: [DlsRequestsComponent, DlsRequestsDetailComponent, DlsRequestsUpdateComponent, DlsRequestsDeleteDialogComponent],
  entryComponents: [DlsRequestsDeleteDialogComponent]
})
export class DlssyncDlsRequestsModule {}
