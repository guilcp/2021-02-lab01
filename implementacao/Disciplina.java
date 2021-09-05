import java.io.Serializable;

public class Disciplina implements Serializable {

	private String nome;
	private boolean obrigatoria;
	private double preco;
	private int creditos;
	private static int maxAlunos = 60;

	//construtor
	public Disciplina( String nome, boolean obrigatoria, double preco, int creditos) {
		this.setNome(nome);
        this.setObrigatoria(obrigatoria);
		this.setPreco(preco);
		this.setCreditos(creditos);
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


}