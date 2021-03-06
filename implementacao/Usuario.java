import java.io.Serializable;

public abstract class  Usuario implements IAutenticavel, Serializable {
	private String nome;
	private String senha;

	//construtor
	public Usuario(String nome, String senha) {
		setNome(nome);
		setSenha(senha);
		DAO.usuarios.add(this);
	}

	public boolean efetuarLogin(String senha){
		boolean resultado = false;
		if(this.senha.equals(senha)){
			resultado = true;
		}
		return resultado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	abstract public String getTipo();
}