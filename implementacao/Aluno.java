import java.util.*;

class Aluno extends Usuario {

	private Curso curso;
	private ArrayList<Matricula> matriculas;

	//construtor
	public Aluno(int id, String nome, String senha) {
		super(id, nome, senha);
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

	public boolean cadastrar(){
		return true;
	}
	
	public boolean remover(){
		return true;
	}
	
	public boolean atualizar(){
		return true;
	}
	
	public boolean consultar(){
		return true;
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