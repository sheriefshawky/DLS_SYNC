import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDlsVehicleTypes, DlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';
import { DlsVehicleTypesService } from './dls-vehicle-types.service';

@Component({
  selector: 'jhi-dls-vehicle-types-update',
  templateUrl: './dls-vehicle-types-update.component.html'
})
export class DlsVehicleTypesUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    nameAr: [null, [Validators.required]],
    nameEn: [null, [Validators.required]]
  });

  constructor(
    protected dlsVehicleTypesService: DlsVehicleTypesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsVehicleTypes }) => {
      this.updateForm(dlsVehicleTypes);
    });
  }

  updateForm(dlsVehicleTypes: IDlsVehicleTypes) {
    this.editForm.patchValue({
      id: dlsVehicleTypes.id,
      code: dlsVehicleTypes.code,
      nameAr: dlsVehicleTypes.nameAr,
      nameEn: dlsVehicleTypes.nameEn
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dlsVehicleTypes = this.createFromForm();
    if (dlsVehicleTypes.id !== undefined) {
      this.subscribeToSaveResponse(this.dlsVehicleTypesService.update(dlsVehicleTypes));
    } else {
      this.subscribeToSaveResponse(this.dlsVehicleTypesService.create(dlsVehicleTypes));
    }
  }

  private createFromForm(): IDlsVehicleTypes {
    return {
      ...new DlsVehicleTypes(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      nameAr: this.editForm.get(['nameAr']).value,
      nameEn: this.editForm.get(['nameEn']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDlsVehicleTypes>>) {
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
