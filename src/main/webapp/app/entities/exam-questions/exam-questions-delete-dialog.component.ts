import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExamQuestions } from 'app/shared/model/exam-questions.model';
import { ExamQuestionsService } from './exam-questions.service';

@Component({
  templateUrl: './exam-questions-delete-dialog.component.html'
})
export class ExamQuestionsDeleteDialogComponent {
  examQuestions: IExamQuestions;

  constructor(
    protected examQuestionsService: ExamQuestionsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.examQuestionsService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'examQuestionsListModification',
        content: 'Deleted an examQuestions'
      });
      this.activeModal.dismiss(true);
    });
  }
}
