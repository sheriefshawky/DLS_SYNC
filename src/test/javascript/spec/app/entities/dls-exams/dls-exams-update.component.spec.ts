import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsExamsUpdateComponent } from 'app/entities/dls-exams/dls-exams-update.component';
import { DlsExamsService } from 'app/entities/dls-exams/dls-exams.service';
import { DlsExams } from 'app/shared/model/dls-exams.model';

describe('Component Tests', () => {
  describe('DlsExams Management Update Component', () => {
    let comp: DlsExamsUpdateComponent;
    let fixture: ComponentFixture<DlsExamsUpdateComponent>;
    let service: DlsExamsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsExamsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DlsExamsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsExamsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsExamsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DlsExams(123);
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
        const entity = new DlsExams();
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
