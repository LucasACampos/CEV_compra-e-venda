CREATE TABLE cev.produto (
	id int8 NOT NULL,
	descricao varchar(255) NULL,
	nome varchar(255) NULL,
	preco numeric(38, 2) NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id)
);

CREATE TABLE cev.request_log (
	id int8 NOT NULL,
	endpoint varchar(255) NULL,
	horario timestamp(6) NULL,
	response oid NULL,
	CONSTRAINT request_log_pkey PRIMARY KEY (id)
);

alter table cev.request_log alter column response type text;