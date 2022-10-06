import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddClienteComponent } from './views/cliente/add-cliente/add-cliente.component';
import { AdminListClienteComponent } from './views/cliente/admin-list-cliente/admin-list-cliente.component';
import { CarrinhoComponent } from './views/carrinho/carrinho.component';
import { AddEnderecoComponent } from './views/endereco/add-endereco/add-endereco.component';
import { HomeComponent } from './views/home/home.component';
import { AddProdutoComponent } from './views/produto/add-produto/add-produto.component';
import { ListProdutoComponent } from './views/produto/list-produto/list-produto.component';
import { FinalizadoComponent } from './views/finalizado/finalizado.component';
import { AdminListProdutoComponent } from './views/produto/admin-list-produto/admin-list-produto.component';
import { AdminListPedidoComponent } from './views/pedido/admin-list-pedido/admin-list-pedido.component';
import { ChooseClienteComponent } from './views/cliente/choose-cliente/choose-cliente.component';
import { ListPedidosClienteComponent } from './views/pedido/list-pedidos-cliente/list-pedidos-cliente.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'add-produto', component: AddProdutoComponent },
  { path: 'edit-produto/:id', component: AddProdutoComponent },
  { path: 'list-produto', component: ListProdutoComponent },
  { path: 'admin-list-produto', component: AdminListProdutoComponent },
  { path: 'add-cliente', component: AddClienteComponent },
  { path: 'admin-list-cliente', component: AdminListClienteComponent },
  { path: 'edit-cliente/:id', component: AddClienteComponent }, // editar cliente (não sei se era necessário, mas fiz porque confundi com o edit endereço)
  { path: 'add-endereco', component: AddEnderecoComponent },
  { path: 'edit-endereco/:id', component: AddEnderecoComponent }, // editar endereço (esse era o correto para fazer)
  { path: 'choose-cliente', component: ChooseClienteComponent },
  { path: 'choose-cliente/:aux', component: ChooseClienteComponent },
  { path: 'carrinho', component: CarrinhoComponent },
  { path: 'finalizado', component: FinalizadoComponent },
  { path: 'admin-list-pedido', component: AdminListPedidoComponent },
  { path: 'list-pedidos-cliente', component: ListPedidosClienteComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
