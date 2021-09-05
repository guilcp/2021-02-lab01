import java.util.*;

public class Aluno extends Usuario {

	private Curso curso;
	private ArrayList<Matricula> matriculas = new ArrayList<>();

	//construtor
	public Aluno(String nome, String senha, Curso curso) {
		super(nome, senha);
		this.curso = curso;
	}

	public Curso getCurso(){
		return curso;
	}

	public void setCurso(Curso c){
		curso = c;
	}

	public ArrayList<Matricula> getMatriculas(){
		return matriculas;
	}

	public void setMatriculas(ArrayList<Matricula> m){
		matriculas = m;
	}

	public void addMatricula(Matricula m ){
		if (matriculas == null){
			matriculas = new ArrayList<Matricula>();
		}
		this.matriculas.add(m);
	}


	@Override
	public void autenticarLogin() {
		/*
		 TODO
		*/
	}



	@Override
	public String getTipo() {
		return this.getClass().getName().toUpperCase(Locale.ROOT);
	}

}