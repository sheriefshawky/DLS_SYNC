import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IDlsVehicleTypes, DlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';
import { DlsVehicleTypesService } from './dls-vehicle-types.service';
import { IDlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';
import { DlsVhlTrsExamsService } from 'app/entities/dls-vhl-trs-exams/dls-vhl-trs-exams.service';

@Component({
  selector: 'jhi-dls-vehicle-types-update',
  templateUrl: './dls-vehicle-types-update.component.html'
})
export class DlsVehicleTypesUpdateComponent implements OnInit {
  isSaving: boolean;

  dlsvhltrsexams: IDlsVhlTrsExams[];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    nameAr: [null, [Validators.required]],
    nameEn: [null, [Validators.required]],
    dlsVhlTrsExams: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected dlsVehicleTypesService: DlsVehicleTypesService,
    protected dlsVhlTrsExamsService: DlsVhlTrsExamsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsVehicleTypes }) => {
      this.updateForm(dlsVehicleTypes);
    });
    this.dlsVhlTrsExamsService
      .query()
      .subscribe(
        (res: HttpResponse<IDlsVhlTrsExams[]>) => (this.dlsvhltrsexams = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(dlsVehicleTypes: IDlsVehicleTypes) {
    this.editForm.patchValue({
      id: dlsVehicleTypes.id,
      code: dlsVehicleTypes.code,
      nameAr: dlsVehicleTypes.nameAr,
      nameEn: dlsVehicleTypes.nameEn,
      dlsVhlTrsExams: dlsVehicleTypes.dlsVhlTrsExams
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
      nameEn: this.editForm.get(['nameEn']).value,
      dlsVhlTrsExams: this.editForm.get(['dlsVhlTrsExams']).value
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
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackDlsVhlTrsExamsById(index: number, item: IDlsVhlTrsExams) {
    return item.id;
  }
}
