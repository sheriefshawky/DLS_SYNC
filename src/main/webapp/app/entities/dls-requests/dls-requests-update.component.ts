import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IDlsRequests, DlsRequests } from 'app/shared/model/dls-requests.model';
import { DlsRequestsService } from './dls-requests.service';
import { IDlsPersons } from 'app/shared/model/dls-persons.model';
import { DlsPersonsService } from 'app/entities/dls-persons/dls-persons.service';

@Component({
  selector: 'jhi-dls-requests-update',
  templateUrl: './dls-requests-update.component.html'
})
export class DlsRequestsUpdateComponent implements OnInit {
  isSaving: boolean;

  dlspersons: IDlsPersons[];

  editForm = this.fb.group({
    id: [],
    transactionType: [null, [Validators.required]],
    requestNo: [],
    exported: [],
    dlsPersons: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected dlsRequestsService: DlsRequestsService,
    protected dlsPersonsService: DlsPersonsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsRequests }) => {
      this.updateForm(dlsRequests);
    });
    this.dlsPersonsService
      .query()
      .subscribe((res: HttpResponse<IDlsPersons[]>) => (this.dlspersons = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(dlsRequests: IDlsRequests) {
    this.editForm.patchValue({
      id: dlsRequests.id,
      transactionType: dlsRequests.transactionType,
      requestNo: dlsRequests.requestNo,
      exported: dlsRequests.exported,
      dlsPersons: dlsRequests.dlsPersons
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dlsRequests = this.createFromForm();
    if (dlsRequests.id !== undefined) {
      this.subscribeToSaveResponse(this.dlsRequestsService.update(dlsRequests));
    } else {
      this.subscribeToSaveResponse(this.dlsRequestsService.create(dlsRequests));
    }
  }

  private createFromForm(): IDlsRequests {
    return {
      ...new DlsRequests(),
      id: this.editForm.get(['id']).value,
      transactionType: this.editForm.get(['transactionType']).value,
      requestNo: this.editForm.get(['requestNo']).value,
      exported: this.editForm.get(['exported']).value,
      dlsPersons: this.editForm.get(['dlsPersons']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDlsRequests>>) {
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

  trackDlsPersonsById(index: number, item: IDlsPersons) {
    return item.id;
  }
}
