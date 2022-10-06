drop schema vissemodas;
create schema vissemodas;
use vissemodas;

insert into produto(descricao, tamanho, valor_unitario, categoria, imagem, status) values 
('calça jogger preta', '38', 130.00, 'calças', 'assets/produtos/calca-1.png', 1),
('calça alfaiataria xadrez', '36', 140.00, 'calças', 'assets/produtos/calca-2.png', 1),
('calça jogger cinza', '42', 110.00, 'calças', 'assets/produtos/calca-3.png', 1);

insert into produto(descricao, tamanho, valor_unitario, categoria, imagem, status) values 
('camisa listrada azul', 'G', 130.00, 'camisas', 'assets/produtos/camisa-1.png', 1),
('camisa listrada colorida', 'M', 140.00, 'camisas', 'assets/produtos/camisa-2.png', 1),
('camisa preta', 'PP', 110.00, 'camisas', 'assets/produtos/camisa-3.png', 1),
('camisa cetim estampada', 'P', 180.00, 'camisas', 'assets/produtos/camisa-4.png', 1);

insert into produto(descricao, tamanho, valor_unitario, categoria, imagem, status) values 
('mochila notebook preta', 38, 130.00, 'mochilas', 'assets/produtos/mochila-1.png', 1),
('mochila tie-dye now united', 40, 140.00, 'mochilas', 'assets/produtos/mochila-2.png', 1),
('mochila preta naruto', 36, 110.00, 'mochilas', 'assets/produtos/mochila-3.png', 1),
('mochila colorida hamster', 36, 110.00, 'mochilas', 'assets/produtos/mochila-4.png', 1),
('mochila roxa unicórnio', 36, 110.00, 'mochilas', 'assets/produtos/mochila-5.png', 1);

insert into produto(descricao, tamanho, valor_unitario, categoria, imagem, status) values 
('sapato social preto', 38, 130.00, 'sapatos', 'assets/produtos/sapato-1.png', 1),
('mocassim verde musgo', 40, 140.00, 'sapatos', 'assets/produtos/sapato-2.png', 1),
('sapato social marrom', 36, 110.00, 'sapatos', 'assets/produtos/sapato-3.png', 1),
('sapato boneca nude', 36, 110.00, 'sapatos', 'assets/produtos/sapato-4.png', 1);

INSERT INTO
	produto(descricao,tamanho,valor_unitario,categoria,imagem,status)
values
	('bolsa dia a dia preta','único',130.00,'bolsas','assets/produtos/bolsa-1.jpg',1),
	('bolsa rosa choque','único',140.00,'bolsas','assets/produtos/bolsa-2.jpg',	1);

-- Ativando os produtos
update produto set status=1 where id in(1,2,3,4,5,6,7,8,9,10,11,12);

select * from cliente c ;
select * from endereco e ;
select * from produto p ;

-- MAIS INSERTS
-- abaixo gerado pelo hibernate -------------- alter TABLE item_pedido add constraint foreign key (pedido_id) references pedido (id);
-- alter TABLE item_pedido add constraint foreign key (produto_id) references produto (id);
-- alter TABLE pedido add constraint foreign key (cliente_id) references cliente (id);
-- alter TABLE pedido add constraint foreign key (endereco_entrega_id) references endereco (id);
-- INNER JOINS
select ip.quantidade,ip.valor_total,produto.descricao,pedido.situacao
from item_pedido AS ip
	INNER JOIN produto ON ip.produto_id = produto.id
	INNER JOIN pedido ON ip.pedido_id = pedido.id;

select ped.situacao,ped.valor_total,ped.quantidade_total,ped.percentual_desconto,cliente.nome,endereco.rua,endereco.bairro
from pedido AS ped
	INNER JOIN cliente ON ped.cliente_id = cliente.id
	INNER JOIN endereco ON ped.endereco_entrega_id = endereco.id;

select ende.cep,ende.cidade,ende.bairro,ende.rua,cliente.nome
from endereco AS ende
	INNER JOIN cliente ON ende.cliente_id = cliente.id;

-- CLIENTES
INSERT INTO
	cliente (cpf, cnpj, nome, tipo_cliente, endereco_id)
values
	('70237731428',null,'Rebeca Lira','FISICA',1);

INSERT INTO
	cliente (cpf, cnpj, nome, tipo_cliente, endereco_id)
values
	(	'13254894413',null,'Yuri Ferreira','FISICA',2);

INSERT INTO
	cliente (cpf, cnpj, nome, tipo_cliente, endereco_id)
values
	(null,'00802269000191','Rivenstorm','JURIDICA',3);

INSERT INTO
	cliente (cpf, cnpj, nome, tipo_cliente, endereco_id)
values
	(null,'89607746000171','Dynaworth','JURIDICA',4);

-- PRODUTOS
INSERT INTO
	produto(descricao,tamanho,valor_unitario,categoria,imagem,status)
values
	('calça jogger preta','38',130.00,'calças','assets/produtos/calca-1.png',1),
	('calça alfaiataria xadrez','36',140.00,'calças','assets/produtos/calca-2.png',1),
	('calça jogger cinza','42',110.00,'calças','assets/produtos/calca-3.png',1);

INSERT INTO
	produto(descricao,tamanho,valor_unitario,categoria,imagem,status)
values
	('camisa listrada azul','G',130.00,'camisas','assets/produtos/camisa-1.png',1),
	('camisa listrada colorida','M',140.00,'camisas','assets/produtos/camisa-2.png',	1),
	('camisa preta','PP',110.00,'camisas',	'assets/produtos/camisa-3.png',	1);

INSERT INTO
	produto(descricao,tamanho,valor_unitario,	categoria,imagem,status)
values
	('mochila notebook preta',38,130.00,	'mochilas','assets/produtos/mochila-1.png',1),
	('mochila tie-dye now united',40,140.00,'mochilas','assets/produtos/mochila-2.png',1),
	('mochila preta naruto',36,110.00,'mochilas','assets/produtos/mochila-3.png',1),
	('mochila colorida hamster',36, 110.00,'mochilas','assets/produtos/mochila-4.png',1),
	('mochila roxa unicórnio',36,110.00,'mochilas','assets/produtos/mochila-5.png',1);

INSERT INTO
	produto(descricao,tamanho,valor_unitario,categoria,imagem,status)
values
	('sapato social preto',38,130.00,'sapatos','assets/produtos/sapato-1.png',1),
	('mocassim verde musgo',40,140.00,'sapatos','assets/produtos/sapato-2.png',1),
	('sapato social marrom',36,110.00,'sapatos','assets/produtos/sapato-3.png',1),
	('sapato boneca nude',36,110.00,'sapatos','assets/produtos/sapato-4.png',1);

INSERT INTO
	produto(descricao,tamanho,valor_unitario,categoria,imagem,status)
values
	('bolsa dia a dia preta','único',130.00,'bolsas','assets/produtos/bolsa-1.jpg',1),
	('bolsa rosa choque','único',140.00,'bolsas','assets/produtos/bolsa-2.jpg',	1),

-- PEDIDOS
INSERT INTO
	pedido (cliente_id,situacao,valor_total,quantidade_total,percentual_desconto)
values
	(1, 'FECHADO', 259.90, 3, 30.00),
	(2, 'ABERTO', 400.00, 7, 15.00);

-- ITENS PEDIDOS
INSERT INTO
	item_pedido (produto_id, pedido_id, quantidade, valor_por_item)
values
	(1, 1, 5, 259.90),
	(2, 2, 3, 400.00);
	
