import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQuestions } from 'app/shared/model/questions.model';
import { QuestionsService } from './questions.service';

@Component({
  templateUrl: './questions-delete-dialog.component.html'
})
export class QuestionsDeleteDialogComponent {
  questions: IQuestions;

  constructor(protected questionsService: QuestionsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.questionsService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'questionsListModification',
        content: 'Deleted an questions'
      });
      this.activeModal.dismiss(true);
    });
  }
}
