class Disciplina {

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


	public boolean cadastrar(){
		/*
		 TODO
		*/
		return true;
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
	
	public boolean consultar(){
		/*
		 TODO
		*/
		return true;
	}
}