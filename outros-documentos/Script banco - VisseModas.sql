CREATE SCHEMA vissemodas;

use vissemodas;

CREATE TABLE cliente (
	id bigint not null auto_increment,
	endereco_id bigint,
	nome varchar(40),
	tipo_cliente varchar(8),
	cpf varchar(11),
	cnpj varchar(14),
	primary key (id)
);

--
CREATE TABLE endereco (
	id bigint not null auto_increment,
	bairro varchar(50),
	cep varchar(10),
	cidade varchar(40),
	estado varchar(20),
	logradouro varchar(50),
	numero int,
	primary key (id)
);

--
CREATE TABLE item_pedido (
	id bigint not null auto_increment,
	produto_id bigint,
	pedido_id bigint,
	quantidade int,
	valor_por_item decimal(19,2),
	primary key (id)
);

--
CREATE TABLE pedido (
	id bigint not null auto_increment,
	cliente_id bigint,
	data Date,
	situacao varchar(7),
	valor_total decimal(19,2),
	quantidade_total int,
	percentual_desconto Double(5,2),
	primary key (id)
);

--
CREATE TABLE produto (
	id bigint not null auto_increment,
	categoria varchar(50),
	descricao varchar(50),
	imagem varchar(50),
	status bit(1),
	tamanho varchar(10),
	valor_unitario decimal(19,2),
	primary key (id)
);

select * from cliente;
select * from item_pedido;
select * from pedido;
select * from produto;
select * from endereco;

-- FOREIGN KEYS alter TABLE item_pedido add constraint foreign key
alter table item_pedido add
	constraint foreign key (produto_id) references produto(id);

alter table item_pedido add
	constraint foreign key (pedido_id) references pedido(id);

alter table pedido add
	constraint foreign key (cliente_id) references cliente(id);

alter table cliente add
	constraint foreign key (endereco_id) references endereco(id);

