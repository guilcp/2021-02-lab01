import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Universidade implements Serializable {

    private static Universidade instancia = null;

    private String nome;
    private ArrayList<Curso> cursos = new ArrayList<>();
    //construtor
    private Universidade(){
        this.nome = "PUC MINAS";
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

    public void setCursos(ArrayList<Curso> cursos){
        this.cursos = cursos;
    }

    public ArrayList<Curso> getCursos(){
        return this.cursos;
    }

    @Override
    public String toString() {
        return "Universidade: {" +
                "\n nome= " + nome  +
                "\n cursos= " + cursos.stream().map(Curso::getNome).collect(Collectors.joining(", ")) +
                "\n}";
    }


    public void addCurso(Curso c) { this.cursos.add(c); }
}
