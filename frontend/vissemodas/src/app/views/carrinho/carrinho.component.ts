import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ItemPedido } from 'src/app/models/itemPedido.model';
import { Produto } from 'src/app/models/produto.model';
import { ToastrService } from 'ngx-toastr';
import { Pedido } from 'src/app/models/pedido.model';
import { Cliente } from 'src/app/models/cliente.model';
import { PedidoService } from 'src/app/services/pedido.service';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css'],
})
export class CarrinhoComponent implements OnInit {
  // Vem do localStorage
  listProdutos: Produto[] = [];

  // Preparação.
  // Cada produto tem um ItemPedido, pois,
  // no ItemPedido vamos ter a quantidade
  // que multiplicamos pelo valorPorItem
  // para 'fecharmos' o pedido
  itemPedido: ItemPedido = {
    produto: {
      descricao: '',
      tamanho: '',
      valorUnitario: 0,
      status: true,
      imagem: '',
      categoria: '',
    },
    quantidade: 0,
    valorTotalItem: 0,
    idProduto: 0
  };

  // De fato a nossa lista de itens
  itensPedido: ItemPedido[] = [];

  count_qtdTotal: number = 0;
  count_valorTotal: number = 0;
  count_desconto: number = 0;

  // Cliente selecionado do 'choose-cliente'
  sessionCliente: Cliente = {
    nome: '',
    tipoCliente: '',
    idEndereco: 0
  };
  // Boolean para verificar se já existe um cliente
  hasCliente: boolean = false;

  // Moldando o pedido para enviar para o backend
  pedido: Pedido = {
    idCliente: 0,
    listaItens: [],
    valorTotal: 0,
    quantidadeTotal: 0,
    percentualDesconto: 0,
  };

  // Texto do botão, 'finalizar' ou 'escolher cliente'
  botaoTexto: String = 'Finalizar';
  // Texto título principal, 'meu carrinho' ou carrinho de 'fulano'
  tituloTexto: String = "Meu carrinho";

  total: String = "Total"

  constructor(
    private _router: Router,
    private _toastr: ToastrService,
    private _servicePedido: PedidoService
  ) {}

  ngOnInit(): void {
    this.buscarItemPedido();
    this.buscarCliente();
    this.cleanCount();
    this.setCountValues();
  }

  buscarItemPedido() {
    if (localStorage.getItem('itensPedido')) {
      this.itensPedido = JSON.parse(
        localStorage.getItem('itensPedido') || '{}'
      );

      // Atribuindo ao itensPedido o produto
      for (let i = 0; i < this.listProdutos.length; i++) {
        let currentProduto = this.listProdutos[i];
        console.log('currentProduto', currentProduto);
        this.itemPedido.produto = currentProduto;
        this.itemPedido.idProduto = currentProduto.id!;
        this.itemPedido.quantidade = 1;
        this.itemPedido.valorTotalItem = currentProduto.valorUnitario * this.itemPedido.quantidade;

        this.itensPedido.push(this.itemPedido);
      }

      this.setCountValues();

      console.log('itensPedido', this.itensPedido);
    } else {
      this.listProdutos = [];
      this.cleanCount();
    }
  }

  buscarCliente() {
    if (localStorage.getItem('client')) {
      this.hasCliente = true;
      this.botaoTexto = 'Finalizar';
      this.tituloTexto = `Carrinho de ${JSON.parse(localStorage.getItem('client') || '{}').nome.split(' ')[0]}`;
      this.sessionCliente = JSON.parse(localStorage.getItem('client') || '{}');
    } else {
      this.hasCliente = false;
      this.botaoTexto = 'Continuar';
      this.sessionCliente = {
        nome: '',
        tipoCliente: '',
        idEndereco: 0
      };
    }
    console.log('sessionCliente', this.sessionCliente);
  }

  removerItemPedido(id: any) {
    // filter() retorna uma lista que satisfaz a condição,
    // neste caso, ser diferente do id passado por parâmetro.
    // Logo, retorna tudo exceto o que tiver esse id
    this.itensPedido = this.itensPedido.filter(
      (item) => item.produto!.id !== id
    );

    this.setCountValues();

    // Retornando uma mensagem ao usuário
    this._toastr.error('Removido do carrinho', 'Produto');

    // Atribuindo essa nova lista filtrada para o localStorage
    localStorage.setItem('itensPedido', JSON.stringify(this.itensPedido));
  }

  handleChangeQTD(qtd: string, id: any) {
    for (let i = 0; i < this.itensPedido.length; i++) {
      if (this.itensPedido[i].produto!.id == id) {
        this.itensPedido[i].quantidade = parseInt(qtd);
      }
    }

    this.setCountValues();

    // Atribuindo a lista com a QTD alterada para o localStorage
    localStorage.setItem('itensPedido', JSON.stringify(this.itensPedido));
  }

  setCountValues() {
    this.cleanCount();
    for (let i = 0; i < this.itensPedido.length; i++) {
      let currentItem = this.itensPedido[i];
      this.count_valorTotal +=
        currentItem.quantidade * currentItem.valorTotalItem;
      this.count_qtdTotal += currentItem.quantidade;
    }

    // Desconto de 20% se a compra for maior ou igual a 500 reais
    if (this.count_valorTotal >= 500) {
      this.count_desconto = 0.2;
      this.total = "Subtotal"
    }
    else{
        this.total = "Total"
      
    }
  }

  cleanCount() {
    this.count_qtdTotal = 0;
    this.count_valorTotal = 0;
    this.count_desconto = 0;
  }

  finalizarCompra() {
    this.setCountValues();

    this.pedido.listaItens = this.itensPedido;
    //this.pedido.situacao = 'ABERTO';

    this.pedido.quantidadeTotal = this.count_qtdTotal;
    this.pedido.percentualDesconto = this.count_desconto;
    this.pedido.valorTotal =
      this.count_valorTotal - this.count_valorTotal * this.count_desconto;

    this.pedido.idCliente = this.sessionCliente.id;
   //this.pedido.idEndereco = this.sessionCliente.enderecoDTO!.id;

    // TODO: enviar a item-pedido primeiro



    this._servicePedido.postPedido(this.pedido).subscribe({
      next: (data) => console.log(data),
      error: (e) => console.log(e),
    });

    // Testando como está o pedido
    console.log('pedido', this.pedido);

    // Limpando as somas após enviar o pedido
    this.cleanCount();

    // Todo: limpar o localStorage depois de enviar o pedido
    localStorage.clear();

    this._router.navigate(['/finalizado']);
  }

  // Verifica se há cliente ou não
  // Caso não haja, fazer o 'choose-cliente'
  // Caso sim, finalizar a compra
  handleCompra() {
    if (this.hasCliente) {
      // TODO: terminar de implementar
      this.finalizarCompra();
    } else {
      this._router.navigate(['choose-cliente']);
    }
  }
}
