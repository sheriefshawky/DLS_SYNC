import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsPersonsDetailComponent } from 'app/entities/dls-persons/dls-persons-detail.component';
import { DlsPersons } from 'app/shared/model/dls-persons.model';

describe('Component Tests', () => {
  describe('DlsPersons Management Detail Component', () => {
    let comp: DlsPersonsDetailComponent;
    let fixture: ComponentFixture<DlsPersonsDetailComponent>;
    const route = ({ data: of({ dlsPersons: new DlsPersons(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsPersonsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DlsPersonsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsPersonsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dlsPersons).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
