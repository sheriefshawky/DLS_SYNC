import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';
import { DlsReqTrainingsService } from './dls-req-trainings.service';

@Component({
  templateUrl: './dls-req-trainings-delete-dialog.component.html'
})
export class DlsReqTrainingsDeleteDialogComponent {
  dlsReqTrainings: IDlsReqTrainings;

  constructor(
    protected dlsReqTrainingsService: DlsReqTrainingsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dlsReqTrainingsService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dlsReqTrainingsListModification',
        content: 'Deleted an dlsReqTrainings'
      });
      this.activeModal.dismiss(true);
    });
  }
}
