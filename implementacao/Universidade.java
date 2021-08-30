import java.io.Serializable;
import java.util.ArrayList;

public class Universidade implements Serializable{

    private String nome;
    private ArrayList<Curso> cursos = new ArrayList<Curso>();

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

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", cursos='" + getCursos() + "'" +
            "}";
    }

}
