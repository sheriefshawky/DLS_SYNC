import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';
import { ExamQuestionAnswersService } from './exam-question-answers.service';

@Component({
  templateUrl: './exam-question-answers-delete-dialog.component.html'
})
export class ExamQuestionAnswersDeleteDialogComponent {
  examQuestionAnswers: IExamQuestionAnswers;

  constructor(
    protected examQuestionAnswersService: ExamQuestionAnswersService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.examQuestionAnswersService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'examQuestionAnswersListModification',
        content: 'Deleted an examQuestionAnswers'
      });
      this.activeModal.dismiss(true);
    });
  }
}
