import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDlsExams } from 'app/shared/model/dls-exams.model';

@Component({
  selector: 'jhi-dls-exams-detail',
  templateUrl: './dls-exams-detail.component.html'
})
export class DlsExamsDetailComponent implements OnInit {
  dlsExams: IDlsExams;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dlsExams }) => {
      this.dlsExams = dlsExams;
    });
  }

  previousState() {
    window.history.back();
  }
}
