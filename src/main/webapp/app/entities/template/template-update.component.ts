import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITemplate, Template } from 'app/shared/model/template.model';
import { TemplateService } from './template.service';

@Component({
  selector: 'jhi-template-update',
  templateUrl: './template-update.component.html'
})
export class TemplateUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nameAr: [null, [Validators.required]],
    nameEn: [null, [Validators.required]],
    code: [null, [Validators.required]],
    timeInSec: [],
    passScore: [],
    status: []
  });

  constructor(protected templateService: TemplateService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ template }) => {
      this.updateForm(template);
    });
  }

  updateForm(template: ITemplate) {
    this.editForm.patchValue({
      id: template.id,
      nameAr: template.nameAr,
      nameEn: template.nameEn,
      code: template.code,
      timeInSec: template.timeInSec,
      passScore: template.passScore,
      status: template.status
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const template = this.createFromForm();
    if (template.id !== undefined) {
      this.subscribeToSaveResponse(this.templateService.update(template));
    } else {
      this.subscribeToSaveResponse(this.templateService.create(template));
    }
  }

  private createFromForm(): ITemplate {
    return {
      ...new Template(),
      id: this.editForm.get(['id']).value,
      nameAr: this.editForm.get(['nameAr']).value,
      nameEn: this.editForm.get(['nameEn']).value,
      code: this.editForm.get(['code']).value,
      timeInSec: this.editForm.get(['timeInSec']).value,
      passScore: this.editForm.get(['passScore']).value,
      status: this.editForm.get(['status']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITemplate>>) {
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
