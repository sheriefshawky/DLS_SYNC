import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';
import { DlsVehicleTypesService } from './dls-vehicle-types.service';
import { DlsVehicleTypesDeleteDialogComponent } from './dls-vehicle-types-delete-dialog.component';

@Component({
  selector: 'jhi-dls-vehicle-types',
  templateUrl: './dls-vehicle-types.component.html'
})
export class DlsVehicleTypesComponent implements OnInit, OnDestroy {
  dlsVehicleTypes: IDlsVehicleTypes[];
  eventSubscriber: Subscription;

  constructor(
    protected dlsVehicleTypesService: DlsVehicleTypesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.dlsVehicleTypesService.query().subscribe((res: HttpResponse<IDlsVehicleTypes[]>) => {
      this.dlsVehicleTypes = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDlsVehicleTypes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDlsVehicleTypes) {
    return item.id;
  }

  registerChangeInDlsVehicleTypes() {
    this.eventSubscriber = this.eventManager.subscribe('dlsVehicleTypesListModification', () => this.loadAll());
  }

  delete(dlsVehicleTypes: IDlsVehicleTypes) {
    const modalRef = this.modalService.open(DlsVehicleTypesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dlsVehicleTypes = dlsVehicleTypes;
  }
}
