create table restaurant_user_responsible (
	restaurant_id bigint not null,
	user_id bigint not null,
	
	primary key (restaurant_id, user_id),
	constraint fk_restaurant_user_restaurant foreign key (restaurant_id) references restaurant (id),
	constraint fk_restaurant_user_user foreign key (user_id) references user (id)
) engine=InnoDB default charset=utf8;