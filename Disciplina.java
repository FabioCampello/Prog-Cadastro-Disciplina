//******************************************************
//Autor...........: F�bio Francisco Camp�lo
//******************************************************

package br.com.campello.fabio.model;

public class Disciplina {

	// DECLARA��O DE VARI�VEIS
	private int idDisciplina;
	private String nome;
	private int cargaHoraria;
	private Curso curso;
	private int vagas;

	// M�TODOS ACESSORES
	public int getIdDisciplina() {
		return idDisciplina;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public int getVagas() {
		return vagas;
	}
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	
	// M�TODO TOSTRING
	@Override
	public String toString() {
		return "Disciplina [idDisciplina=" + idDisciplina + ", nome=" + nome + ", cargaHoraria=" + cargaHoraria
				+ ", curso=" + curso + ", vagas=" + vagas + "]";
	}

}
