import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsReqExamsUpdateComponent } from 'app/entities/dls-req-exams/dls-req-exams-update.component';
import { DlsReqExamsService } from 'app/entities/dls-req-exams/dls-req-exams.service';
import { DlsReqExams } from 'app/shared/model/dls-req-exams.model';

describe('Component Tests', () => {
  describe('DlsReqExams Management Update Component', () => {
    let comp: DlsReqExamsUpdateComponent;
    let fixture: ComponentFixture<DlsReqExamsUpdateComponent>;
    let service: DlsReqExamsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsReqExamsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DlsReqExamsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsReqExamsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsReqExamsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DlsReqExams(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new DlsReqExams();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
