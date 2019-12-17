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
import { IExam, Exam } from 'app/shared/model/exam.model';
import { ExamService } from './exam.service';
import { ITemplate } from 'app/shared/model/template.model';
import { TemplateService } from 'app/entities/template/template.service';

@Component({
  selector: 'jhi-exam-update',
  templateUrl: './exam-update.component.html'
})
export class ExamUpdateComponent implements OnInit {
  isSaving: boolean;

  templates: ITemplate[];

  editForm = this.fb.group({
    id: [],
    validfrom: [null, [Validators.required]],
    validto: [null, [Validators.required]],
    timeInSec: [],
    score: [],
    status: [null, [Validators.required]],
    passScore: [],
    startAt: [],
    submitAt: [],
    template: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected examService: ExamService,
    protected templateService: TemplateService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ exam }) => {
      this.updateForm(exam);
    });
    this.templateService.query({ filter: 'exam-is-null' }).subscribe(
      (res: HttpResponse<ITemplate[]>) => {
        if (!this.editForm.get('template').value || !this.editForm.get('template').value.id) {
          this.templates = res.body;
        } else {
          this.templateService
            .find(this.editForm.get('template').value.id)
            .subscribe(
              (subRes: HttpResponse<ITemplate>) => (this.templates = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(exam: IExam) {
    this.editForm.patchValue({
      id: exam.id,
      validfrom: exam.validfrom != null ? exam.validfrom.format(DATE_TIME_FORMAT) : null,
      validto: exam.validto != null ? exam.validto.format(DATE_TIME_FORMAT) : null,
      timeInSec: exam.timeInSec,
      score: exam.score,
      status: exam.status,
      passScore: exam.passScore,
      startAt: exam.startAt != null ? exam.startAt.format(DATE_TIME_FORMAT) : null,
      submitAt: exam.submitAt != null ? exam.submitAt.format(DATE_TIME_FORMAT) : null,
      template: exam.template
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const exam = this.createFromForm();
    if (exam.id !== undefined) {
      this.subscribeToSaveResponse(this.examService.update(exam));
    } else {
      this.subscribeToSaveResponse(this.examService.create(exam));
    }
  }

  private createFromForm(): IExam {
    return {
      ...new Exam(),
      id: this.editForm.get(['id']).value,
      validfrom:
        this.editForm.get(['validfrom']).value != null ? moment(this.editForm.get(['validfrom']).value, DATE_TIME_FORMAT) : undefined,
      validto: this.editForm.get(['validto']).value != null ? moment(this.editForm.get(['validto']).value, DATE_TIME_FORMAT) : undefined,
      timeInSec: this.editForm.get(['timeInSec']).value,
      score: this.editForm.get(['score']).value,
      status: this.editForm.get(['status']).value,
      passScore: this.editForm.get(['passScore']).value,
      startAt: this.editForm.get(['startAt']).value != null ? moment(this.editForm.get(['startAt']).value, DATE_TIME_FORMAT) : undefined,
      submitAt: this.editForm.get(['submitAt']).value != null ? moment(this.editForm.get(['submitAt']).value, DATE_TIME_FORMAT) : undefined,
      template: this.editForm.get(['template']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExam>>) {
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

  trackTemplateById(index: number, item: ITemplate) {
    return item.id;
  }
}
