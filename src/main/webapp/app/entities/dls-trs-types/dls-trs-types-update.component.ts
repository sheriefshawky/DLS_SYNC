import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDlsTrsTypes, DlsTrsTypes } from 'app/shared/model/dls-trs-types.model';
import { DlsTrsTypesService } from './dls-trs-types.service';

@Component({
  selector: 'jhi-dls-trs-types-update',
  templateUrl: './dls-trs-types-update.component.html'
})
export class DlsTrsTypesUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    nameAr: [null, [Validators.required]],
    nameEn: [null, [Validators.required]]
  });

  constructor(protected dlsTrsTypesService: DlsTrsTypesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsTrsTypes }) => {
      this.updateForm(dlsTrsTypes);
    });
  }

  updateForm(dlsTrsTypes: IDlsTrsTypes) {
    this.editForm.patchValue({
      id: dlsTrsTypes.id,
      code: dlsTrsTypes.code,
      nameAr: dlsTrsTypes.nameAr,
      nameEn: dlsTrsTypes.nameEn
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dlsTrsTypes = this.createFromForm();
    if (dlsTrsTypes.id !== undefined) {
      this.subscribeToSaveResponse(this.dlsTrsTypesService.update(dlsTrsTypes));
    } else {
      this.subscribeToSaveResponse(this.dlsTrsTypesService.create(dlsTrsTypes));
    }
  }

  private createFromForm(): IDlsTrsTypes {
    return {
      ...new DlsTrsTypes(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      nameAr: this.editForm.get(['nameAr']).value,
      nameEn: this.editForm.get(['nameEn']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDlsTrsTypes>>) {
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
