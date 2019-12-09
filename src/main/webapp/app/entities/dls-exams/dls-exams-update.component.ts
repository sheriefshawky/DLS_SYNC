import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDlsExams, DlsExams } from 'app/shared/model/dls-exams.model';
import { DlsExamsService } from './dls-exams.service';

@Component({
  selector: 'jhi-dls-exams-update',
  templateUrl: './dls-exams-update.component.html'
})
export class DlsExamsUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    nameAr: [null, [Validators.required]],
    nameEn: [null, [Validators.required]],
    testId: [null, [Validators.required]],
    qroupId: [null, [Validators.required]],
    passPercentage: [null, [Validators.required]]
  });

  constructor(protected dlsExamsService: DlsExamsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsExams }) => {
      this.updateForm(dlsExams);
    });
  }

  updateForm(dlsExams: IDlsExams) {
    this.editForm.patchValue({
      id: dlsExams.id,
      code: dlsExams.code,
      nameAr: dlsExams.nameAr,
      nameEn: dlsExams.nameEn,
      testId: dlsExams.testId,
      qroupId: dlsExams.qroupId,
      passPercentage: dlsExams.passPercentage
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
      passPercentage: this.editForm.get(['passPercentage']).value
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
}
