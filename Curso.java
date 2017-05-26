//******************************************************
//Autor...........: Fábio Francisco Campêlo
//******************************************************

package br.com.campello.fabio.model;

public class Curso {

	// DECLARAÇÃO DE VARIÁVEIS
	private int idCurso;
	private String nomeCurso;
	private String periodo;

	// MÉTODOS ACESSORES
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

	//MÉTODO TOSTRING
	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nomeCurso=" + nomeCurso + ", periodo=" + periodo + "]";
	}

}
