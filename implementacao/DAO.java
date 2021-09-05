import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class DAO {
    static public Universidade universidade = Universidade.getInstance();
    static public ArrayList<Oferta> ofertas = new ArrayList<>();
    static public ArrayList<Usuario> usuarios = new ArrayList<>();
    static public ArrayList<Disciplina> disciplinas = new ArrayList<>();
    static public ArrayList<Curso> cursos = new ArrayList<>();

    static void carregaDados(){

        usuarios = lerUsuario();
        ofertas = lerOferta();
        universidade = lerUniversidade();

        populaDAO();
        //todos os cursos da universidade
        cursos = universidade.getCursos();
        //passa por todos os cursos e pega todas as disciplinas
        for(Curso curso : cursos){
            disciplinas.addAll(curso.getDisciplinas());
        }

        testeLeitura();

    }

    static private void testeLeitura(){
        System.out.println("N° de usuários = " + usuarios.size());
        //nome dos usuarios
        System.out.println(usuarios.stream().map(Usuario::getNome).collect(Collectors.joining(", ")));

        //System.out.println(usuarios.get(0).getNome());
        System.out.println("N° de ofertas = "+ ofertas.size());
        //System.out.println(ofertas.get(0).getDisciplina().getNome());
        //System.out.println(ofertas.get(0).getProfessor().getNome());
        System.out.println(universidade.toString());

    }

    static void populaDAO(){

        if(usuarios.size() == 0) {
            usuarios.add(new Secretaria("admin", "admin"));
        }
        if(ofertas.size() == 0){
            Professor professor = new Professor( "joao", "joao");
            Professor professor2 = new Professor( "andre", "andre");

            Disciplina d = new Disciplina("Projeto de software", true, 300.0, 20);
            Disciplina d2 = new Disciplina("Redes de computadores", true, 300.0, 20);

            Curso curso = new Curso(200, "Engenharia de software");
            curso.addDisciplina(d);
            curso.addDisciplina(d2);

            new Oferta(professor, new Date(2021, 8, 01), new Date(2021, 12, 25), d);
            new Oferta(professor2, new Date(2021, 8, 01), new Date(2021, 12, 25), d2);
        }

    }

    static void salvaDados(){
        gravaUniversidade(universidade);
        gravarUsuario(usuarios);
        gravaOferta(ofertas);

    }
    public static void gravarUsuario(ArrayList<Usuario> usuarios){

        try {

            new FileWriter("Usuario.bin", false).close(); // limpa os dados para não duplicar quando salvar novamente

            FileOutputStream fileOutputStream = new FileOutputStream("Usuario.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Usuario usuario : usuarios) {
                objectOutputStream.writeObject(usuario);
            }

            objectOutputStream.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public static void gravaOferta(ArrayList<Oferta> ofertas){

        try {

            new FileWriter("Oferta.bin", false).close(); // limpa os dados para não duplicar quando salvar novamente

            FileOutputStream fileOutputStream = new FileOutputStream("Oferta.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Oferta usuario : ofertas) {
                objectOutputStream.writeObject(usuario);
            }

            objectOutputStream.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public static void gravaUniversidade(Universidade universidade){

        try {
            new FileWriter("Universidade.bin", false).close(); // limpa os dados para não duplicar quando salvar novamente

            FileOutputStream fileOutputStream = new FileOutputStream("Universidade.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(universidade);

            objectOutputStream.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public static ArrayList<Usuario> lerUsuario() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("Usuario.bin");
            ObjectInputStream input = new ObjectInputStream(fis);
            try  {
                while (true) {
                    usuarios.add((Usuario) input.readObject());
                }
            }catch (EOFException e) {}
            input.close();
        }catch (Exception e){
            //System.out.println(e);
        }

        return usuarios;
    }

    public static ArrayList<Oferta> lerOferta() {
        ArrayList<Oferta> ofertas = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("Oferta.bin");
            ObjectInputStream input = new ObjectInputStream(fis);
            try  {
                while (true) {
                    ofertas.add((Oferta) input.readObject());
                }
            }catch (EOFException e) {}
            input.close();
        }catch (Exception e){
            //System.out.println(e);
        }

        return ofertas;
    }

    public static Universidade lerUniversidade() {
        Universidade universidade = Universidade.getInstance();
        try {
            FileInputStream fis = new FileInputStream("Universidade.bin");
            ObjectInputStream input = new ObjectInputStream(fis);

            universidade = (Universidade) input.readObject();

            input.close();
        }catch (Exception e){
            //System.out.println(e);
        }

        return universidade;
    }




}
