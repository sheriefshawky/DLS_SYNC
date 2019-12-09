import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';
import { DlsVhlTrsExamsService } from './dls-vhl-trs-exams.service';

@Component({
  templateUrl: './dls-vhl-trs-exams-delete-dialog.component.html'
})
export class DlsVhlTrsExamsDeleteDialogComponent {
  dlsVhlTrsExams: IDlsVhlTrsExams;

  constructor(
    protected dlsVhlTrsExamsService: DlsVhlTrsExamsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dlsVhlTrsExamsService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dlsVhlTrsExamsListModification',
        content: 'Deleted an dlsVhlTrsExams'
      });
      this.activeModal.dismiss(true);
    });
  }
}
