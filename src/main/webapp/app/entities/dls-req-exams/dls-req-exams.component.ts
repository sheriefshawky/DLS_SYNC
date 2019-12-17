import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDlsReqExams } from 'app/shared/model/dls-req-exams.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DlsReqExamsService } from './dls-req-exams.service';
import { DlsReqExamsDeleteDialogComponent } from './dls-req-exams-delete-dialog.component';

@Component({
  selector: 'jhi-dls-req-exams',
  templateUrl: './dls-req-exams.component.html'
})
export class DlsReqExamsComponent implements OnInit, OnDestroy {
  dlsReqExams: IDlsReqExams[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected dlsReqExamsService: DlsReqExamsService,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.dlsReqExamsService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IDlsReqExams[]>) => this.paginateDlsReqExams(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/dls-req-exams'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/dls-req-exams',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDlsReqExams();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDlsReqExams) {
    return item.id;
  }

  registerChangeInDlsReqExams() {
    this.eventSubscriber = this.eventManager.subscribe('dlsReqExamsListModification', () => this.loadAll());
  }

  delete(dlsReqExams: IDlsReqExams) {
    const modalRef = this.modalService.open(DlsReqExamsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dlsReqExams = dlsReqExams;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateDlsReqExams(data: IDlsReqExams[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.dlsReqExams = data;
  }
}
