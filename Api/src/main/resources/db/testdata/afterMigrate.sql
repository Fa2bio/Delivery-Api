set foreign_key_checks = 0;

delete from city;
delete from kitchen;
delete from state;
delete from payment_method;
delete from cluster;
delete from cluster_permission;
delete from permission;
delete from product;
delete from orderr;
delete from item_orderr;
delete from restaurant;
delete from restaurant_payment_method;
delete from restaurant_user_responsible;
delete from user;
delete from user_cluster;
delete from photo_product;

set foreign_key_checks = 1;

alter table city auto_increment = 1;
alter table kitchen auto_increment = 1;
alter table state auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table cluster auto_increment = 1;
alter table orderr auto_increment = 1;
alter table item_orderr auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table user auto_increment = 1;

insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');
insert into kitchen (id, name) values (3, 'Argentina');
insert into kitchen (id, name) values (4, 'Brasileira');


insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, name, rate_shipping, kitchen_id, creation_date, update_date, active, open,
address_city_id, address_zip_code, address_public_place, address_number, address_district) 
values (1, 'Burguer King', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
 
insert into restaurant (id, name, rate_shipping, kitchen_id, creation_date, update_date, active, open) values (2, 'Mc Donalds', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id, name, rate_shipping, kitchen_id, creation_date, update_date, active, open) values (3, 'Girafas', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id, name, rate_shipping, kitchen_id, creation_date, update_date, active, open) values (4, 'Habibs', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id, name, rate_shipping, kitchen_id, creation_date, update_date, active, open) values (5, 'Caravana Lanches', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id, name, rate_shipping, kitchen_id, creation_date, update_date, active, open) values (6, 'Bar do zé', 6, 4, utc_timestamp, utc_timestamp, true, true);

insert into payment_method (id, description) values (1, 'Cartão de crédito');
insert into payment_method (id, description) values (2, 'Cartão de débito');
insert into payment_method (id, description) values (3, 'Dinheiro');

insert into permission (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into product (name, description, price, active, restaurant_id) values ('Whopper', 'Delicioso hamburguer com carne bovina e salda', 28.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Big King', 'Delicioso haburguer duplo com carne bovina e molho especial', 25.9, 1, 1);

insert into product (name, description, price, active, restaurant_id) values ('Big Mac', 'Delicioso haburguer duplo com carne bovina e molho especial Big Mac', 37.20, 1, 2);

insert into product (name, description, price, active, restaurant_id) values ('Brasileirinho', 'Arroz, feijão e file à cavalo', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Frango ao molho', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into product (name, description, price, active, restaurant_id) values ('Bib esfiha clássica', 'Deliciosa esfiha de carne, calabresa ou queijo', 0.99, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('Pastel de Belem', 'Delicioso doce portugues feito com a nata do leite', 2.89, 0, 4);

insert into product (name, description, price, active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into product (name, description, price, active, restaurant_id) values ('Caipirinha', 'Bebida brasileira com limão açúcar, cachaça, gelo e açúcar', 8, 1, 6);

insert into cluster (name) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

insert into user (id, name, email, password , date_register) values
(1, 'Fa2bio', 'fabio.ger@test.com', '123', utc_timestamp),
(2, 'Marta Carvalho', 'marta.vnd@test.com', '456', utc_timestamp),
(3, 'José Souza', 'jose.sec@test.com', '789', utc_timestamp),
(4, 'Carlos Martins', 'Carlos.cad@test.com', '000', utc_timestamp);

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into cluster_permission (cluster_id, permission_id) values (1,1), (1,2), (2,1), (3,1), (4,1), (4,2);

insert into user_cluster (user_id, cluster_id) values (1,1), (2,2), (3,3), (4,4);

insert into restaurant_user_responsible (restaurant_id, user_id) values (1,1), (2,2), (3,3), (4,4), (5,2), (6,3),(1,4);

insert into orderr (id, uui_code, restaurant_id, user_client_id, payment_method_id, 
					address_city_id , address_zip_code, address_public_place, address_number, address_complement, address_district,
	                status, creation_date, subtotal, rate_shipping, total_amount)
values (1, '5a76ddc6-fc45-468d-8b8d-bc8bef3462b4', 1, 1, 1, 
		1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
        'CREATED', utc_timestamp, 298.90, 10, 308.90);

insert into item_orderr (id, orderr_id, product_id, quantity, unit_price, total_price, note)
values (1, 1, 1, 1, 28.9, 28.9, null);

insert into item_orderr (id, orderr_id, product_id, quantity, unit_price, total_price, note)
values (2, 1, 2, 2, 25.9, 51.8, 'Com mais queijo, por favor');

insert into orderr (id, uui_code, restaurant_id, user_client_id, payment_method_id, 
					address_city_id , address_zip_code, address_public_place, address_number, address_complement, address_district,
	                status, creation_date, subtotal, rate_shipping, total_amount)	                
values (2, 'bfd4c484-fb0f-4c2d-ad1c-2b42e9cccd58', 1, 1, 1, 
		1, '18400-000', 'Rua Marçal Azul', '310', 'Casa 81', 'Brasil',
        'CREATED', '2022-10-31 21:10:00', 188.90, 10, 198.90);
        
insert into item_orderr (id, orderr_id, product_id, quantity, unit_price, total_price, note)
values (3, 2, 6, 1, 0.99, 0.99, 'Ao ponto');


insert into orderr (id, uui_code, restaurant_id, user_client_id, payment_method_id, 
					address_city_id , address_zip_code, address_public_place, address_number, address_complement, address_district,
	                status, creation_date,confirmation_date,delivery_date, subtotal, rate_shipping, total_amount)	                
values (3, 'b5741512-8fbc-47fa-9ac1-b530354fc0ff', 1, 1, 1, 
		1, '18400-000', 'Rua Natal', '200', 'null', 'Brasil',
        'DELIVERED', '2022-11-01 21:10:00', '2022-11-01 21:11:45','2022-11-01 21:55:44', 110.00, 10, 120.00);
        
insert into item_orderr (id, orderr_id, product_id, quantity, unit_price, total_price, note)
values (4, 3, 2, 1, 110, 110, null);

insert into orderr (id, uui_code, restaurant_id, user_client_id, payment_method_id, 
					address_city_id , address_zip_code, address_public_place, address_number, address_complement, address_district,
	                status, creation_date,confirmation_date,delivery_date, subtotal, rate_shipping, total_amount)	                
values (4, '5c621c9a-ba61-4454-8631-8aabefe58dc2', 1, 2, 1, 
		1, '78458-010', 'Rua Acre', '398', 'Quadra 12', 'Brasil',
        'DELIVERED', '2022-11-12 01:34:04', '2022-11-12 12:11:45','2022-11-12 12:55:44', 174.40, 5, 179.40);
        
insert into item_orderr (id, orderr_id, product_id, quantity, unit_price, total_price, note)
values (5, 4, 3, 2, 87.2, 174.4, null);
