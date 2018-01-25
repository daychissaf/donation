import { Component, OnInit } from '@angular/core';
import { DonorService } from './donor.service';
import { Donor } from './donor';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'donor-crud',
  templateUrl: './donor-crud.component.html'
})

export class DonorCrudComponent implements OnInit {
  donors: Donor[];
  newDonor: Donor = new Donor();
  editing: boolean = false;
  editingDonor: Donor = new Donor();

  constructor(
    private donorService: DonorService,
  ) {}

  ngOnInit(): void {
    this.getDonors();
  }

  getDonors(): void {
    this.donorService.getDonors()
      .then(donors => this.donors = donors );
  }

  createDonor(donorForm: NgForm): void {
    this.donorService.createDonor(this.newDonor)
      .then(createDonor => {
        donorForm.reset();
        this.newDonor = new Donor();
        this.donors.unshift(createDonor);
      });
  }

  deleteDonor(id: number): void {
    this.donorService.deleteDonor(id)
    .then(() => {
      this.donors = this.donors.filter(donor => donor.id != id);
    });
  }

  updateDonor(donorData: Donor): void {
    console.log(donorData);
    this.donorService.updateDonor(donorData)
    .then(updatedDonor => {
      let existingDonor = this.donors.find(donor => donor.id === updatedDonor.id);
      Object.assign(existingDonor, updatedDonor);
      this.clearEditing();
    });
  }

  toggleCompleted(donorData: Donor): void {
    //donorData.completed = !donorData.completed;
    this.donorService.updateDonor(donorData)
    .then(updatedDonor => {
      let existingDonor = this.donors.find(donor => donor.id === updatedDonor.id);
      Object.assign(existingDonor, updatedDonor);
    });
  }

  editDonor(donorData: Donor): void {
    this.editing = true;
    Object.assign(this.editingDonor, donorData);
  }

  clearEditing(): void {
    this.editingDonor = new Donor();
    this.editing = false;
  }
}
