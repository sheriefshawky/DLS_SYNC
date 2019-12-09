import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsVhlTrsExamsUpdateComponent } from 'app/entities/dls-vhl-trs-exams/dls-vhl-trs-exams-update.component';
import { DlsVhlTrsExamsService } from 'app/entities/dls-vhl-trs-exams/dls-vhl-trs-exams.service';
import { DlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';

describe('Component Tests', () => {
  describe('DlsVhlTrsExams Management Update Component', () => {
    let comp: DlsVhlTrsExamsUpdateComponent;
    let fixture: ComponentFixture<DlsVhlTrsExamsUpdateComponent>;
    let service: DlsVhlTrsExamsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsVhlTrsExamsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DlsVhlTrsExamsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsVhlTrsExamsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsVhlTrsExamsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DlsVhlTrsExams(123);
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
        const entity = new DlsVhlTrsExams();
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
