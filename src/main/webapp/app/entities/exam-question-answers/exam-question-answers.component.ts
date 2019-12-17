import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ExamQuestionAnswersService } from './exam-question-answers.service';
import { ExamQuestionAnswersDeleteDialogComponent } from './exam-question-answers-delete-dialog.component';

@Component({
  selector: 'jhi-exam-question-answers',
  templateUrl: './exam-question-answers.component.html'
})
export class ExamQuestionAnswersComponent implements OnInit, OnDestroy {
  examQuestionAnswers: IExamQuestionAnswers[];
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
    protected examQuestionAnswersService: ExamQuestionAnswersService,
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
    this.examQuestionAnswersService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IExamQuestionAnswers[]>) => this.paginateExamQuestionAnswers(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/exam-question-answers'], {
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
      '/exam-question-answers',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInExamQuestionAnswers();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IExamQuestionAnswers) {
    return item.id;
  }

  registerChangeInExamQuestionAnswers() {
    this.eventSubscriber = this.eventManager.subscribe('examQuestionAnswersListModification', () => this.loadAll());
  }

  delete(examQuestionAnswers: IExamQuestionAnswers) {
    const modalRef = this.modalService.open(ExamQuestionAnswersDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.examQuestionAnswers = examQuestionAnswers;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateExamQuestionAnswers(data: IExamQuestionAnswers[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.examQuestionAnswers = data;
  }
}
