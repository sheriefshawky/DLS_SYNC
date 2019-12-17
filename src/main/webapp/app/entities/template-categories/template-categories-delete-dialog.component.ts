import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITemplateCategories } from 'app/shared/model/template-categories.model';
import { TemplateCategoriesService } from './template-categories.service';

@Component({
  templateUrl: './template-categories-delete-dialog.component.html'
})
export class TemplateCategoriesDeleteDialogComponent {
  templateCategories: ITemplateCategories;

  constructor(
    protected templateCategoriesService: TemplateCategoriesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.templateCategoriesService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'templateCategoriesListModification',
        content: 'Deleted an templateCategories'
      });
      this.activeModal.dismiss(true);
    });
  }
}
