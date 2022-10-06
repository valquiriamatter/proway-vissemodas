import { Produto } from './produto.model';

export class ItemPedido {
  id?: number;
  idProduto: number;
  idPedido?: number;
  produto?: Produto;
  quantidade: number;
  valorTotalItem: number;

  constructor(idProduto: number, quantidade: number, valorTotalItem: number) {
    this.idProduto = idProduto;
    this.quantidade = quantidade;
    this.valorTotalItem = valorTotalItem;
  }
}
