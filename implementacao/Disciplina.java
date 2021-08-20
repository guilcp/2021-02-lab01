class Disciplina {
	private boolean obrigatoria;
	private static int maxAlunos = 60;
	private double preco;
	private int creditos;
	public boolean isObrigatoria() {
		return obrigatoria;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public static int getMaxAlunos() {
		return maxAlunos;
	}
	public static void setMaxAlunos(int maxAlunos) {
		Disciplina.maxAlunos = maxAlunos;
	}
	public void setObrigatoria(boolean obrigatoria) {
		this.obrigatoria = obrigatoria;
	} 
}