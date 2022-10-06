import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Cliente } from '../models/cliente.model';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  private readonly url = 'http://localhost:8080/clientes';

  constructor(private _http: HttpClient) {}

  getClientes(): Observable<any> {
    return this._http.get(this.url);
  }

  postCliente(cliente: Cliente): Observable<any> {
    return this._http
      .post(this.url, cliente)
      .pipe(catchError(this.handleError));
  }

  handleError(error: any) {
    // return throwError(error.message || 'Server error');
    return throwError(error.error.errors[0].defaultMessage || 'Server error');
  }

  deleteCliente(id: any): Observable<any> {
    return this._http.delete(`${this.url}/${id}`);
  }

  getOneCliente(id: any): Observable<any> {
    return this._http.get(`${this.url}/${id}`);
  }

  editCliente(id: any, cliente: Cliente): Observable<any> {
    return this._http.put(`${this.url}/${id}`, cliente);
  }

}
