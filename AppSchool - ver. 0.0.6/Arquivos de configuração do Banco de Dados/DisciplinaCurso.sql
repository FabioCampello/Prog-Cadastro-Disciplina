CREATE TABLE Curso (
  idCurso INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nomeCurso VARCHAR(200) NOT NULL,
  periodo VARCHAR(50) NOT NULL,
  PRIMARY KEY(idCurso)
);

CREATE TABLE Disciplina (
  idDisciplina INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(200) NOT NULL,
  cargaHoraria INTEGER NOT NULL,
  vagas INTEGER NOT NULL,
  Curso_idCurso INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idDisciplina),
  INDEX Disciplina_FKIndex1(Curso_idCurso)
);


