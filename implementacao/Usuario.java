abstract class  Usuario implements IAutenticavel {
	private int id;
	private String nome;
	private String senha;

	//construtor
	public Usuario(int id, String nome, String senha) {
		setId(id);
		setNome(nome);
	}

	public boolean efetuarLogin(String senha){
		boolean resultado = false;
		if(this.senha == senha){
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
}