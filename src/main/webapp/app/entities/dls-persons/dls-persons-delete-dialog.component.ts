import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDlsPersons } from 'app/shared/model/dls-persons.model';
import { DlsPersonsService } from './dls-persons.service';

@Component({
  templateUrl: './dls-persons-delete-dialog.component.html'
})
export class DlsPersonsDeleteDialogComponent {
  dlsPersons: IDlsPersons;

  constructor(
    protected dlsPersonsService: DlsPersonsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dlsPersonsService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dlsPersonsListModification',
        content: 'Deleted an dlsPersons'
      });
      this.activeModal.dismiss(true);
    });
  }
}
