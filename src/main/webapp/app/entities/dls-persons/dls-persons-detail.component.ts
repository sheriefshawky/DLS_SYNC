import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDlsPersons } from 'app/shared/model/dls-persons.model';

@Component({
  selector: 'jhi-dls-persons-detail',
  templateUrl: './dls-persons-detail.component.html'
})
export class DlsPersonsDetailComponent implements OnInit {
  dlsPersons: IDlsPersons;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dlsPersons }) => {
      this.dlsPersons = dlsPersons;
    });
  }

  previousState() {
    window.history.back();
  }
}
