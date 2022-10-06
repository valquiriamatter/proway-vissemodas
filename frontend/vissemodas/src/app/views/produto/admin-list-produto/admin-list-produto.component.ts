import { Component, OnInit } from '@angular/core';
import { Produto } from 'src/app/models/produto.model';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-admin-list-produto',
  templateUrl: './admin-list-produto.component.html',
  styleUrls: ['./admin-list-produto.component.css'],
})
export class AdminListProdutoComponent implements OnInit {
  listaProdutos: Produto[] = [];

  constructor(private _service: ProdutoService) {}

  ngOnInit(): void {
    this.listarProdutos();
  }

  listarProdutos() {
    return this._service.getTodosProdutos().subscribe({
      next: (data) => {
        console.log('getTodosProdutos',data);
        this.listaProdutos = data;
      },
      error: (e) => console.log(e),
    });
  }

  desativarProduto(id: any) {
    this._service.deleteProduto(id).subscribe({
      next: (data) => {
        console.log('Produto deletado');
        this.listarProdutos();
      },
      error: (e) => console.log(e),
    });
  }
}
