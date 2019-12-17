import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IExamQuestions } from 'app/shared/model/exam-questions.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ExamQuestionsService } from './exam-questions.service';
import { ExamQuestionsDeleteDialogComponent } from './exam-questions-delete-dialog.component';

@Component({
  selector: 'jhi-exam-questions',
  templateUrl: './exam-questions.component.html'
})
export class ExamQuestionsComponent implements OnInit, OnDestroy {
  examQuestions: IExamQuestions[];
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
    protected examQuestionsService: ExamQuestionsService,
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
    this.examQuestionsService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IExamQuestions[]>) => this.paginateExamQuestions(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/exam-questions'], {
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
      '/exam-questions',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInExamQuestions();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IExamQuestions) {
    return item.id;
  }

  registerChangeInExamQuestions() {
    this.eventSubscriber = this.eventManager.subscribe('examQuestionsListModification', () => this.loadAll());
  }

  delete(examQuestions: IExamQuestions) {
    const modalRef = this.modalService.open(ExamQuestionsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.examQuestions = examQuestions;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateExamQuestions(data: IExamQuestions[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.examQuestions = data;
  }
}
