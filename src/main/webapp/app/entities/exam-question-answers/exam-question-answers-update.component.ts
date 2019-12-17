import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IExamQuestionAnswers, ExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';
import { ExamQuestionAnswersService } from './exam-question-answers.service';
import { IExamQuestions } from 'app/shared/model/exam-questions.model';
import { ExamQuestionsService } from 'app/entities/exam-questions/exam-questions.service';

@Component({
  selector: 'jhi-exam-question-answers-update',
  templateUrl: './exam-question-answers-update.component.html'
})
export class ExamQuestionAnswersUpdateComponent implements OnInit {
  isSaving: boolean;

  examquestions: IExamQuestions[];

  editForm = this.fb.group({
    id: [],
    descAr: [null, [Validators.required]],
    descEn: [null, [Validators.required]],
    code: [null, [Validators.required]],
    imgPath: [],
    isRightAnswer: [],
    status: [],
    answerId: [],
    examQuestions: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected examQuestionAnswersService: ExamQuestionAnswersService,
    protected examQuestionsService: ExamQuestionsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ examQuestionAnswers }) => {
      this.updateForm(examQuestionAnswers);
    });
    this.examQuestionsService
      .query()
      .subscribe(
        (res: HttpResponse<IExamQuestions[]>) => (this.examquestions = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(examQuestionAnswers: IExamQuestionAnswers) {
    this.editForm.patchValue({
      id: examQuestionAnswers.id,
      descAr: examQuestionAnswers.descAr,
      descEn: examQuestionAnswers.descEn,
      code: examQuestionAnswers.code,
      imgPath: examQuestionAnswers.imgPath,
      isRightAnswer: examQuestionAnswers.isRightAnswer,
      status: examQuestionAnswers.status,
      answerId: examQuestionAnswers.answerId,
      examQuestions: examQuestionAnswers.examQuestions
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const examQuestionAnswers = this.createFromForm();
    if (examQuestionAnswers.id !== undefined) {
      this.subscribeToSaveResponse(this.examQuestionAnswersService.update(examQuestionAnswers));
    } else {
      this.subscribeToSaveResponse(this.examQuestionAnswersService.create(examQuestionAnswers));
    }
  }

  private createFromForm(): IExamQuestionAnswers {
    return {
      ...new ExamQuestionAnswers(),
      id: this.editForm.get(['id']).value,
      descAr: this.editForm.get(['descAr']).value,
      descEn: this.editForm.get(['descEn']).value,
      code: this.editForm.get(['code']).value,
      imgPath: this.editForm.get(['imgPath']).value,
      isRightAnswer: this.editForm.get(['isRightAnswer']).value,
      status: this.editForm.get(['status']).value,
      answerId: this.editForm.get(['answerId']).value,
      examQuestions: this.editForm.get(['examQuestions']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExamQuestionAnswers>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackExamQuestionsById(index: number, item: IExamQuestions) {
    return item.id;
  }
}
