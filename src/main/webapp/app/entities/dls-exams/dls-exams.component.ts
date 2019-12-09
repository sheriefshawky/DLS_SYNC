import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDlsExams } from 'app/shared/model/dls-exams.model';
import { DlsExamsService } from './dls-exams.service';
import { DlsExamsDeleteDialogComponent } from './dls-exams-delete-dialog.component';

@Component({
  selector: 'jhi-dls-exams',
  templateUrl: './dls-exams.component.html'
})
export class DlsExamsComponent implements OnInit, OnDestroy {
  dlsExams: IDlsExams[];
  eventSubscriber: Subscription;

  constructor(protected dlsExamsService: DlsExamsService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll() {
    this.dlsExamsService.query().subscribe((res: HttpResponse<IDlsExams[]>) => {
      this.dlsExams = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDlsExams();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDlsExams) {
    return item.id;
  }

  registerChangeInDlsExams() {
    this.eventSubscriber = this.eventManager.subscribe('dlsExamsListModification', () => this.loadAll());
  }

  delete(dlsExams: IDlsExams) {
    const modalRef = this.modalService.open(DlsExamsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dlsExams = dlsExams;
  }
}
