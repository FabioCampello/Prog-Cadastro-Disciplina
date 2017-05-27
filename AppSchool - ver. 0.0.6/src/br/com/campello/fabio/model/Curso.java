//******************************************************
//Autor...........: F�bio Francisco Camp�lo
//******************************************************

package br.com.campello.fabio.model;

public class Curso {

	// DECLARA��O DE VARI�VEIS
	private int idCurso;
	private String nomeCurso;
	private String periodo;

	// M�TODOS ACESSORES
	public int getIdCurso() {
		return idCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	//M�TODO TOSTRING
	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nomeCurso=" + nomeCurso + ", periodo=" + periodo + "]";
	}

}
