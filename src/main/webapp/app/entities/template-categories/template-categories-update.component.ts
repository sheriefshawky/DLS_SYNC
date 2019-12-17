import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ITemplateCategories, TemplateCategories } from 'app/shared/model/template-categories.model';
import { TemplateCategoriesService } from './template-categories.service';
import { ICategories } from 'app/shared/model/categories.model';
import { CategoriesService } from 'app/entities/categories/categories.service';
import { ITemplate } from 'app/shared/model/template.model';
import { TemplateService } from 'app/entities/template/template.service';

@Component({
  selector: 'jhi-template-categories-update',
  templateUrl: './template-categories-update.component.html'
})
export class TemplateCategoriesUpdateComponent implements OnInit {
  isSaving: boolean;

  categories: ICategories[];

  templates: ITemplate[];

  editForm = this.fb.group({
    id: [],
    noOfQuestions: [null, [Validators.required]],
    seq: [null, [Validators.required]],
    category: [],
    template: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected templateCategoriesService: TemplateCategoriesService,
    protected categoriesService: CategoriesService,
    protected templateService: TemplateService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ templateCategories }) => {
      this.updateForm(templateCategories);
    });
    this.categoriesService.query({ filter: 'templatecategories-is-null' }).subscribe(
      (res: HttpResponse<ICategories[]>) => {
        if (!this.editForm.get('category').value || !this.editForm.get('category').value.id) {
          this.categories = res.body;
        } else {
          this.categoriesService
            .find(this.editForm.get('category').value.id)
            .subscribe(
              (subRes: HttpResponse<ICategories>) => (this.categories = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.templateService
      .query()
      .subscribe((res: HttpResponse<ITemplate[]>) => (this.templates = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(templateCategories: ITemplateCategories) {
    this.editForm.patchValue({
      id: templateCategories.id,
      noOfQuestions: templateCategories.noOfQuestions,
      seq: templateCategories.seq,
      category: templateCategories.category,
      template: templateCategories.template
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const templateCategories = this.createFromForm();
    if (templateCategories.id !== undefined) {
      this.subscribeToSaveResponse(this.templateCategoriesService.update(templateCategories));
    } else {
      this.subscribeToSaveResponse(this.templateCategoriesService.create(templateCategories));
    }
  }

  private createFromForm(): ITemplateCategories {
    return {
      ...new TemplateCategories(),
      id: this.editForm.get(['id']).value,
      noOfQuestions: this.editForm.get(['noOfQuestions']).value,
      seq: this.editForm.get(['seq']).value,
      category: this.editForm.get(['category']).value,
      template: this.editForm.get(['template']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITemplateCategories>>) {
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

  trackCategoriesById(index: number, item: ICategories) {
    return item.id;
  }

  trackTemplateById(index: number, item: ITemplate) {
    return item.id;
  }
}
