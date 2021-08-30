import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

abstract public class Usuario implements IAutenticavel{
	private int id;
	private String nome;
	private String senha;

	// construtor
	public Usuario(int id, String nome, String senha) {
		setId(id);
		setNome(nome);
		setSenha(senha);
	}

	public Usuario() {
		setId(-1);
		setNome(null);
		setSenha(null);
	}

	public Usuario autenticarLogin(String nome, String senha) throws IOException {

		UsuarioDAO dao = new UsuarioDAO();
		ArrayList<Usuario> usuarios = dao.lerTodos();
		List<Usuario> resultado = null;
		Predicate<Usuario> mesmoNome = u -> nome.equals(u.getNome());
		Predicate<Usuario> mesmaSenha = u -> senha.equals(u.getSenha());
		resultado = usuarios.stream().filter(mesmoNome).filter(mesmaSenha)
				.collect(Collectors.toList());

		if (resultado == null || resultado.isEmpty())
			return null;
		else
			return resultado.get(0);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		oos.writeInt(getId());
		oos.writeUTF(getNome());
		oos.writeUTF(getSenha());
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		setId(ois.readInt());
		setNome(ois.readUTF());
		setSenha(ois.readUTF());
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", nome='" + getNome() + "'" + ", senha='" + getSenha() + "'" + "}";
	}

}