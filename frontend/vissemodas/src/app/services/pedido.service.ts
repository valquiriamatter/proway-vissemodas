import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pedido } from '../models/pedido.model';

@Injectable({
  providedIn: 'root',
})
export class PedidoService {
  private readonly url = 'http://localhost:8080/pedidos';

  constructor(private _http: HttpClient) {}

  getOnePedido(id: any): Observable<any> {
    return this._http.get(`${this.url}/${id}`);
  }

  getPedidos(): Observable<any> {
    return this._http.get(this.url);
  }

  postPedido(pedido: Pedido): Observable<any> {
    return this._http.post(this.url, pedido);
  }

  deletePedido(id: any): Observable<any> {
    return this._http.delete(`${this.url}/${id}`);
  }
}
