drop schema vissemodas;
create schema vissemodas;
use vissemodas;

-- Endereços
insert into endereco (cep, estado, cidade, bairro, logradouro, numero) values
('97032040', 'PE', 'Recife',  'Boa Viagem', 'Rua Bernardo Cohem', 21),
('27977580', 'RJ', 'Macaé',  'Cabiúnas', 'Rua Fonseca', 154),
('51021040', 'RS', 'Santa Maria', 'Tancredo Neves', 'Av. Eng. Domingos Ferreira', 18);

insert into endereco (cep, estado, cidade, bairro, logradouro, numero) values
('76629970', 'GO', 'Goiás',  'Setor Central', 'Praça Doutor Brasil Caiado', 46),
('77019128', 'TO', 'Palmas', 'Plano Diretor Sul', 'Quadra 1105 Sul Alameda', 19);

SELECT * FROM endereco;
SELECT * FROM cliente;

drop table endereco;
drop table cliente;

-- Clientes
insert into cliente (cpf, cnpj, nome, tipo_cliente, endereco_id) values ('70237731428', null, 'Jana Ursel', 'FISICA', 1);
insert into cliente (cpf, cnpj, nome, tipo_cliente, endereco_id) values ('13254894413', null, 'Zayn ad-Din Aliyyah', 'FISICA', 2);

insert into cliente (cpf, cnpj, nome, tipo_cliente, endereco_id) values (null, '00802269000191', 'Rivenstorm', 'JURIDICA', 3);
insert into cliente (cpf, cnpj, nome, tipo_cliente, endereco_id) values (null, '89607746000171', 'Dynaworth', 'JURIDICA', 4);