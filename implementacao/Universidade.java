import java.util.ArrayList;

public class Universidade {

    private String nome;
    private ArrayList<Curso> cursos;

	//construtor
	public Universidade(String nome) {
		this.setNome(nome);
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}
    
}
