import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { DlsPersonsService } from 'app/entities/dls-persons/dls-persons.service';
import { IDlsPersons, DlsPersons } from 'app/shared/model/dls-persons.model';

describe('Service Tests', () => {
  describe('DlsPersons Service', () => {
    let injector: TestBed;
    let service: DlsPersonsService;
    let httpMock: HttpTestingController;
    let elemDefault: IDlsPersons;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(DlsPersonsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new DlsPersons(0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a DlsPersons', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new DlsPersons(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a DlsPersons', () => {
        const returnedFromService = Object.assign(
          {
            fullName: 'BBBBBB',
            mobileNo: 'BBBBBB',
            licenseCategory: 1,
            nationalId: 'BBBBBB',
            passportKey: 'BBBBBB',
            transactionType: 1,
            exported: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of DlsPersons', () => {
        const returnedFromService = Object.assign(
          {
            fullName: 'BBBBBB',
            mobileNo: 'BBBBBB',
            licenseCategory: 1,
            nationalId: 'BBBBBB',
            passportKey: 'BBBBBB',
            transactionType: 1,
            exported: true
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
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

      it('should delete a DlsPersons', () => {
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
