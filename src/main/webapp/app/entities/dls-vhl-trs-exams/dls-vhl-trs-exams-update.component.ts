import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDlsVhlTrsExams, DlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';
import { DlsVhlTrsExamsService } from './dls-vhl-trs-exams.service';

@Component({
  selector: 'jhi-dls-vhl-trs-exams-update',
  templateUrl: './dls-vhl-trs-exams-update.component.html'
})
export class DlsVhlTrsExamsUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required]],
    nameAr: [null, [Validators.required]],
    nameEn: [null, [Validators.required]]
  });

  constructor(protected dlsVhlTrsExamsService: DlsVhlTrsExamsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dlsVhlTrsExams }) => {
      this.updateForm(dlsVhlTrsExams);
    });
  }

  updateForm(dlsVhlTrsExams: IDlsVhlTrsExams) {
    this.editForm.patchValue({
      id: dlsVhlTrsExams.id,
      code: dlsVhlTrsExams.code,
      nameAr: dlsVhlTrsExams.nameAr,
      nameEn: dlsVhlTrsExams.nameEn
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
      nameEn: this.editForm.get(['nameEn']).value
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
}
