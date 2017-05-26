# Prog-Cadastro-Disciplina
Programa para cadastro de cursos e disiplinas

|===============================================================================|
|				                Desenvolvido por: Fábio Campêllo. 					            |
|===============================================================================|
|											                                                         	|
|	É um software livre, desenvolvido para fins acadêmicos. Colocando em prática  |
| conceitos como:                                                               |
|						                                                             				|
|	1. Persistência no banco de dados MySQL, originalmente utilizando o           |
| WampServer.                                                              			|
|						                                                             				|
|	2. A criaçaõ de aplicação desktop, na liguagem Java através da IDE Eclipse    |
| Mars 2.                                                                       |
|						                                                             				|
|	Enfim, um CRUD básico para aplicação dos conceitos inerentes de sua           |
| construção.                                                                   |
|						                                                             				|
|===============================================================================|
|				                 	Instrução de instalação				                   			|
|===============================================================================|
|											                                                        	|
|	Segue abaixo o código SQL para a criação do banco de dados MySQL, Copie-o e   |
| use para a criação.           	                                              |
|	do banco de dados para que a aplicação funcione.				                     	|
|	Depois de instalado o software e criado o banco de dados, basta executar a    |
| aplicação                                                                    	|
|										                                                     				|
|===============================================================================|

// Criando o banco de dados
CREATE DATABASE escoladb;

//Setando o banco de dados à ser manipulado
use escoladb;

// Criando a tabela Curso
CREATE TABLE Curso (
  idCurso INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nomeCurso VARCHAR(200) NOT NULL,
  periodo VARCHAR(50) NOT NULL,
  PRIMARY KEY(idCurso)
);

// Criando a tabela Disciplina
CREATE TABLE Disciplina (
  idDisciplina INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(200) NOT NULL,
  cargaHoraria INTEGER NOT NULL,
  vagas INTEGER NOT NULL,
  Curso_idCurso INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idDisciplina),
  INDEX Disciplina_FKIndex1(Curso_idCurso)
);


