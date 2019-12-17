import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITemplateCategories } from 'app/shared/model/template-categories.model';

@Component({
  selector: 'jhi-template-categories-detail',
  templateUrl: './template-categories-detail.component.html'
})
export class TemplateCategoriesDetailComponent implements OnInit {
  templateCategories: ITemplateCategories;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ templateCategories }) => {
      this.templateCategories = templateCategories;
    });
  }

  previousState() {
    window.history.back();
  }
}
