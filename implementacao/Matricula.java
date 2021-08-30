import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Matricula implements Serializable{
	private int id;
	private LocalDateTime data;
	private boolean aprovacao;
	private Oferta oferta;
	private Aluno aluno;
	
	//construtor
	public Matricula(int id, LocalDateTime data, boolean aprovacao, Oferta oferta, Aluno aluno) {
		this.setId(id);
		this.setData(data);
		this.setAprovacao(aprovacao);
		this.setOferta(oferta);
		this.setAluno(aluno);
	}

	public Matricula() {
		this.setId(-1);
		this.setData(null);
		this.setAprovacao(false);
		this.setOferta(null);
		this.setAluno(null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public boolean getAprovacao() {
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
		MatriculaDAO dao = new MatriculaDAO();

		dao.gravar(this);
		aluno.addMatricula(this);
		System.out.println("Matricula realizada com sucesso!");
	}

	public void cancelarMatricula() {
		/*
		 TODO
		*/
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(getId());
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		oos.writeUTF(getData().format(formatter));
        oos.writeBoolean(getAprovacao());
		oos.writeObject(getOferta());
		oos.writeObject(getAluno());		
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        setId(ois.readInt());
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(ois.readUTF(), formatter);
        setData(dateTime);
        setAprovacao(ois.readBoolean());
		setOferta((Oferta) ois.readObject());
		setAluno((Aluno) ois.readObject());        
    }

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", data='" + getData() + "'" +
			", aprovacao='" + getAprovacao() + "'" +
			"}";
	}

}