import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { DlsExamsDeleteDialogComponent } from 'app/entities/dls-exams/dls-exams-delete-dialog.component';
import { DlsExamsService } from 'app/entities/dls-exams/dls-exams.service';

describe('Component Tests', () => {
  describe('DlsExams Management Delete Component', () => {
    let comp: DlsExamsDeleteDialogComponent;
    let fixture: ComponentFixture<DlsExamsDeleteDialogComponent>;
    let service: DlsExamsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsExamsDeleteDialogComponent]
      })
        .overrideTemplate(DlsExamsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsExamsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsExamsService);
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
