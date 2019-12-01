import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDlsRequests } from 'app/shared/model/dls-requests.model';
import { DlsRequestsService } from './dls-requests.service';

@Component({
  templateUrl: './dls-requests-delete-dialog.component.html'
})
export class DlsRequestsDeleteDialogComponent {
  dlsRequests: IDlsRequests;

  constructor(
    protected dlsRequestsService: DlsRequestsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dlsRequestsService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dlsRequestsListModification',
        content: 'Deleted an dlsRequests'
      });
      this.activeModal.dismiss(true);
    });
  }
}
