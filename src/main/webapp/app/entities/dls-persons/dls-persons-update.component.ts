import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDlsPersons, DlsPersons } from 'app/shared/model/dls-persons.model';
import { DlsPersonsService } from './dls-persons.service';

@Component({
  selector: 'jhi-dls-persons-update',
  templateUrl: './dls-persons-update.component.html'
})
export class DlsPersonsUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    fullName: [null, [Validators.required]],
    mobileNo: [null, [Validators.required]],
    licenseCategory: [null, [Validators.required]],
    nationalId: [],
    passportKey: [],
    transactionType: [null, [Validators.required]],
    exported: []
  });

  constructor(protected dlsPersonsService: DlsPersonsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsPersons }) => {
      this.updateForm(dlsPersons);
    });
  }

  updateForm(dlsPersons: IDlsPersons) {
    this.editForm.patchValue({
      id: dlsPersons.id,
      fullName: dlsPersons.fullName,
      mobileNo: dlsPersons.mobileNo,
      licenseCategory: dlsPersons.licenseCategory,
      nationalId: dlsPersons.nationalId,
      passportKey: dlsPersons.passportKey,
      transactionType: dlsPersons.transactionType,
      exported: dlsPersons.exported
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dlsPersons = this.createFromForm();
    if (dlsPersons.id !== undefined) {
      this.subscribeToSaveResponse(this.dlsPersonsService.update(dlsPersons));
    } else {
      this.subscribeToSaveResponse(this.dlsPersonsService.create(dlsPersons));
    }
  }

  private createFromForm(): IDlsPersons {
    return {
      ...new DlsPersons(),
      id: this.editForm.get(['id']).value,
      fullName: this.editForm.get(['fullName']).value,
      mobileNo: this.editForm.get(['mobileNo']).value,
      licenseCategory: this.editForm.get(['licenseCategory']).value,
      nationalId: this.editForm.get(['nationalId']).value,
      passportKey: this.editForm.get(['passportKey']).value,
      transactionType: this.editForm.get(['transactionType']).value,
      exported: this.editForm.get(['exported']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDlsPersons>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
