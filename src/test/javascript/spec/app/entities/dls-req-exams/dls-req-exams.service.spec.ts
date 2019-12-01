import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DlsReqExamsService } from 'app/entities/dls-req-exams/dls-req-exams.service';
import { IDlsReqExams, DlsReqExams } from 'app/shared/model/dls-req-exams.model';

describe('Service Tests', () => {
  describe('DlsReqExams Service', () => {
    let injector: TestBed;
    let service: DlsReqExamsService;
    let httpMock: HttpTestingController;
    let elemDefault: IDlsReqExams;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(DlsReqExamsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DlsReqExams(0, 0, 0, currentDate, 0, 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            examDate: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a DlsReqExams', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            examDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            examDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new DlsReqExams(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a DlsReqExams', () => {
        const returnedFromService = Object.assign(
          {
            examCode: 1,
            examResult: 1,
            examDate: currentDate.format(DATE_TIME_FORMAT),
            examGrade: 1,
            isEligibile: 1,
            exported: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            examDate: currentDate
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

      it('should return a list of DlsReqExams', () => {
        const returnedFromService = Object.assign(
          {
            examCode: 1,
            examResult: 1,
            examDate: currentDate.format(DATE_TIME_FORMAT),
            examGrade: 1,
            isEligibile: 1,
            exported: true
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            examDate: currentDate
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

      it('should delete a DlsReqExams', () => {
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
