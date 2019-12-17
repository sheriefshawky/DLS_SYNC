import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ExamQuestionsService } from 'app/entities/exam-questions/exam-questions.service';
import { IExamQuestions, ExamQuestions } from 'app/shared/model/exam-questions.model';

describe('Service Tests', () => {
  describe('ExamQuestions Service', () => {
    let injector: TestBed;
    let service: ExamQuestionsService;
    let httpMock: HttpTestingController;
    let elemDefault: IExamQuestions;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ExamQuestionsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ExamQuestions(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            startAt: currentDate.format(DATE_TIME_FORMAT),
            submitAt: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a ExamQuestions', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            startAt: currentDate.format(DATE_TIME_FORMAT),
            submitAt: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startAt: currentDate,
            submitAt: currentDate
          },
          returnedFromService
        );
        service
          .create(new ExamQuestions(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ExamQuestions', () => {
        const returnedFromService = Object.assign(
          {
            descAr: 'BBBBBB',
            descEn: 'BBBBBB',
            code: 'BBBBBB',
            imgPath: 'BBBBBB',
            timeInSec: 1,
            type: 1,
            weight: 1,
            score: 1,
            status: 1,
            seq: 1,
            categoryId: 1,
            questionId: 1,
            startAt: currentDate.format(DATE_TIME_FORMAT),
            submitAt: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startAt: currentDate,
            submitAt: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of ExamQuestions', () => {
        const returnedFromService = Object.assign(
          {
            descAr: 'BBBBBB',
            descEn: 'BBBBBB',
            code: 'BBBBBB',
            imgPath: 'BBBBBB',
            timeInSec: 1,
            type: 1,
            weight: 1,
            score: 1,
            status: 1,
            seq: 1,
            categoryId: 1,
            questionId: 1,
            startAt: currentDate.format(DATE_TIME_FORMAT),
            submitAt: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            startAt: currentDate,
            submitAt: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ExamQuestions', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
