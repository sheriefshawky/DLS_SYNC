import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';

@Component({
  selector: 'jhi-exam-question-answers-detail',
  templateUrl: './exam-question-answers-detail.component.html'
})
export class ExamQuestionAnswersDetailComponent implements OnInit {
  examQuestionAnswers: IExamQuestionAnswers;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ examQuestionAnswers }) => {
      this.examQuestionAnswers = examQuestionAnswers;
    });
  }

  previousState() {
    window.history.back();
  }
}
