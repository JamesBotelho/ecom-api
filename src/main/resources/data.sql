INSERT INTO CLIENTE(NOME, CPF, EMAIL, DATA_NASCIMENTO, SENHA) VALUES ('TESTE', '48313525606', 'teste@email.com', '2021-06-03', '$2y$12$WiThuE3IhFYwPI8WJ8/Hberwp2XWO.TOONYOteNgSrCCHqMxRxG4e');
INSERT INTO CLIENTE(NOME, CPF, EMAIL, DATA_NASCIMENTO, SENHA) VALUES ('TESTE DOIS', '58139705098', 'teste2@email.com', '2021-06-03', '$2y$12$WiThuE3IhFYwPI8WJ8/Hberwp2XWO.TOONYOteNgSrCCHqMxRxG4e');
INSERT INTO CLIENTE(NOME, CPF, EMAIL, DATA_NASCIMENTO, SENHA) VALUES ('TESTE TRES', '79234967062', 'teste3@email.com', '2021-06-03', '$2y$12$WiThuE3IhFYwPI8WJ8/Hberwp2XWO.TOONYOteNgSrCCHqMxRxG4e');

INSERT INTO CATEGORIA(CATEGORIA) VALUES ('Categoria Teste');
INSERT INTO CATEGORIA(CATEGORIA) VALUES ('Categoria Teste 2');
INSERT INTO PRODUTO(NOME, DESCRICAO, URL_IMAGEM, PRECO, ID_CATEGORIA) VALUES ('Produto Um', 'DESCRICAO', 'http://url/', 40, 1);

INSERT INTO PEDIDO(VALOR_TOTAL, ID_CLIENTE, DATA_HORA) VALUES (50, 1, '2021-06-03 19:30:00');
INSERT INTO PEDIDO(VALOR_TOTAL, ID_CLIENTE, DATA_HORA) VALUES (50, 1, '2021-06-03 19:00:00');
INSERT INTO PEDIDO(VALOR_TOTAL, ID_CLIENTE, DATA_HORA) VALUES (50, 2, '2021-06-03 19:30:00');
INSERT INTO PEDIDO(VALOR_TOTAL, ID_CLIENTE, DATA_HORA) VALUES (50, 2, '2021-06-03 19:00:00');

INSERT INTO ITEM_PEDIDO(QUANTIDADE, VALOR_PRODUTO, ID_PRODUTO, ID_PEDIDO) VALUES (1, 40, 1, 1);
INSERT INTO ITEM_PEDIDO(QUANTIDADE, VALOR_PRODUTO, ID_PRODUTO, ID_PEDIDO) VALUES (1, 40, 1, 2);