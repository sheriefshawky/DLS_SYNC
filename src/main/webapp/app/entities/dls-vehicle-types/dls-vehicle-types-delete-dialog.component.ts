import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';
import { DlsVehicleTypesService } from './dls-vehicle-types.service';

@Component({
  templateUrl: './dls-vehicle-types-delete-dialog.component.html'
})
export class DlsVehicleTypesDeleteDialogComponent {
  dlsVehicleTypes: IDlsVehicleTypes;

  constructor(
    protected dlsVehicleTypesService: DlsVehicleTypesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dlsVehicleTypesService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dlsVehicleTypesListModification',
        content: 'Deleted an dlsVehicleTypes'
      });
      this.activeModal.dismiss(true);
    });
  }
}
