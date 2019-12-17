import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExamQuestions } from 'app/shared/model/exam-questions.model';

@Component({
  selector: 'jhi-exam-questions-detail',
  templateUrl: './exam-questions-detail.component.html'
})
export class ExamQuestionsDetailComponent implements OnInit {
  examQuestions: IExamQuestions;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ examQuestions }) => {
      this.examQuestions = examQuestions;
    });
  }

  previousState() {
    window.history.back();
  }
}
