import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { DlsTrsTypesDeleteDialogComponent } from 'app/entities/dls-trs-types/dls-trs-types-delete-dialog.component';
import { DlsTrsTypesService } from 'app/entities/dls-trs-types/dls-trs-types.service';

describe('Component Tests', () => {
  describe('DlsTrsTypes Management Delete Component', () => {
    let comp: DlsTrsTypesDeleteDialogComponent;
    let fixture: ComponentFixture<DlsTrsTypesDeleteDialogComponent>;
    let service: DlsTrsTypesService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsTrsTypesDeleteDialogComponent]
      })
        .overrideTemplate(DlsTrsTypesDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsTrsTypesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsTrsTypesService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
