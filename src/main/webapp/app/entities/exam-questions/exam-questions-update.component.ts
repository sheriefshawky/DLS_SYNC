import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IExamQuestions, ExamQuestions } from 'app/shared/model/exam-questions.model';
import { ExamQuestionsService } from './exam-questions.service';
import { IExam } from 'app/shared/model/exam.model';
import { ExamService } from 'app/entities/exam/exam.service';

@Component({
  selector: 'jhi-exam-questions-update',
  templateUrl: './exam-questions-update.component.html'
})
export class ExamQuestionsUpdateComponent implements OnInit {
  isSaving: boolean;

  exams: IExam[];

  editForm = this.fb.group({
    id: [],
    descAr: [null, [Validators.required]],
    descEn: [null, [Validators.required]],
    code: [null, [Validators.required]],
    imgPath: [],
    timeInSec: [],
    type: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    score: [],
    status: [],
    seq: [],
    categoryId: [],
    questionId: [],
    startAt: [],
    submitAt: [],
    exam: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected examQuestionsService: ExamQuestionsService,
    protected examService: ExamService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ examQuestions }) => {
      this.updateForm(examQuestions);
    });
    this.examService
      .query()
      .subscribe((res: HttpResponse<IExam[]>) => (this.exams = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(examQuestions: IExamQuestions) {
    this.editForm.patchValue({
      id: examQuestions.id,
      descAr: examQuestions.descAr,
      descEn: examQuestions.descEn,
      code: examQuestions.code,
      imgPath: examQuestions.imgPath,
      timeInSec: examQuestions.timeInSec,
      type: examQuestions.type,
      weight: examQuestions.weight,
      score: examQuestions.score,
      status: examQuestions.status,
      seq: examQuestions.seq,
      categoryId: examQuestions.categoryId,
      questionId: examQuestions.questionId,
      startAt: examQuestions.startAt != null ? examQuestions.startAt.format(DATE_TIME_FORMAT) : null,
      submitAt: examQuestions.submitAt != null ? examQuestions.submitAt.format(DATE_TIME_FORMAT) : null,
      exam: examQuestions.exam
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const examQuestions = this.createFromForm();
    if (examQuestions.id !== undefined) {
      this.subscribeToSaveResponse(this.examQuestionsService.update(examQuestions));
    } else {
      this.subscribeToSaveResponse(this.examQuestionsService.create(examQuestions));
    }
  }

  private createFromForm(): IExamQuestions {
    return {
      ...new ExamQuestions(),
      id: this.editForm.get(['id']).value,
      descAr: this.editForm.get(['descAr']).value,
      descEn: this.editForm.get(['descEn']).value,
      code: this.editForm.get(['code']).value,
      imgPath: this.editForm.get(['imgPath']).value,
      timeInSec: this.editForm.get(['timeInSec']).value,
      type: this.editForm.get(['type']).value,
      weight: this.editForm.get(['weight']).value,
      score: this.editForm.get(['score']).value,
      status: this.editForm.get(['status']).value,
      seq: this.editForm.get(['seq']).value,
      categoryId: this.editForm.get(['categoryId']).value,
      questionId: this.editForm.get(['questionId']).value,
      startAt: this.editForm.get(['startAt']).value != null ? moment(this.editForm.get(['startAt']).value, DATE_TIME_FORMAT) : undefined,
      submitAt: this.editForm.get(['submitAt']).value != null ? moment(this.editForm.get(['submitAt']).value, DATE_TIME_FORMAT) : undefined,
      exam: this.editForm.get(['exam']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExamQuestions>>) {
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

  trackExamById(index: number, item: IExam) {
    return item.id;
  }
}
