import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsRequestsDetailComponent } from 'app/entities/dls-requests/dls-requests-detail.component';
import { DlsRequests } from 'app/shared/model/dls-requests.model';

describe('Component Tests', () => {
  describe('DlsRequests Management Detail Component', () => {
    let comp: DlsRequestsDetailComponent;
    let fixture: ComponentFixture<DlsRequestsDetailComponent>;
    const route = ({ data: of({ dlsRequests: new DlsRequests(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsRequestsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DlsRequestsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsRequestsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dlsRequests).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
