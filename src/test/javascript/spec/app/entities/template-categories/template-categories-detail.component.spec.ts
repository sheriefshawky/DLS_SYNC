import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { TemplateCategoriesDetailComponent } from 'app/entities/template-categories/template-categories-detail.component';
import { TemplateCategories } from 'app/shared/model/template-categories.model';

describe('Component Tests', () => {
  describe('TemplateCategories Management Detail Component', () => {
    let comp: TemplateCategoriesDetailComponent;
    let fixture: ComponentFixture<TemplateCategoriesDetailComponent>;
    const route = ({ data: of({ templateCategories: new TemplateCategories(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [TemplateCategoriesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TemplateCategoriesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TemplateCategoriesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.templateCategories).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
