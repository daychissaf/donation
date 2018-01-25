import { Injectable } from '@angular/core';
import { Donor } from './donor';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class DonorService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: Http) { }

  getDonors():  Promise<Donor[]> {
    return this.http.get(this.baseUrl + '/api/donors/')
      .toPromise()
      .then(response => response.json() as Donor[])
      .catch(this.handleError);
  }

  createDonor(donorData: Donor): Promise<Donor> {
    return this.http.post(this.baseUrl + '/api/donors/', donorData)
      .toPromise().then(response => response.json() as Donor)
      .catch(this.handleError);
  }

  updateDonor(donorData: Donor): Promise<Donor> {
    return this.http.put(this.baseUrl + '/api/donors/' + donorData.id, donorData)
      .toPromise()
      .then(response => response.json() as Donor)
      .catch(this.handleError);
  }

  deleteDonor(id: number): Promise<any> {
    return this.http.delete(this.baseUrl + '/api/donors/' + id)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }
}
