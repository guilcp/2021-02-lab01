import java.io.Serializable;
import java.util.*;

public class Curso implements Serializable {

	private String nome;
	private int creditosTotal;
	private ArrayList<Disciplina> disciplinas = new ArrayList<>();

	//construtor
	public Curso( int creditosTotal, String nome) {
		this.setCreditosTotal(creditosTotal);
		this.setNome(nome);
		DAO.universidade.addCurso(this);
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCreditosTotal() {
		return creditosTotal;
	}

	public void setCreditosTotal(int creditosTotal) {
		this.creditosTotal = creditosTotal;
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public void addDisciplina(Disciplina d) {
		this.disciplinas.add(d);
	}

	public boolean cadastrar(){
		/*
		 TODO
		*/
		return true;
	}
	
	public boolean remover(){
		/*
		 TODO
		*/
		return true;
	}
	
	public boolean atualizar(){
		/*
		 TODO
		*/
		return true;
	}
	
	public boolean consultar(){
		/*
		 TODO
		*/
		return true;
	}
}