import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { DlsTrsTypesComponent } from './dls-trs-types.component';
import { DlsTrsTypesDetailComponent } from './dls-trs-types-detail.component';
import { DlsTrsTypesUpdateComponent } from './dls-trs-types-update.component';
import { DlsTrsTypesDeleteDialogComponent } from './dls-trs-types-delete-dialog.component';
import { dlsTrsTypesRoute } from './dls-trs-types.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(dlsTrsTypesRoute)],
  declarations: [DlsTrsTypesComponent, DlsTrsTypesDetailComponent, DlsTrsTypesUpdateComponent, DlsTrsTypesDeleteDialogComponent],
  entryComponents: [DlsTrsTypesDeleteDialogComponent]
})
export class DlssyncDlsTrsTypesModule {}
