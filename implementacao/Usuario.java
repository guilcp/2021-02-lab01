class Usuario {
	private int id;
	private String nome;
	private String senha;

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
}