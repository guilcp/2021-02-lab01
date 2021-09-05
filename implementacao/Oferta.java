import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Oferta implements Serializable {
	private Professor professor;
	private Date dataInicio;
	private Date dataFinal;
	private Disciplina disciplina;

	//construtor
	public Oferta( Professor professor, Date dataInicio, Date dataFinal, Disciplina disciplina) {
		this.setProfessor(professor);
		this.setDataInicio(dataInicio);
		this.setDataFinal(dataFinal);
		this.setDisciplina(disciplina);
		DAO.ofertas.add(this);
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Disciplina getDisciplina(){
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
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

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		return "Oferta:{ " +
				"\n disciplina= " + disciplina.getNome() +
				"\n professor= " + professor.getNome() +
				"\n dataInicio= " + dateFormat.format(dataInicio)+
				"\n dataFinal= " + dateFormat.format(dataFinal)+
				"\n}";
	}
}