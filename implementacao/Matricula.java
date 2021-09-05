import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Matricula implements Serializable {
	private Date data;
	private boolean aprovacao;
	private Oferta oferta;
	private Aluno aluno;
	
	//construtor
	public Matricula( Date data, boolean aprovacao, Oferta oferta, Aluno aluno) {
		this.setData(data);
		this.setAprovacao(aprovacao);
		this.setOferta(oferta);
		this.setAluno(aluno);
		oferta.iteraQtdAluno();
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

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return "Matricula: {" +
				"\ndata= " + dateFormat.format(data) +
				"\naprovacao= " + aprovacao +
				"\noferta= " + oferta.toString() +
				"\naluno= " + aluno.getNome() +
				"\n}";
	}
}