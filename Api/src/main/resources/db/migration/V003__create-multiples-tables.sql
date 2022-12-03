create table cluster (
	id bigint not null auto_increment,
	name varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table permission (
	id bigint not null auto_increment,
	description varchar(60) not null,
	name varchar(100) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table cluster_permission (
	cluster_id bigint not null,
	permission_id bigint not null,
	
	primary key (cluster_id, permission_id)
) engine=InnoDB default charset=utf8;

create table product (
	id bigint not null auto_increment,
	restaurant_id bigint not null,
	name varchar(80) not null,
	description text not null,
	price decimal(10,2) not null,
	active tinyint(1) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment,
	kitchen_id bigint not null,
	name varchar(80) not null,
	rate_shipping decimal(10,2) not null,
	update_date datetime not null,
	creation_date datetime not null,
	
	address_city_id bigint,
	address_zip_code varchar(9),
	address_public_place varchar(100),
	address_number varchar(20),
	address_complement varchar(60),
	address_district varchar(60),
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table payment_method (
	id bigint not null auto_increment,
	description varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_payment_method (
	restaurant_id bigint not null,
	payment_method_id bigint not null,
	
	primary key (restaurant_id, payment_method_id )
) engine=InnoDB default charset=utf8;

create table user (
	id bigint not null auto_increment,
	name varchar(80) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	date_register datetime not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table user_cluster (
	user_id bigint not null,
	cluster_id bigint not null,
	
	primary key (user_id, cluster_id)
) engine=InnoDB default charset=utf8;

