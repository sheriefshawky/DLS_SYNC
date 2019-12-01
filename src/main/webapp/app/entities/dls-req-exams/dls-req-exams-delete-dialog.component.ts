import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDlsReqExams } from 'app/shared/model/dls-req-exams.model';
import { DlsReqExamsService } from './dls-req-exams.service';

@Component({
  templateUrl: './dls-req-exams-delete-dialog.component.html'
})
export class DlsReqExamsDeleteDialogComponent {
  dlsReqExams: IDlsReqExams;

  constructor(
    protected dlsReqExamsService: DlsReqExamsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dlsReqExamsService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dlsReqExamsListModification',
        content: 'Deleted an dlsReqExams'
      });
      this.activeModal.dismiss(true);
    });
  }
}
