import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produto } from '../models/produto.model';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  private readonly url = 'http://localhost:8080/produtos';

  constructor(private _http: HttpClient) {}

  getOneProduto(id: any): Observable<any> { 
    return this._http.get(`${this.url}/${id}`);
  }

  getProdutos(): Observable<any> {
    return this._http.get(this.url);
  }

  getTodosProdutos(): Observable<any> {
    return this._http.get(`${this.url}/todos`);
  }

  postProduto(produto: Produto): Observable<any> {
    return this._http.post(this.url, produto);
  }

  deleteProduto(id: any): Observable<any> {
    return this._http.delete(`${this.url}/${id}`);
  }

  putProduto(id: any, produto: Produto): Observable<any> {
    return this._http.put(`${this.url}/${id}`, produto);
  }
}
