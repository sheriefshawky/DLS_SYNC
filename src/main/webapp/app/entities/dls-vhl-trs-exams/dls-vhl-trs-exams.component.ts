import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';
import { DlsVhlTrsExamsService } from './dls-vhl-trs-exams.service';
import { DlsVhlTrsExamsDeleteDialogComponent } from './dls-vhl-trs-exams-delete-dialog.component';

@Component({
  selector: 'jhi-dls-vhl-trs-exams',
  templateUrl: './dls-vhl-trs-exams.component.html'
})
export class DlsVhlTrsExamsComponent implements OnInit, OnDestroy {
  dlsVhlTrsExams: IDlsVhlTrsExams[];
  eventSubscriber: Subscription;

  constructor(
    protected dlsVhlTrsExamsService: DlsVhlTrsExamsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.dlsVhlTrsExamsService.query().subscribe((res: HttpResponse<IDlsVhlTrsExams[]>) => {
      this.dlsVhlTrsExams = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDlsVhlTrsExams();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDlsVhlTrsExams) {
    return item.id;
  }

  registerChangeInDlsVhlTrsExams() {
    this.eventSubscriber = this.eventManager.subscribe('dlsVhlTrsExamsListModification', () => this.loadAll());
  }

  delete(dlsVhlTrsExams: IDlsVhlTrsExams) {
    const modalRef = this.modalService.open(DlsVhlTrsExamsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dlsVhlTrsExams = dlsVhlTrsExams;
  }
}
