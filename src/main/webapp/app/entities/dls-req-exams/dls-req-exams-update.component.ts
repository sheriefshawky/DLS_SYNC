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
import { IDlsReqExams, DlsReqExams } from 'app/shared/model/dls-req-exams.model';
import { DlsReqExamsService } from './dls-req-exams.service';
import { IDlsRequests } from 'app/shared/model/dls-requests.model';
import { DlsRequestsService } from 'app/entities/dls-requests/dls-requests.service';

@Component({
  selector: 'jhi-dls-req-exams-update',
  templateUrl: './dls-req-exams-update.component.html'
})
export class DlsReqExamsUpdateComponent implements OnInit {
  isSaving: boolean;

  dlsrequests: IDlsRequests[];

  editForm = this.fb.group({
    id: [],
    examCode: [null, [Validators.required]],
    examResult: [],
    examDate: [],
    examGrade: [],
    isEligibile: [],
    exported: [],
    dlsRequests: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected dlsReqExamsService: DlsReqExamsService,
    protected dlsRequestsService: DlsRequestsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsReqExams }) => {
      this.updateForm(dlsReqExams);
    });
    this.dlsRequestsService
      .query()
      .subscribe(
        (res: HttpResponse<IDlsRequests[]>) => (this.dlsrequests = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(dlsReqExams: IDlsReqExams) {
    this.editForm.patchValue({
      id: dlsReqExams.id,
      examCode: dlsReqExams.examCode,
      examResult: dlsReqExams.examResult,
      examDate: dlsReqExams.examDate != null ? dlsReqExams.examDate.format(DATE_TIME_FORMAT) : null,
      examGrade: dlsReqExams.examGrade,
      isEligibile: dlsReqExams.isEligibile,
      exported: dlsReqExams.exported,
      dlsRequests: dlsReqExams.dlsRequests
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dlsReqExams = this.createFromForm();
    if (dlsReqExams.id !== undefined) {
      this.subscribeToSaveResponse(this.dlsReqExamsService.update(dlsReqExams));
    } else {
      this.subscribeToSaveResponse(this.dlsReqExamsService.create(dlsReqExams));
    }
  }

  private createFromForm(): IDlsReqExams {
    return {
      ...new DlsReqExams(),
      id: this.editForm.get(['id']).value,
      examCode: this.editForm.get(['examCode']).value,
      examResult: this.editForm.get(['examResult']).value,
      examDate: this.editForm.get(['examDate']).value != null ? moment(this.editForm.get(['examDate']).value, DATE_TIME_FORMAT) : undefined,
      examGrade: this.editForm.get(['examGrade']).value,
      isEligibile: this.editForm.get(['isEligibile']).value,
      exported: this.editForm.get(['exported']).value,
      dlsRequests: this.editForm.get(['dlsRequests']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDlsReqExams>>) {
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

  trackDlsRequestsById(index: number, item: IDlsRequests) {
    return item.id;
  }
}
