import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Professor extends Usuario implements Serializable{

	private ArrayList<Oferta> ofertas = new ArrayList<Oferta>();

	//construtor
	public Professor(int id, String nome, String senha) {
		super(id, nome, senha);
	}

	public Professor(){
		setId(-1);
		setNome(null);
		setSenha(null);
	}

	public ArrayList<Aluno> consultarAlunos(){
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		return alunos;
	}

	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public void cadastrar(){
		UsuarioDAO dao = new UsuarioDAO();
		dao.gravar(this);
		System.out.println("Professor cadastrado com sucesso!");
	}
	
	public boolean remover(){
		/*
		 TODO
		*/
		return true;
	}
	
	public boolean atualizar(){
		/*
		 TODO
		*/
		return true;
	}

	public void addOferta(Oferta o){
		ofertas.add(o);
	}
	
	public List<Usuario> consultar() throws IOException{
		UsuarioDAO dao = new UsuarioDAO();

		List<Usuario> professores = dao.lerTodos().stream()
								 .filter(p -> p instanceof Professor)
								 .collect(Collectors.toList());
		return professores;
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
		oos.writeInt(ofertas.size());
        for(Oferta oferta : ofertas){
			oos.writeObject(oferta);
        }
		
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
       
        ois.defaultReadObject();
        setId(ois.readInt());
        setNome(ois.readUTF());
        setSenha(ois.readUTF());
        this.ofertas = new ArrayList<Oferta>();
        int numOfertas = ois.readInt();
        for(int i = 0; i < numOfertas; i++){
			addOferta((Oferta) ois.readObject());
        }
        
    }

	@Override
	public String toString() {
		return "{" +
			" ofertas='" + getOfertas() + "'" +
			"}";
	}

}