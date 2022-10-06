import { Component, OnInit } from '@angular/core';
import { ProdutoService } from 'src/app/services/produto.service';
import { Produto } from 'src/app/models/produto.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

  listProdutos: Produto[] = [];
  produtosFiltrados: Produto[] = [];

  constructor(private _service: ProdutoService) {}

  ngOnInit(): void {
    this.buscarProduto();
  } 

  buscarProduto() {
    this._service.getProdutos().subscribe({
      next: (data) => {
        this.listProdutos = data;
        this.produtosFiltrados = data;
        console.log(data);
      },
      error: (e) => console.log(e),
    });
  }

  filterProdutos(categoria: string){
    for (let i = this.produtosFiltrados.length; i <= this.produtosFiltrados.length; i++) {
      console.log('categoria',categoria);
      this.produtosFiltrados = this.produtosFiltrados.filter((item) => item.categoria == categoria);
      console.log('produtosFiltrados', this.produtosFiltrados);
    }    
    this.produtosFiltrados = this.listProdutos; 
  }
}
