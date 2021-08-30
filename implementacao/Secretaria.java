import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Secretaria extends Usuario implements Serializable{
	private ArrayList<Curso> cursos = new ArrayList<Curso>();

	//construtor
	public Secretaria(int id, String nome, String senha) {
		super(id, nome, senha);
	}

	public Secretaria(){
		setId(-1);
		setNome(null);
		setSenha(null);
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
	public boolean autenticarLogin() {
		/*
		 TODO
		*/
		return false;
	}

	public void cadastrar(){
		UsuarioDAO dao = new UsuarioDAO();

		dao.gravar(this);

		System.out.println("Secretaria cadastrado com sucesso!");
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(getId());
        oos.writeUTF(getNome());
		oos.writeUTF(getSenha());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        setId(ois.readInt());
        setNome(ois.readUTF());
        setSenha(ois.readUTF());
    }
	
}