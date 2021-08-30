import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Oferta implements Serializable{
	private int id;
	private Professor professor;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFinal;
	private Disciplina disciplina;

	//construtor
	public Oferta(int id, Professor professor, LocalDateTime dataInicio, LocalDateTime dataFinal, Disciplina disciplina) {
		this.setId(id);
		this.setProfessor(professor);
		this.setDataInicio(dataInicio);
		this.setDataFinal(dataFinal);
		this.setDisciplina(disciplina);
	}

	public Oferta(int id){
		setId(id);
		this.setProfessor(null);
		this.setDataInicio(LocalDateTime.now());
		this.setDataFinal(LocalDateTime.now());
		this.setDisciplina(null);
	}

	public Oferta(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Disciplina getDisciplina(){
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public void cadastrar(){
		OfertaDAO dao = new OfertaDAO();
		dao.gravar(this);
		System.out.println("Oferta cadastrado com sucesso!");
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
	
	public ArrayList<Oferta> consultar() throws IOException{
		OfertaDAO dao = new OfertaDAO();

		return dao.lerTodos();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(getId());
		oos.writeObject(getProfessor());
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		oos.writeUTF(getDataInicio().format(formatter));
		oos.writeUTF(getDataFinal().format(formatter));
		oos.writeObject(getDisciplina());
				
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        setId(ois.readInt());
		setProfessor((Professor) ois.readObject());
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime dataI = LocalDateTime.parse(ois.readUTF(), formatter);
        setDataInicio(dataI);
		LocalDateTime dataF = LocalDateTime.parse(ois.readUTF(), formatter);
		setDataFinal(dataF);
        setDisciplina((Disciplina) ois.readObject());
    }

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", professor='" + getProfessor() + "'" +
			", dataInicio='" + getDataInicio() + "'" +
			", dataFinal='" + getDataFinal() + "'" +
			", disciplina='" + getDisciplina() + "'" +
			"}";
	}
}