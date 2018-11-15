import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) {
  }

  get(url: string, param?: any): Observable<any> {
    return this.http.get(url);
  }

  post(url: string, param: any): Observable<any> {
    console.log(url, JSON.stringify(param));
    return this.http.post(url, param);
  }
}
