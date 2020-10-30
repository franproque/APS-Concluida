CREATE DATABASE DB_LANCHONETE;
USE DB_LANCHONETE;

CREATE TABLE tb_produtos(
cod_produto BIGINT NOT NULL,
nome_produto VARCHAR(60) NOT NULL,
descricao_produto VARCHAR(150) NOT NULL,
imagem_produto LONGTEXT NOT NULL,
fg_ativo BOOLEAN NOT NULL,
CONSTRAINT pk_cod_produto_tb_produtos primary key(cod_produto)
);

CREATE TABLE tb_entrada_produto(
numero_nota BIGINT NOT NULL,
valor_unitario NUMERIC(7,2) NOT NULL,
cod_produto BIGINT NOT NULL,
quantidade NUMERIC(7,2) NOT NULL,
desconto NUMERIC(7,2),
fg_ativo BOOLEAN,
CONSTRAINT pk_numero_nota_cod_produto_tb_entrada_produto PRIMARY KEY(numero_nota,cod_produto),
CONSTRAINT fk_cod_produto_tb_entrada_produto_cod_produto_tb_produtos FOREIGN KEY (cod_produto) REFERENCES tb_produtos(cod_produto)
);

CREATE TABLE tb_produtos_precos(
cod_produtos_precos BIGINT NOT NULL,
cod_produto BIGINT NOT NULL,
preco_produto NUMERIC(7,2) NOT NULL,
fg_ativo BOOLEAN NOT NULL,
peso NUMERIC(7,2) NOT NULL,
un_medida CHAR(2) NOT NULL,
CONSTRAINT pk_cod_produtos_precos_cod_produto_tb_produtos_precos PRIMARY KEY(cod_produtos_precos, cod_produto),
CONSTRAINT fk_cod_produto_tb_produtos_precos_cod_produto_tb_produtos FOREIGN KEY(cod_produto) REFERENCES tb_produtos(cod_produto)
);

CREATE TABLE tb_usuario(
cpf_cnpj VARCHAR(14) NOT NULL,
email VARCHAR(64) NOT NULL,
nome_fantasia_nome_completo VARCHAR(100) NOT NULL,
telefone_fixo VARCHAR(10),
telefone VARCHAR(11) NOT NULL,
data_nascimento DATE NOT NULL,
fg_ativo BOOLEAN NOT NULL,
CONSTRAINT pk_cpf_cnpj_email_tb_usuario PRIMARY KEY(cpf_cnpj)
);

CREATE TABLE tb_enderecos(
cod_endereco BIGINT AUTO_INCREMENT,
cpf_cnpj VARCHAR(14) NOT NULL,
cidade VARCHAR(64) NOT NULL,
fg_ativo BOOLEAN NOT NULL,
estado VARCHAR(64) NOT NULL,
endereco VARCHAR(100) NOT NULL,
numero VARCHAR(10),
bairro VARCHAR(65) NOT NULL,
complemento VARCHAR(50),
CONSTRAINT pk_cod_endereco_cpf_cnpj PRIMARY KEY(cod_endereco, cpf_cnpj),
CONSTRAINT fk_cpf_cnpj_tb_endereco_cpf_cnpj_tb_usuario FOREIGN KEY (cpf_cnpj) REFERENCES tb_usuario(cpf_cnpj)

);

CREATE TABLE tb_gastos_mensais(
cod_gasto BIGINT NOT NULL,
descricao VARCHAR(100) NOT NULL,
valor NUMERIC(7,2) NOT NULL,
fg_ativo BOOLEAN NOT NULL,
CONSTRAINT pk_cod_gasto_tb_gastos_mensais PRIMARY KEY(cod_gasto)
);

CREATE TABLE tb_lanches(
cod_lanche BIGINT NOT NULL,
cpf_cnpj VARCHAR(14) NOT NULL,
cod_produto BIGINT NOT NULL,
quantidade BIGINT NOT NULL,
fg_ativo BOOLEAN NOT NULL,
CONSTRAINT pk_cod_lanche_cpf_cnpj_cod_produto_tb_lanches PRIMARY KEY(cod_lanche,cpf_cnpj,cod_produto),
CONSTRAINT fk_cpf_cnpj_tb_lanches_cpf_cnpj_tb_usuarios FOREIGN KEY (cpf_cnpj) REFERENCES tb_usuario(cpf_cnpj),
CONSTRAINT fk_cod_produto_tb_lanches_cod_produto_tb_produtos FOREIGN KEY (cod_produto) REFERENCES tb_produtos(cod_produto)
);


CREATE TABLE tb_pedidos(
cod_pedido BIGINT NOT NULL,
cod_lanche BIGINT NOT NULL,
preco_total NUMERIC(7,2) NOT NULL,
desconto NUMERIC(7,2),
fg_ativo BOOLEAN NOT NULL,
data_entrada DATE NOT NULL,
CONSTRAINT pk_cod_pedido_cod_lanche_tb_pedidos PRIMARY KEY(cod_pedido,cod_lanche),
CONSTRAINT fk_cod_lanche_tb_pedidos_cod_lanche_tb_lanches FOREIGN KEY(cod_lanche) REFERENCES tb_lanches(cod_lanche)
);

CREATE TABLE tb_caixa(
cod_caixa BIGINT AUTO_INCREMENT,
cod_pedido BIGINT NOT NULL,
valor NUMERIC (7,2) NOT NULL,
fg_ativo BOOLEAN NOT NULL,
data_entrada DATE NOT NULL,
CONSTRAINT pk_cod_caixa_tb_caixa PRIMARY KEY(cod_caixa),
CONSTRAINT fk_cod_pedido_tb_caixa FOREIGN KEY (cod_pedido) REFERENCES tb_pedidos(cod_pedido)

);


alter table tb_pedidos add column cpf_cnpj VARCHAR(14) NOT NULL;
ALTER TABLE tb_pedidos ADD column data_saida DATETIME;
ALTER TABLE tb_pedidos ADD COLUMN data_entregue DATETIME;