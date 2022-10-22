set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Burguer King', 10, 1, utc_timestamp, utc_timestamp, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (2, 'Mc Donalds', 9.50, 1, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (3, 'Girafas', 15, 2, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (4, 'Habibs', 12, 3, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (5, 'Caravana Lanches', 11, 4, utc_timestamp, utc_timestamp, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo) values (6, 'Bar do zé', 6, 4, utc_timestamp, utc_timestamp, true);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Whopper', 'Delicioso hamburguer com carne bovina e salda', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Big King', 'Delicioso haburguer duplo com carne bovina e molho especial', 110, 1, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Big Mac', 'Delicioso haburguer duplo com carne bovina e molho especial Big Mac', 87.20, 1, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Brasileirinho', 'Arroz, feijão e file à cavalo', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Frango ao molho', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bib esfiha clássica', 'Deliciosa esfiha de carne, calabresa ou queijo', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Pastel de Belem', 'Delicioso doce portugues feito com a nata do leite', 89, 1, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Caipirinha', 'Bebida brasileira com limão açúcar, cachaça, gelo e açúcar', 8, 1, 6);

insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

insert into usuario (id, nome, email, senha, data_cadastro) values
(1, 'Fábio da Silva', 'fabio.ger@test.com', '123', utc_timestamp),
(2, 'Marta Carvalho', 'marta.vnd@test.com', '456', utc_timestamp),
(3, 'José Souza', 'jose.aux@test.com', '789', utc_timestamp),
(4, 'Carlos Martins', 'Carlos.cad@test.com', '000', utc_timestamp);