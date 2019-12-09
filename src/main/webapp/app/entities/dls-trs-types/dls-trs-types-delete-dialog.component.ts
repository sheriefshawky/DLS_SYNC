import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDlsTrsTypes } from 'app/shared/model/dls-trs-types.model';
import { DlsTrsTypesService } from './dls-trs-types.service';

@Component({
  templateUrl: './dls-trs-types-delete-dialog.component.html'
})
export class DlsTrsTypesDeleteDialogComponent {
  dlsTrsTypes: IDlsTrsTypes;

  constructor(
    protected dlsTrsTypesService: DlsTrsTypesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dlsTrsTypesService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dlsTrsTypesListModification',
        content: 'Deleted an dlsTrsTypes'
      });
      this.activeModal.dismiss(true);
    });
  }
}
