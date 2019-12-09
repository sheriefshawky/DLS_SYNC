import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDlsExams } from 'app/shared/model/dls-exams.model';
import { DlsExamsService } from './dls-exams.service';

@Component({
  templateUrl: './dls-exams-delete-dialog.component.html'
})
export class DlsExamsDeleteDialogComponent {
  dlsExams: IDlsExams;

  constructor(protected dlsExamsService: DlsExamsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dlsExamsService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dlsExamsListModification',
        content: 'Deleted an dlsExams'
      });
      this.activeModal.dismiss(true);
    });
  }
}
