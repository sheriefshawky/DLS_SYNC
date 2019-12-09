import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';

@Component({
  selector: 'jhi-dls-vhl-trs-exams-detail',
  templateUrl: './dls-vhl-trs-exams-detail.component.html'
})
export class DlsVhlTrsExamsDetailComponent implements OnInit {
  dlsVhlTrsExams: IDlsVhlTrsExams;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dlsVhlTrsExams }) => {
      this.dlsVhlTrsExams = dlsVhlTrsExams;
    });
  }

  previousState() {
    window.history.back();
  }
}
