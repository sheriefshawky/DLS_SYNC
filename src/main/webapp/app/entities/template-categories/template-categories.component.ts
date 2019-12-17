import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITemplateCategories } from 'app/shared/model/template-categories.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TemplateCategoriesService } from './template-categories.service';
import { TemplateCategoriesDeleteDialogComponent } from './template-categories-delete-dialog.component';

@Component({
  selector: 'jhi-template-categories',
  templateUrl: './template-categories.component.html'
})
export class TemplateCategoriesComponent implements OnInit, OnDestroy {
  templateCategories: ITemplateCategories[];
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
    protected templateCategoriesService: TemplateCategoriesService,
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
    this.templateCategoriesService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ITemplateCategories[]>) => this.paginateTemplateCategories(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/template-categories'], {
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
      '/template-categories',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInTemplateCategories();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ITemplateCategories) {
    return item.id;
  }

  registerChangeInTemplateCategories() {
    this.eventSubscriber = this.eventManager.subscribe('templateCategoriesListModification', () => this.loadAll());
  }

  delete(templateCategories: ITemplateCategories) {
    const modalRef = this.modalService.open(TemplateCategoriesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.templateCategories = templateCategories;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTemplateCategories(data: ITemplateCategories[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.templateCategories = data;
  }
}
