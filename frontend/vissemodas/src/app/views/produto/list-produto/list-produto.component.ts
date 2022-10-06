import { Component, OnInit } from '@angular/core';
import { ItemPedido } from 'src/app/models/itemPedido.model';
import { Produto } from 'src/app/models/produto.model';
import { ProdutoService } from 'src/app/services/produto.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-list-produto',
  templateUrl: './list-produto.component.html',
  styleUrls: ['./list-produto.component.css'],
})
export class ListProdutoComponent implements OnInit {
  // O que é exibido ao usuário
  listaProdutos: Produto[] = [];
  // O que o usuário adiciona ao carrinho
  carrinho: Produto[] = [];

  paginaAtual : number = 1;
  contador : number = 6;

  // Modelo de um itemPedido para fazer a lista itensPedido
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

  constructor(private _service: ProdutoService, private _toastr: ToastrService) {

    console.log('Aplicação iniciada, carregando dados...');
    this.listaProdutos = [];
  }

  ngOnInit(): void {
    this.listarProdutos();
  }

  // Buscando os produtos do banco de dados
  listarProdutos() {
    return this._service.getProdutos().subscribe({
      next: (data) => {
        // Alimentando os produtos do banco na nossa listaProdutos
        this.listaProdutos = data;
      },
      error: (e) => console.log(e),
    });
  }

  // Método invocado quando o cliente clica no botão 'Adicionar ao carrinho'
  addToCart(produto: Produto) {
    console.log('addToCard', produto);
    this._toastr.success('Adicionada ao carrinho', produto.descricao);
    this.carrinho.push(produto);
    this.setToLocalStorage(produto);
  }

  setToLocalStorage(produto: Produto) {
    // Atribuindo produto ao itemPedido.produto
    this.itemPedido.produto = produto;
    this.itemPedido.quantidade = 1;
    this.itemPedido.idProduto = produto.id!;
    this.itemPedido.valorTotalItem = produto.valorUnitario;

    this.itensPedido.push(this.itemPedido);

    // Limpando o ItemPedido
    // que é usado para moldar e ser mandado à nossa lista itensPedido
    this.cleanItemPedido();

    localStorage.setItem('itensPedido', JSON.stringify(this.itensPedido));
  }

  cleanItemPedido() {
    this.itemPedido = {
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
  }
}
