alter table orderr add uui_code varchar(36) not null after id;
update orderr set uui_code = uuid();
alter table orderr add constraint uk_orderr_uui_code unique (uui_code);