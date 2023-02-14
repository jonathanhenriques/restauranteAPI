


create table pedido (

	id BIGINT NOT NULL AUTO_INCREMENT,
	subtotal DECIMAL(10,2) NOT NULL,
	taxa_frete DECIMAL(10,2) NOT NULL,
	valor_total DECIMAL(10,2) NOT NULL,


	endereco_cidade_id BIGINT(20) NOT NULL,
	endereco_cep VARCHAR(9) NOT NULL,
	endereco_logradouro VARCHAR(100) NOT NULL,
	endereco_numero VARCHAR(20) NOT NULL,
	endereco_complemento VARCHAR(60) NULL,
	endereco_bairro VARCHAR(60) NOT NULL,

	status  VARCHAR(10) NOT NULL,
	data_criacao datetime NOT NULL,
	data_confirmacao datetime NULL,
	data_cancelamento datetime NULL,
	data_entrega datetime NULL,

	PRIMARY KEY(id)

)engine=InnoDB default charset=utf8mb4;



CREATE TABLE item_pedido (
	id BIGINT NOT NULL AUTO_INCREMENT,
	quantidade SMALLINT(6) NOT NULL,
	preco_unitario DECIMAL(10,2) NOT NULL,
	preco_total DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(255),

	PRIMARY KEY(id)
)engine=InnoDB default charset=utf8mb4;




alter table pedido ADD constraint fk_pedido_endereco_cidade
foreign key (endereco_cidade_id) references cidade(id);

alter table pedido ADD restaurante_id BIGINT;
alter table pedido ADD constraint fk_pedido_restaurante
foreign key (restaurante_id) references restaurante(id);

alter table pedido ADD usuario_cliente_id BIGINT;
alter table pedido add constraint fk_pedido_usuario_cliente
foreign key (usuario_cliente_id) references usuario(id);

alter table pedido ADD forma_pagamento_id BIGINT;
alter table pedido ADD constraint fk_pedido_forma_pagamento
foreign key (forma_pagamento_id) references forma_pagamento(id);


alter table item_pedido ADD pedido_id BIGINT NOT NULL;
alter table item_pedido ADD constraint fk_item_pedido_pedido
foreign key (pedido_id) references pedido(id);

alter table item_pedido ADD produto_id BIGINT NOT NULL;
alter table item_pedido ADD constraint fk_item_pedido_produto
foreign key (produto_id) references produto(id);
