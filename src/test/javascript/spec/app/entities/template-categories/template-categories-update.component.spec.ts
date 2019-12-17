import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { TemplateCategoriesUpdateComponent } from 'app/entities/template-categories/template-categories-update.component';
import { TemplateCategoriesService } from 'app/entities/template-categories/template-categories.service';
import { TemplateCategories } from 'app/shared/model/template-categories.model';

describe('Component Tests', () => {
  describe('TemplateCategories Management Update Component', () => {
    let comp: TemplateCategoriesUpdateComponent;
    let fixture: ComponentFixture<TemplateCategoriesUpdateComponent>;
    let service: TemplateCategoriesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [TemplateCategoriesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TemplateCategoriesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TemplateCategoriesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TemplateCategoriesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TemplateCategories(123);
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
        const entity = new TemplateCategories();
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
