import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDlsTrsTypes } from 'app/shared/model/dls-trs-types.model';
import { DlsTrsTypesService } from './dls-trs-types.service';
import { DlsTrsTypesDeleteDialogComponent } from './dls-trs-types-delete-dialog.component';

@Component({
  selector: 'jhi-dls-trs-types',
  templateUrl: './dls-trs-types.component.html'
})
export class DlsTrsTypesComponent implements OnInit, OnDestroy {
  dlsTrsTypes: IDlsTrsTypes[];
  eventSubscriber: Subscription;

  constructor(
    protected dlsTrsTypesService: DlsTrsTypesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.dlsTrsTypesService.query().subscribe((res: HttpResponse<IDlsTrsTypes[]>) => {
      this.dlsTrsTypes = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDlsTrsTypes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDlsTrsTypes) {
    return item.id;
  }

  registerChangeInDlsTrsTypes() {
    this.eventSubscriber = this.eventManager.subscribe('dlsTrsTypesListModification', () => this.loadAll());
  }

  delete(dlsTrsTypes: IDlsTrsTypes) {
    const modalRef = this.modalService.open(DlsTrsTypesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dlsTrsTypes = dlsTrsTypes;
  }
}
