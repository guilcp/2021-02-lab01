import java.util.*;
public class Secretaria extends Usuario{
	private ArrayList<Curso> cursos;

	//construtor
	public Secretaria(int id, String nome, String senha) {
		super(id, nome, senha);
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	public void gerarCurriculo(){
		/*
		 TODO
		*/
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