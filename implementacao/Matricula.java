class Matricula{
	private String data;
	private boolean aprovacao;
	private Oferta oferta;
	private Aluno aluno;
	public String getData() {
		return data;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public boolean isAprovacao() {
		return aprovacao;
	}
	public void setAprovacao(boolean aprovacao) {
		this.aprovacao = aprovacao;
	}
	public void setData(String data) {
		this.data = data;
	}

}