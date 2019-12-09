import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IDlsExams, DlsExams } from 'app/shared/model/dls-exams.model';
import { DlsExamsService } from './dls-exams.service';
import { IDlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';
import { DlsVhlTrsExamsService } from 'app/entities/dls-vhl-trs-exams/dls-vhl-trs-exams.service';

@Component({
  selector: 'jhi-dls-exams-update',
  templateUrl: './dls-exams-update.component.html'
})
export class DlsExamsUpdateComponent implements OnInit {
  isSaving: boolean;

  dlsvhltrsexams: IDlsVhlTrsExams[];

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    nameAr: [null, [Validators.required]],
    nameEn: [null, [Validators.required]],
    testId: [null, [Validators.required]],
    qroupId: [null, [Validators.required]],
    passPercentage: [null, [Validators.required]],
    dlsVhlTrsExams: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected dlsExamsService: DlsExamsService,
    protected dlsVhlTrsExamsService: DlsVhlTrsExamsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsExams }) => {
      this.updateForm(dlsExams);
    });
    this.dlsVhlTrsExamsService
      .query()
      .subscribe(
        (res: HttpResponse<IDlsVhlTrsExams[]>) => (this.dlsvhltrsexams = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(dlsExams: IDlsExams) {
    this.editForm.patchValue({
      id: dlsExams.id,
      code: dlsExams.code,
      nameAr: dlsExams.nameAr,
      nameEn: dlsExams.nameEn,
      testId: dlsExams.testId,
      qroupId: dlsExams.qroupId,
      passPercentage: dlsExams.passPercentage,
      dlsVhlTrsExams: dlsExams.dlsVhlTrsExams
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dlsExams = this.createFromForm();
    if (dlsExams.id !== undefined) {
      this.subscribeToSaveResponse(this.dlsExamsService.update(dlsExams));
    } else {
      this.subscribeToSaveResponse(this.dlsExamsService.create(dlsExams));
    }
  }

  private createFromForm(): IDlsExams {
    return {
      ...new DlsExams(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      nameAr: this.editForm.get(['nameAr']).value,
      nameEn: this.editForm.get(['nameEn']).value,
      testId: this.editForm.get(['testId']).value,
      qroupId: this.editForm.get(['qroupId']).value,
      passPercentage: this.editForm.get(['passPercentage']).value,
      dlsVhlTrsExams: this.editForm.get(['dlsVhlTrsExams']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDlsExams>>) {
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
