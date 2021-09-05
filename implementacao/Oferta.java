import java.util.Date;

class Oferta {
	private int id;
	private Professor professor;
	private Date dataInicio;
	private Date dataFinal;
	private Disciplina disciplina;

	//construtor
	public Oferta(int id, Professor professor, Date dataInicio, Date dataFinal, Disciplina disciplina) {
		this.setId(id);
		this.setProfessor(professor);
		this.setDataInicio(dataInicio);
		this.setDataFinal(dataFinal);
		this.setDisciplina(disciplina);
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
}