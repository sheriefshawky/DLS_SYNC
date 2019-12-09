import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { DlsVehicleTypesComponent } from './dls-vehicle-types.component';
import { DlsVehicleTypesDetailComponent } from './dls-vehicle-types-detail.component';
import { DlsVehicleTypesUpdateComponent } from './dls-vehicle-types-update.component';
import { DlsVehicleTypesDeleteDialogComponent } from './dls-vehicle-types-delete-dialog.component';
import { dlsVehicleTypesRoute } from './dls-vehicle-types.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(dlsVehicleTypesRoute)],
  declarations: [
    DlsVehicleTypesComponent,
    DlsVehicleTypesDetailComponent,
    DlsVehicleTypesUpdateComponent,
    DlsVehicleTypesDeleteDialogComponent
  ],
  entryComponents: [DlsVehicleTypesDeleteDialogComponent]
})
export class DlssyncDlsVehicleTypesModule {}
