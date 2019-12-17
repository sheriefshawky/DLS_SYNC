import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAnswers } from 'app/shared/model/answers.model';
import { AnswersService } from './answers.service';

@Component({
  templateUrl: './answers-delete-dialog.component.html'
})
export class AnswersDeleteDialogComponent {
  answers: IAnswers;

  constructor(protected answersService: AnswersService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.answersService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'answersListModification',
        content: 'Deleted an answers'
      });
      this.activeModal.dismiss(true);
    });
  }
}
