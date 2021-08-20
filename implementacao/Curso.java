import java.util.*;

class Curso {
	private int creditosTotal;
	private String nome;
	private ArrayList<Disciplina> disciplinas;
	public int getCreditosTotal() {
		return creditosTotal;
	}
	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCreditosTotal(int creditosTotal) {
		this.creditosTotal = creditosTotal;
	}

	
}