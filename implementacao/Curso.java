import java.util.*;

class Curso {

	private int id;
	private String nome;
	private int creditosTotal;
	private ArrayList<Disciplina> disciplinas;

	//construtor
	public Curso(int id, int creditosTotal, String nome, ArrayList<Disciplina> disciplinas) {
		this.setId(id);
		this.setCreditosTotal(creditosTotal);
		this.setNome(nome);
        this.setDisciplinas(disciplinas);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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