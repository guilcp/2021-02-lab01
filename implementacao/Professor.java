import java.util.*;
class Professor extends Usuario{

	private ArrayList<Oferta> ofertas;

	//construtor
	public Professor(int id, String nome, String senha) {
		super(id, nome, senha);
	}

	public ArrayList<Aluno> consultarAlunos(){
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		return alunos;
	}

	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
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
	public void autenticarLogin() {
		/*
		 TODO
		*/
	}

	@Override
	public String getTipo() {
		return this.getClass().getName().toUpperCase(Locale.ROOT);
	}

}