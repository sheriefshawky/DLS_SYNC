import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { DlsVhlTrsExamsDeleteDialogComponent } from 'app/entities/dls-vhl-trs-exams/dls-vhl-trs-exams-delete-dialog.component';
import { DlsVhlTrsExamsService } from 'app/entities/dls-vhl-trs-exams/dls-vhl-trs-exams.service';

describe('Component Tests', () => {
  describe('DlsVhlTrsExams Management Delete Component', () => {
    let comp: DlsVhlTrsExamsDeleteDialogComponent;
    let fixture: ComponentFixture<DlsVhlTrsExamsDeleteDialogComponent>;
    let service: DlsVhlTrsExamsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsVhlTrsExamsDeleteDialogComponent]
      })
        .overrideTemplate(DlsVhlTrsExamsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsVhlTrsExamsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsVhlTrsExamsService);
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
