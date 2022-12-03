alter table restaurant add active tinyint(1) not null;
update restaurant set active = true;

alter table restaurant add open tinyint(1) not null;
update restaurant set open = true;