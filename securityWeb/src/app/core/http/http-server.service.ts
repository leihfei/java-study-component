import { BASE_URL } from './base-config';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpServerService {
  private base_url = `${BASE_URL}`;

  constructor(private http: HttpClient) { }

  get(url): Observable<any> {
    return this.http.get(`${this.base_url}/url`);
  }
}
