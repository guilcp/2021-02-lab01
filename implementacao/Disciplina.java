import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Disciplina  implements Serializable{

	private int id;
	private String nome;
	private boolean obrigatoria;
	private double preco;
	private int creditos;
	private static int maxAlunos = 60;

	//construtor
	public Disciplina(int id, String nome, boolean obrigatoria, double preco, int creditos) {
		this.setId(id);
		this.setNome(nome);
        this.setObrigatoria(obrigatoria);
		this.setPreco(preco);
		this.setCreditos(creditos);
	}

	public Disciplina(){
		this.setId(-1);
		this.setNome(null);
        this.setObrigatoria(false);
		this.setPreco(0);
		this.setCreditos(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setObrigatoria(boolean obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	public boolean isObrigatoria() {
		return obrigatoria;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public static int getMaxAlunos() {
		return maxAlunos;
	}

	public static void setMaxAlunos(int maxAlunos) {
		Disciplina.maxAlunos = maxAlunos;
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
	
	public ArrayList<Disciplina> consultar() throws IOException{
		DisciplinaDAO dao = new DisciplinaDAO();

		return dao.lerTodos();
	}

	public void cadastrar(){
		DisciplinaDAO dao = new DisciplinaDAO();
		dao.gravar(this);
		System.out.println("Disciplina cadastrado com sucesso!");
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(getId());
        oos.writeUTF(getNome());
		oos.writeBoolean(isObrigatoria());
		oos.writeDouble(getPreco());
		oos.writeInt(getCreditos());		
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        setId(ois.readInt());
        setNome(ois.readUTF());
        setObrigatoria(ois.readBoolean());
		setPreco(ois.readDouble());
		setCreditos(ois.readInt());        
    }

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", nome='" + getNome() + "'" +
			", obrigatoria='" + isObrigatoria() + "'" +
			", preco='" + getPreco() + "'" +
			", creditos='" + getCreditos() + "'" +
			"}";
	}

}