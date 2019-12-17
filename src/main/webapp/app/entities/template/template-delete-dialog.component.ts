import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITemplate } from 'app/shared/model/template.model';
import { TemplateService } from './template.service';

@Component({
  templateUrl: './template-delete-dialog.component.html'
})
export class TemplateDeleteDialogComponent {
  template: ITemplate;

  constructor(protected templateService: TemplateService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.templateService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'templateListModification',
        content: 'Deleted an template'
      });
      this.activeModal.dismiss(true);
    });
  }
}
