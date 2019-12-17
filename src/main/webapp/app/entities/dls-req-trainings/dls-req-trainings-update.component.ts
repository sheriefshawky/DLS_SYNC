import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IDlsReqTrainings, DlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';
import { DlsReqTrainingsService } from './dls-req-trainings.service';
import { IDlsRequests } from 'app/shared/model/dls-requests.model';
import { DlsRequestsService } from 'app/entities/dls-requests/dls-requests.service';

@Component({
  selector: 'jhi-dls-req-trainings-update',
  templateUrl: './dls-req-trainings-update.component.html'
})
export class DlsReqTrainingsUpdateComponent implements OnInit {
  isSaving: boolean;

  dlsrequests: IDlsRequests[];

  editForm = this.fb.group({
    id: [],
    trainingCode: [null, [Validators.required]],
    trainingLectures: [],
    trainingfulfilled: [],
    exported: [],
    dlsRequests: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected dlsReqTrainingsService: DlsReqTrainingsService,
    protected dlsRequestsService: DlsRequestsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsReqTrainings }) => {
      this.updateForm(dlsReqTrainings);
    });
    this.dlsRequestsService
      .query()
      .subscribe(
        (res: HttpResponse<IDlsRequests[]>) => (this.dlsrequests = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(dlsReqTrainings: IDlsReqTrainings) {
    this.editForm.patchValue({
      id: dlsReqTrainings.id,
      trainingCode: dlsReqTrainings.trainingCode,
      trainingLectures: dlsReqTrainings.trainingLectures,
      trainingfulfilled: dlsReqTrainings.trainingfulfilled,
      exported: dlsReqTrainings.exported,
      dlsRequests: dlsReqTrainings.dlsRequests
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dlsReqTrainings = this.createFromForm();
    if (dlsReqTrainings.id !== undefined) {
      this.subscribeToSaveResponse(this.dlsReqTrainingsService.update(dlsReqTrainings));
    } else {
      this.subscribeToSaveResponse(this.dlsReqTrainingsService.create(dlsReqTrainings));
    }
  }

  private createFromForm(): IDlsReqTrainings {
    return {
      ...new DlsReqTrainings(),
      id: this.editForm.get(['id']).value,
      trainingCode: this.editForm.get(['trainingCode']).value,
      trainingLectures: this.editForm.get(['trainingLectures']).value,
      trainingfulfilled: this.editForm.get(['trainingfulfilled']).value,
      exported: this.editForm.get(['exported']).value,
      dlsRequests: this.editForm.get(['dlsRequests']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDlsReqTrainings>>) {
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
