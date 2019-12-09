import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DlssyncTestModule } from '../../../test.module';
import { DlsTrsTypesComponent } from 'app/entities/dls-trs-types/dls-trs-types.component';
import { DlsTrsTypesService } from 'app/entities/dls-trs-types/dls-trs-types.service';
import { DlsTrsTypes } from 'app/shared/model/dls-trs-types.model';

describe('Component Tests', () => {
  describe('DlsTrsTypes Management Component', () => {
    let comp: DlsTrsTypesComponent;
    let fixture: ComponentFixture<DlsTrsTypesComponent>;
    let service: DlsTrsTypesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsTrsTypesComponent],
        providers: []
      })
        .overrideTemplate(DlsTrsTypesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsTrsTypesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsTrsTypesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DlsTrsTypes(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dlsTrsTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
