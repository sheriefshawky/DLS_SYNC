import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IQuestions, Questions } from 'app/shared/model/questions.model';
import { QuestionsService } from './questions.service';
import { ICategories } from 'app/shared/model/categories.model';
import { CategoriesService } from 'app/entities/categories/categories.service';

@Component({
  selector: 'jhi-questions-update',
  templateUrl: './questions-update.component.html'
})
export class QuestionsUpdateComponent implements OnInit {
  isSaving: boolean;

  categories: ICategories[];

  editForm = this.fb.group({
    id: [],
    descAr: [null, [Validators.required]],
    descEn: [null, [Validators.required]],
    code: [null, [Validators.required]],
    imgPath: [],
    timeInSec: [],
    type: [null, [Validators.required]],
    weight: [null, [Validators.required]],
    status: [],
    categories: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected questionsService: QuestionsService,
    protected categoriesService: CategoriesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ questions }) => {
      this.updateForm(questions);
    });
    this.categoriesService
      .query()
      .subscribe((res: HttpResponse<ICategories[]>) => (this.categories = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(questions: IQuestions) {
    this.editForm.patchValue({
      id: questions.id,
      descAr: questions.descAr,
      descEn: questions.descEn,
      code: questions.code,
      imgPath: questions.imgPath,
      timeInSec: questions.timeInSec,
      type: questions.type,
      weight: questions.weight,
      status: questions.status,
      categories: questions.categories
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const questions = this.createFromForm();
    if (questions.id !== undefined) {
      this.subscribeToSaveResponse(this.questionsService.update(questions));
    } else {
      this.subscribeToSaveResponse(this.questionsService.create(questions));
    }
  }

  private createFromForm(): IQuestions {
    return {
      ...new Questions(),
      id: this.editForm.get(['id']).value,
      descAr: this.editForm.get(['descAr']).value,
      descEn: this.editForm.get(['descEn']).value,
      code: this.editForm.get(['code']).value,
      imgPath: this.editForm.get(['imgPath']).value,
      timeInSec: this.editForm.get(['timeInSec']).value,
      type: this.editForm.get(['type']).value,
      weight: this.editForm.get(['weight']).value,
      status: this.editForm.get(['status']).value,
      categories: this.editForm.get(['categories']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuestions>>) {
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
}
