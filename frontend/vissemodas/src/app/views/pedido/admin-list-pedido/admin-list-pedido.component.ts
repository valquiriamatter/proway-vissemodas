import { Component, OnInit } from '@angular/core';
import { Pedido } from 'src/app/models/pedido.model';
import { PedidoService } from 'src/app/services/pedido.service';

@Component({
  selector: 'app-admin-list-pedido',
  templateUrl: './admin-list-pedido.component.html',
  styleUrls: ['./admin-list-pedido.component.css']
})
export class AdminListPedidoComponent implements OnInit {

  listPedidos: Pedido[] = [];

  constructor(private _service: PedidoService) { }

  ngOnInit(): void {
    this.buscarPedidos();
  }

  buscarPedidos() {
    this._service.getPedidos().subscribe({
      next: (data) => {this.listPedidos = data; console.log(data)},
      error: (e) => console.log(e)
    });
  }

}
