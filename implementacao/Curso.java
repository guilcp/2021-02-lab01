import java.util.*;
import java.io.Serializable;

public class Curso  implements Serializable{

	private int id;
	private String nome;
	private int creditosTotal;
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

	//construtor
	public Curso(int id, String nome) {
		this.setId(id);
		this.setNome(nome);
		this.setCreditosTotal(0);
        disciplinas = new ArrayList<Disciplina>();
	}

	public Curso(){
		this.setId(-1);
		this.setNome(null);
		this.setCreditosTotal(0);
        disciplinas = new ArrayList<Disciplina>();
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
	
	public void addDisciplina(Disciplina d){
		disciplinas.add(d);
		atualizaCreditos();
	} 

	public void atualizaCreditos(){
		int soma = 0;
		for(Disciplina d : disciplinas){
			soma += d.getCreditos();
		}
		setCreditosTotal(soma);
	}

	public void cadastrar(){
		CursoDAO dao = new CursoDAO();
		dao.gravar(this);
		System.out.println("Curso cadastrado com sucesso!");
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

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", nome='" + getNome() + "'" +
			", creditosTotal='" + getCreditosTotal() + "'" +
			", disciplinas='" + getDisciplinas() + "'" +
			"}";
	}

}