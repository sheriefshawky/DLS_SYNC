import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { CategoriesDetailComponent } from 'app/entities/categories/categories-detail.component';
import { Categories } from 'app/shared/model/categories.model';

describe('Component Tests', () => {
  describe('Categories Management Detail Component', () => {
    let comp: CategoriesDetailComponent;
    let fixture: ComponentFixture<CategoriesDetailComponent>;
    const route = ({ data: of({ categories: new Categories(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [CategoriesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CategoriesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoriesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categories).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
