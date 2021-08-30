import java.util.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Aluno extends Usuario implements Serializable{

	private Curso curso;
	private ArrayList<Matricula> matriculas = new ArrayList<Matricula>();

	//construtor
	public Aluno(int id, String nome, String senha) {
		super(id, nome, senha);
	}

	public Aluno(){
		setId(-1);
		setNome(null);
		setSenha(null);
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

	public void cadastrar(){
		UsuarioDAO dao = new UsuarioDAO();
		dao.gravar(this);
		System.out.println("Aluno cadastrado com sucesso!");
	}

	public void addMatricula(Matricula m){
		matriculas.add(m);
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
	public boolean autenticarLogin() {
		/*
		 TODO
		*/
		return false;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
       
        oos.defaultWriteObject();
        oos.writeInt(getId());
        oos.writeUTF(getNome());
		oos.writeUTF(getSenha());
		oos.writeInt(matriculas.size());
        for(Matricula matricula : matriculas){
			oos.writeObject(matricula);
        }
		
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
       
        ois.defaultReadObject();
        setId(ois.readInt());
        setNome(ois.readUTF());
        setSenha(ois.readUTF());
        this.matriculas = new ArrayList<Matricula>();
        int numMatriculas = ois.readInt();
        for(int i = 0; i < numMatriculas; i++){
			addMatricula((Matricula) ois.readObject());
        }
        
    }

	@Override
	public String toString() {
		return "{" +
			" curso='" + getCurso() + "'" +
			", matriculas='" + getMatriculas() + "'" +
			"}";
	}
}