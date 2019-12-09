import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DlssyncTestModule } from '../../../test.module';
import { DlsExamsComponent } from 'app/entities/dls-exams/dls-exams.component';
import { DlsExamsService } from 'app/entities/dls-exams/dls-exams.service';
import { DlsExams } from 'app/shared/model/dls-exams.model';

describe('Component Tests', () => {
  describe('DlsExams Management Component', () => {
    let comp: DlsExamsComponent;
    let fixture: ComponentFixture<DlsExamsComponent>;
    let service: DlsExamsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsExamsComponent],
        providers: []
      })
        .overrideTemplate(DlsExamsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsExamsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsExamsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DlsExams(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dlsExams[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
