create table orderr (
  id bigint not null auto_increment,
  subtotal decimal(10,2) not null,
  rate_shipping decimal(10,2) not null,
  total_amount decimal(10,2) not null,

  restaurant_id bigint not null,
  user_client_id bigint not null,
  payment_method_id bigint not null,
  
  address_city_id  bigint(20) not null,
  address_zip_code varchar(9) not null,
  address_public_place varchar(100) not null,
  address_number varchar(20) not null,
  address_complement varchar(60) null,
  address_district varchar(60) not null,
  
  status varchar(10) not null,
  creation_date datetime not null,
  confirmation_date datetime null,
  cancellation_date datetime null,
  delivery_date datetime null,

  primary key (id),

  constraint fk_orderr_restaurant foreign key (restaurant_id) references restaurant (id),
  constraint fk_orderr_user_client foreign key (user_client_id) references user (id),
  constraint fk_orderr_payment_method foreign key (payment_method_id) references payment_method (id)
) engine=InnoDB default charset=utf8;

create table item_orderr (
  id bigint not null auto_increment,
  quantity smallint(6) not null,
  unit_price decimal(10,2) not null,
  total_price decimal(10,2) not null,
  note varchar(255) null,
  orderr_id bigint not null,
  product_id bigint not null,
  
  primary key (id),
  unique key uk_item_orderr_product (orderr_id, product_id),

  constraint fk_item_orderr_orderr foreign key (orderr_id) references orderr (id),
  constraint fk_item_orderr_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;