import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DlssyncTestModule } from '../../../test.module';
import { DlsVhlTrsExamsComponent } from 'app/entities/dls-vhl-trs-exams/dls-vhl-trs-exams.component';
import { DlsVhlTrsExamsService } from 'app/entities/dls-vhl-trs-exams/dls-vhl-trs-exams.service';
import { DlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';

describe('Component Tests', () => {
  describe('DlsVhlTrsExams Management Component', () => {
    let comp: DlsVhlTrsExamsComponent;
    let fixture: ComponentFixture<DlsVhlTrsExamsComponent>;
    let service: DlsVhlTrsExamsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsVhlTrsExamsComponent],
        providers: []
      })
        .overrideTemplate(DlsVhlTrsExamsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsVhlTrsExamsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsVhlTrsExamsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DlsVhlTrsExams(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dlsVhlTrsExams[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
