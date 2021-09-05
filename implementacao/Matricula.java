import java.util.Date;

class Matricula{
	private int id;
	private Date data;
	private boolean aprovacao;
	private Oferta oferta;
	private Aluno aluno;
	
	//construtor
	public Matricula(int id, Date data, boolean aprovacao, Oferta oferta, Aluno aluno) {
		this.setId(id);
		this.setData(data);
		this.setAprovacao(aprovacao);
		this.setOferta(oferta);
		this.setAluno(aluno);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAprovacao() {
		return aprovacao;
	}

	public void setAprovacao(boolean aprovacao) {
		this.aprovacao = aprovacao;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void realizarMatricula() {
		/*
		 TODO
		*/
	}

	public void cancelarMatricula() {
		/*
		 TODO
		*/
	}

}