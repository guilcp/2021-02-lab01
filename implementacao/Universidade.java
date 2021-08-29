import java.util.ArrayList;

public class Universidade {

    private static Universidade instancia = null;

    private String nome;
    private ArrayList<Curso> cursos;

	//construtor
    private Universidade(){
        this.nome = "PUC MINAS";
        this.cursos = null;
    }

    public static Universidade getInstance(){
        if(instancia == null) {
            instancia = new Universidade();
        }
        return instancia;
    }

    public String getNome(){
        return this.nome;
    }

    public ArrayList<Curso> getCursos(){
        return this.cursos;
    }
    
}
