import java.util.*;

class Aluno extends Usuario{
	private Curso curso;
	private ArrayList<Matricula> matriculas;

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
}