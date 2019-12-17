import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExam } from 'app/shared/model/exam.model';
import { ExamService } from './exam.service';

@Component({
  templateUrl: './exam-delete-dialog.component.html'
})
export class ExamDeleteDialogComponent {
  exam: IExam;

  constructor(protected examService: ExamService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.examService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'examListModification',
        content: 'Deleted an exam'
      });
      this.activeModal.dismiss(true);
    });
  }
}
