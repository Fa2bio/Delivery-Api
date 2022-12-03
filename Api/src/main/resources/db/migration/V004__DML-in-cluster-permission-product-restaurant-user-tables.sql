alter table cluster_permission add constraint fk_cluster_permission_permission
foreign key (permission_id) references permission (id);

alter table cluster_permission add constraint fk_cluster_permission_cluster
foreign key (cluster_id) references cluster (id);

alter table product add constraint fk_product_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table restaurant add constraint fk_restaurant_kitchen
foreign key (kitchen_id) references kitchen (id);

alter table restaurant add constraint fk_restaurant_city
foreign key (address_city_id) references city (id);

alter table restaurant_payment_method add constraint fk_rest_pay_metho_pay_metho
foreign key (payment_method_id) references payment_method (id);

alter table restaurant_payment_method add constraint fk_rest_pay_metho_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table user_cluster add constraint fk_user_cluster_cluster
foreign key (cluster_id) references cluster (id);

alter table user_cluster add constraint fk_user_cluster_user
foreign key (user_id) references user (id);