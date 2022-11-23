create table restaurante_usuario (
	restaurante_id bigint not null,
	usuario_id bigint not null,
	
	primary key (restaurante_id, usuario_id),
	constraint fk_restaurante_usuario_restaurante foreign key (restaurante_id) references restaurante (id),
	constraint fk_restaurante_usuario_usuario foreign key (usuario_id) references usuario (id)
) engine=InnoDB default charset=utf8;