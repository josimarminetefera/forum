--ESTE ARQUIVO É EXECUTANDO QUANDO O PROJETO INICIA
--para usar no H2 tem que tirar todos forum. e para usar para mysql tem que colocar forum.usuario
INSERT INTO usuario(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$D93oL0Tm7sjhfZiKETPJ.OFhSjvDZR/oN0ZqeHBFud0sL3oSiK3z2');

INSERT INTO curso(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO curso(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO topico(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO topico(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO topico(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);
