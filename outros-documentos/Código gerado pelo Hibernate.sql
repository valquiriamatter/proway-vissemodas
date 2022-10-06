-- Código gerado pelo Hibernate
-- spring.jpa.show-sql=true
-- prettier-ignore
create table cliente (
    id bigint not null auto_increment,
    cnpj varchar(255),
    cpf varchar(255),
    nome varchar(255),
    tipo_cliente varchar(255),
    primary key (id)
) engine = InnoDB --
create table endereco (
    id bigint not null auto_increment,
    bairro varchar(255),
    cep varchar(255),
    cidade varchar(255),
    estado varchar(255),
    logradouro varchar(255),
    numero integer,
    primary key (id)
) engine = InnoDB --
create table item_pedido (
    id bigint not null auto_increment,
    quantidade integer,
    valor_item decimal(19, 2),
    pedido_id bigint,
    produto_id bigint,
    primary key (id)
) engine = InnoDB --
create table pedido (
    id bigint not null auto_increment,
    data date,
    percentual_desconto double precision,
    quantidade_total integer,
    situacao varchar(255),
    valor_total decimal(19, 2),
    cliente_id bigint,
    endereco_entrega_id bigint,
    primary key (id)
) engine = InnoDB --
create table produto (
    id bigint not null auto_increment,
    categoria varchar(255),
    descricao varchar(255),
    imagem varchar(255),
    status bit,
    tamanho varchar(255),
    valor_unitario decimal(19, 2),
    primary key (id)
) engine = InnoDB --
alter table
    item_pedido
add
    constraint FK60ym08cfoysa17wrn1swyiuda foreign key (pedido_id) references pedido (id) --
alter table
    item_pedido
add
    constraint FKtk55mn6d6bvl5h0no5uagi3sf foreign key (produto_id) references produto (id) --
alter table
    pedido
add
    constraint FK30s8j2ktpay6of18lbyqn3632 foreign key (cliente_id) references cliente (id) --
alter table
    pedido
add
    constraint FKcrxxe5rpllxbh0sfi4a6rwphb foreign key (endereco_entrega_id) references endereco (id)