import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente.model';
import { Pedido } from 'src/app/models/pedido.model';
import { Produto } from 'src/app/models/produto.model';
import { PedidoService } from 'src/app/services/pedido.service';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-list-pedidos-cliente',
  templateUrl: './list-pedidos-cliente.component.html',
  styleUrls: ['./list-pedidos-cliente.component.css']
})
export class ListPedidosClienteComponent implements OnInit {

  // TODO: Puxar do itemPedidos de acordo com o cliente
  // vai ser um componente usado no choose-cliente
  // listando abaixo os pedidos

  // TODO: ajustar o choose-cliente para verificar se é compra ou se é meusPedidos do cliente

  listPedidos: Pedido[] = [];
  listaFiltrada: Pedido[] = []
  listaProdutos: Produto[] = []
  tituloTexto: string = "Meus pedidos"
  

   // Cliente selecionado do 'choose-cliente'
   sessionCliente: Cliente = {
    nome: '',
    tipoCliente: '',
    idEndereco: 0
  };
  // Boolean para verificar se já existe um cliente
  hasCliente: boolean = false;

  constructor(private _servicePedido: PedidoService, private _serviceProduto: ProdutoService) { 
    
  }

  ngOnInit(): void {
    this.buscarPedidos();
    this.listaCompletaDosProdutos();
  }
  buscarPedidos = () =>{
    this._servicePedido.getPedidos().subscribe({
      next: (data) => {this.listPedidos = data; console.log(data); this.buscarCliente();
      },
      error: (e) => console.log(e)
    });
  }

  buscarCliente() {
    if (localStorage.getItem('client')) {
      this.hasCliente = true;
      this.sessionCliente = JSON.parse(localStorage.getItem('client') || '{}');
      this.listaFiltrada = this.listPedidos.filter((item) => item.nomeCliente == this.sessionCliente.nome)
      this.tituloTexto = `Pedidos de ${this.sessionCliente.nome.split(" ")[0]}`
    } else {
      this.hasCliente = false;
      this.sessionCliente = {
        nome: '',
        tipoCliente: '',
        idEndereco: 0
      };
      this.tituloTexto = "Meus pedidos"
    }
    console.log('sessionCliente', this.sessionCliente);
  }

  listaCompletaDosProdutos() {
    return this._serviceProduto.getProdutos().subscribe({
      next: (data) => {
        // Alimentando os produtos do banco na nossa listaProdutos
        this.listaProdutos = data;
      },
      error: (e) => console.log(e),
    });

}

selecionarProduto(id: number): Produto[]{
  console.log(this.listaProdutos.filter((item) => item.id == id))
  return this.listaProdutos.filter((item) => item.id == id)
}
} 