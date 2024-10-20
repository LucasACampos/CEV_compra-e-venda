create schema cev;

create sequence cev.produto_id_seq;
CREATE TABLE cev.produto (
	id int8 primary key default nextval('cev.produto_id_seq'),
	descricao varchar(255) NULL,
	nome varchar(255) NULL,
	preco numeric(38, 2) NULL
);

create sequence cev.request_log_id_seq;
CREATE TABLE cev.request_log (
	id int8 primary key default nextval('cev.request_log_id_seq'),
	endpoint varchar(255) NULL,
	horario timestamp(6) NULL,
	response text NULL
);

alter table cev.request_log add column argumentos text;

create sequence cev.repository_log_id_seq;
create table cev.repository_log(
	id int8 primary key default nextval('cev.repository_log_id_seq'),
	metodo varchar(255),
	argumentos text,
	horario timestamp,
	response text
);

alter table cev.request_log add column request_id uuid;
alter table cev.repository_log add column request_id uuid;
