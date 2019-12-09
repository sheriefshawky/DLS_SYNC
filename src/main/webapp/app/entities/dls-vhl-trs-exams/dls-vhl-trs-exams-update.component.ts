import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IDlsVhlTrsExams, DlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';
import { DlsVhlTrsExamsService } from './dls-vhl-trs-exams.service';
import { IDlsExams } from 'app/shared/model/dls-exams.model';
import { DlsExamsService } from 'app/entities/dls-exams/dls-exams.service';
import { IDlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';
import { DlsVehicleTypesService } from 'app/entities/dls-vehicle-types/dls-vehicle-types.service';
import { IDlsTrsTypes } from 'app/shared/model/dls-trs-types.model';
import { DlsTrsTypesService } from 'app/entities/dls-trs-types/dls-trs-types.service';

@Component({
  selector: 'jhi-dls-vhl-trs-exams-update',
  templateUrl: './dls-vhl-trs-exams-update.component.html'
})
export class DlsVhlTrsExamsUpdateComponent implements OnInit {
  isSaving: boolean;

  dlsexams: IDlsExams[];

  dlsvehicletypes: IDlsVehicleTypes[];

  dlstrstypes: IDlsTrsTypes[];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    nameAr: [null, [Validators.required]],
    nameEn: [null, [Validators.required]],
    exams: [],
    vehicleTypes: [],
    trsTypes: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected dlsVhlTrsExamsService: DlsVhlTrsExamsService,
    protected dlsExamsService: DlsExamsService,
    protected dlsVehicleTypesService: DlsVehicleTypesService,
    protected dlsTrsTypesService: DlsTrsTypesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsVhlTrsExams }) => {
      this.updateForm(dlsVhlTrsExams);
    });
    this.dlsExamsService
      .query()
      .subscribe((res: HttpResponse<IDlsExams[]>) => (this.dlsexams = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.dlsVehicleTypesService
      .query()
      .subscribe(
        (res: HttpResponse<IDlsVehicleTypes[]>) => (this.dlsvehicletypes = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.dlsTrsTypesService
      .query()
      .subscribe(
        (res: HttpResponse<IDlsTrsTypes[]>) => (this.dlstrstypes = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(dlsVhlTrsExams: IDlsVhlTrsExams) {
    this.editForm.patchValue({
      id: dlsVhlTrsExams.id,
      code: dlsVhlTrsExams.code,
      nameAr: dlsVhlTrsExams.nameAr,
      nameEn: dlsVhlTrsExams.nameEn,
      exams: dlsVhlTrsExams.exams,
      vehicleTypes: dlsVhlTrsExams.vehicleTypes,
      trsTypes: dlsVhlTrsExams.trsTypes
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dlsVhlTrsExams = this.createFromForm();
    if (dlsVhlTrsExams.id !== undefined) {
      this.subscribeToSaveResponse(this.dlsVhlTrsExamsService.update(dlsVhlTrsExams));
    } else {
      this.subscribeToSaveResponse(this.dlsVhlTrsExamsService.create(dlsVhlTrsExams));
    }
  }

  private createFromForm(): IDlsVhlTrsExams {
    return {
      ...new DlsVhlTrsExams(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      nameAr: this.editForm.get(['nameAr']).value,
      nameEn: this.editForm.get(['nameEn']).value,
      exams: this.editForm.get(['exams']).value,
      vehicleTypes: this.editForm.get(['vehicleTypes']).value,
      trsTypes: this.editForm.get(['trsTypes']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDlsVhlTrsExams>>) {
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

  trackDlsExamsById(index: number, item: IDlsExams) {
    return item.id;
  }

  trackDlsVehicleTypesById(index: number, item: IDlsVehicleTypes) {
    return item.id;
  }

  trackDlsTrsTypesById(index: number, item: IDlsTrsTypes) {
    return item.id;
  }
}
