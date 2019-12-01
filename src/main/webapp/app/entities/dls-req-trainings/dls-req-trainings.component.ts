import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DlsReqTrainingsService } from './dls-req-trainings.service';
import { DlsReqTrainingsDeleteDialogComponent } from './dls-req-trainings-delete-dialog.component';

@Component({
  selector: 'jhi-dls-req-trainings',
  templateUrl: './dls-req-trainings.component.html'
})
export class DlsReqTrainingsComponent implements OnInit, OnDestroy {
  dlsReqTrainings: IDlsReqTrainings[];
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
    protected dlsReqTrainingsService: DlsReqTrainingsService,
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
    this.dlsReqTrainingsService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IDlsReqTrainings[]>) => this.paginateDlsReqTrainings(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/dls-req-trainings'], {
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
      '/dls-req-trainings',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDlsReqTrainings();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDlsReqTrainings) {
    return item.id;
  }

  registerChangeInDlsReqTrainings() {
    this.eventSubscriber = this.eventManager.subscribe('dlsReqTrainingsListModification', () => this.loadAll());
  }

  delete(dlsReqTrainings: IDlsReqTrainings) {
    const modalRef = this.modalService.open(DlsReqTrainingsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dlsReqTrainings = dlsReqTrainings;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateDlsReqTrainings(data: IDlsReqTrainings[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.dlsReqTrainings = data;
  }
}
