import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { HttpClientModule } from '@angular/common/http';

// Componentes
import { AddProdutoComponent } from './views/produto/add-produto/add-produto.component';
import { ListProdutoComponent } from './views/produto/list-produto/list-produto.component';
import { HomeComponent } from './views/home/home.component';
import { NavbarComponent } from './views/shared/navbar/navbar.component';
import { FooterComponent } from './views/shared/footer/footer.component';
import { AddClienteComponent } from './views/cliente/add-cliente/add-cliente.component';
import { AdminListClienteComponent } from './views/cliente/admin-list-cliente/admin-list-cliente.component';
import { ChooseClienteComponent } from './views/cliente/choose-cliente/choose-cliente.component';
import { AddEnderecoComponent } from './views/endereco/add-endereco/add-endereco.component';
import { CarrinhoComponent } from './views/carrinho/carrinho.component';
import { FinalizadoComponent } from './views/finalizado/finalizado.component';
import { AdminListProdutoComponent } from './views/produto/admin-list-produto/admin-list-produto.component';
import { AdminListPedidoComponent } from './views/pedido/admin-list-pedido/admin-list-pedido.component';

// Paginação
import { NgxPaginationModule } from 'ngx-pagination';

// Mask
import { NgxMaskModule } from 'ngx-mask';
import { CommonModule } from '@angular/common';
import { ListPedidosClienteComponent } from './views/pedido/list-pedidos-cliente/list-pedidos-cliente.component';

@NgModule({
  declarations: [
    AppComponent,
    AddProdutoComponent,
    ListProdutoComponent,
    AddClienteComponent,
    AdminListClienteComponent,
    ChooseClienteComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    AddEnderecoComponent,
    CarrinhoComponent,
    FinalizadoComponent,
    AdminListProdutoComponent,
    AdminListPedidoComponent,
    ListPedidosClienteComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    NgxMaskModule.forRoot(),
    NgxPaginationModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}