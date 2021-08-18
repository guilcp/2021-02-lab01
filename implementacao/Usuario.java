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
}