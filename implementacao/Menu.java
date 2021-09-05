import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class Menu {
    public static Scanner teclado = ScannerSingleton.getInstance().getTeclado();
    public static Usuario userLogado;

    public static void renderizar() {
        pausa();

        System.out.println("Olá " + userLogado.getNome() + ", o que gostaria de realizar?");

        if (userLogado instanceof Secretaria) {
            menuSecretaria();
        }
        else if (userLogado instanceof Aluno) {
            menuAluno();
        }
        else if (userLogado instanceof Professor) {
            menuProfessor();
        }


    }

    public static void login(Usuario usuario) throws Exception {

        Scanner teclado = ScannerSingleton.getInstance().getTeclado();
        System.out.println("Digite a senha: ");
        if(usuario.efetuarLogin(teclado.nextLine())){
            System.out.println("Login realizado com sucesos!");
            System.out.println("Renderizar permissões de " + usuario.getTipo());
            userLogado = usuario;
            renderizar();
        }else{
            throw new Exception("Senha inválida");
        }

    }

    static void pausa(){
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }


    static void menuSecretaria(){
        int opcao = 0;
        try {
            while (opcao != 8) {
                System.out.println("1 - Cadastrar Aluno");
                System.out.println("2 - Cadastrar Professor");
                System.out.println("3 - Cadastrar Oferta");
                System.out.println("4 - lista ofertas");
                System.out.println("5 - Cadastrar Disciplina");
                System.out.println("6 - Cadastrar Curso");
                //System.out.println("8 vincular disciplina no curso");
                System.out.println("7 - valida disciplinas");
                System.out.println("8 - Sair");
                opcao = Integer.parseInt(teclado.nextLine());
                switchSecretaria(opcao);
            }
        }catch (InputMismatchException exception) {
                System.out.println("Favor inserir valor válido!");
                menuSecretaria();
        }
    }

    static void switchSecretaria(Integer opcao){
        try {
            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    pausa();
                    break;
                case 2:
                    cadastrarProfessor();
                    pausa();
                    break;
                case 3:
                    cadastrarOferta();
                    pausa();
                    break;
                case 4:
                    listaOfertas();
                    pausa();
                    break;
                case 5:
                    cadastrarDisciplina();
                    pausa();
                    break;
                case 6:
                    cadadastrarCurso();
                    pausa();
                    break;
                case 7:
                    validaDisciplinas();
                    break;
                case 8:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
            pausa();
        }
    }


    static void menuAluno(){
        System.out.println("Aluno do curso " + ((Aluno) userLogado).getCurso().getNome());
        int opcao = 0;
        try {
            while( opcao != 6) {
                System.out.println("1 - Matricular em uma disciplina");
                System.out.println("2 - Cancelar matricula ");
                System.out.println("3 - listar matriculas ");
                System.out.println("6 - Sair");
                opcao = Integer.parseInt(teclado.nextLine());
                switchAluno(opcao);
            }
        }catch (InputMismatchException exception) {
            System.out.println("Favor inserir valor válido!");
            menuSecretaria();
        }
        
    }

    static void switchAluno(Integer opcao){
        try {
            switch (opcao) {
                case 1:
                    matricular();
                    pausa();
                    break;
                case 2:
                    cancelarMatricula();
                    pausa();
                    break;
                case 3:
                    listaMatriculas((Aluno) userLogado);
                    pausa();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void menuProfessor(){
        int opcao = 0;
        try {
            while( opcao != 6) {
                System.out.println("1 - Consultar matriculas dos alunos");
                System.out.println("6 - Sair");

                opcao = Integer.parseInt(teclado.nextLine());
                switchProfessor(opcao);
            }
        }catch (InputMismatchException exception) {
            System.out.println("Favor inserir valor válido!");
            menuSecretaria();
        }
    }

    static void switchProfessor(int opcao){
        try {
            switch (opcao) {
                case 1:
                    listaTodasMatriculas();
                    pausa();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void listaTodasMatriculas(){
        for ( Usuario usuario : DAO.usuarios){
            if (usuario instanceof Aluno){
                listaMatriculas((Aluno)usuario);
            }
        }
    }

    static void matricular() throws Exception {
        Aluno aluno = ((Aluno) userLogado);
        try {
            System.out.println(" 1 - Discplinas obrigatorias ");
            System.out.println(" 2 - Disciplinas opcionais ");
            int opcao = teclado.nextInt();

            if(opcao == 1) {
                Long qtdDisciplinasObrigatorias = 0l;

                if (aluno.getMatriculas() != null) {
                    qtdDisciplinasObrigatorias = aluno.getMatriculas().stream().filter(
                            matricula -> matricula.getOferta().getDisciplina().isObrigatoria()
                    ).count();
                }
                System.out.println("Quantidade de discilpinas obrigatorias matriculadas: " + qtdDisciplinasObrigatorias);
                if (qtdDisciplinasObrigatorias < 4) {
                    Oferta oferta = buscaOferta();
                    int qtdAlunos = oferta.getQtdAlunos();
                    if( qtdAlunos < 60) {
                        LocalDate lt = LocalDate.now();
                        Date agora = Date.from(lt.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        Matricula matricula = new Matricula(agora, false, oferta, aluno);
                        aluno.addMatricula(matricula);

                    }else throw new Exception("Quantidade de alunos excedida " + qtdAlunos);

                } else {
                    throw new Exception("Numero de matriculas obrigatorias excedidos");
                }
            }
            else if(opcao == 2){
                Long qtdDisciplinasOpcionais= 0l;

                if(aluno.getMatriculas() != null) {
                    qtdDisciplinasOpcionais = aluno.getMatriculas().stream().filter(
                            matricula -> matricula.getOferta().getDisciplina().isObrigatoria() == false
                    ).count();
                }

                System.out.println("Quantidade de discilpinas opcionais matriculadas: " + qtdDisciplinasOpcionais);

                if(qtdDisciplinasOpcionais < 2) {
                    Oferta oferta = buscaOfertaOpcionais();
                    int qtdAlunos = oferta.getQtdAlunos();
                    if( qtdAlunos < 60) {
                        LocalDate lt = LocalDate.now();
                        Date agora = Date.from(lt.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        Matricula matricula = new Matricula(agora, false, oferta, aluno);
                        aluno.addMatricula(matricula);

                    }else throw new Exception("Quantidade de alunos excedida " + qtdAlunos);

                }else{
                    throw new Exception("Numero de matriculas opcionais excedidos");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void cancelarMatricula() throws Exception {
        Aluno aluno = ((Aluno) userLogado);
        ArrayList<Matricula> matriculas = aluno.getMatriculas();

        for (int i = 0; i < matriculas.size(); i++){
            System.out.println("Id: " + i + " - " + matriculas.get(i).getOferta().getDisciplina().getNome());
        }

        System.out.println("Digite o id da matricula cancelada");
        int id = teclado.nextInt();
        if(id > matriculas.size() || id < 0){
            throw new Exception("Matricula nao encontrada com o id " + id );
        }

        aluno.getMatriculas().remove(matriculas.get(id));
        System.out.println("Matricula cancelada com sucesso!");
    }

    static void listaMatriculas(Aluno aluno){

        if( aluno.getMatriculas() != null && aluno.getMatriculas().size() > 0) {
            System.out.println("Matriculas do(a) " + aluno.getNome());
            for (Matricula matricula : aluno.getMatriculas()) {
                System.out.println(matricula.toString());
            }
        }else{
            System.out.println("Aluno nao possui matriculas");
        }
    }

    static void cadastrarAluno(){
        try {

            System.out.println("Digite o nome do aluno:");
            String nomeA = teclado.nextLine();
            System.out.println("Digite a senha do aluno:");
            String senhaA = teclado.nextLine();
            Curso curso = buscaCurso();
            new Aluno( nomeA, senhaA, curso);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
    static void cadastrarOferta(){

        try {
            List<Professor> professores = new ArrayList<Professor>();

            System.out.println("Professores disponíveis: ");
            for ( Usuario prof : DAO.usuarios ){
                if( prof instanceof Professor){
                    System.out.println(" " + prof.getNome());
                    professores.add((Professor) prof);
                }
            }

            System.out.println("\nDigite o nome do professor: ");

            Optional<Professor> prof = professores.stream()
                                                  .filter(p -> p.getNome()
                                                          .equalsIgnoreCase(teclado.nextLine())
                                                  ).findFirst();
            if (!prof.isPresent()){
                throw new Exception("Professor não encontrado");
            }

            System.out.println("Disciplinas disponíveis: ");
            for ( Disciplina disciplina : DAO.disciplinas ){
                System.out.println(" " + disciplina.getNome());
            }

            System.out.println("\nDigite o nome da disciplina: ");

            Optional<Disciplina> disc = DAO.disciplinas.stream()
                                                    .filter(d -> d.getNome()
                                                            .equalsIgnoreCase(teclado.nextLine())
                                                    ).findFirst();

            if(!disc.isPresent()){
                throw new Exception("Disciplina não encontrada");
            }

            System.out.println("Digite a data inicial no formato \"dd/MM/yyyy\" ");
            Date inicial = formataData(teclado.nextLine());

            System.out.println("Digite a data final no formato \"dd/MM/yyyy\" ");
            Date dataFim = formataData(teclado.nextLine());

            new Oferta(prof.get(),inicial, dataFim, disc.get() );

            System.out.println("Oferta criada com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    static Date formataData(String data) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Date dataFormatada = formato.parse(data);

        return dataFormatada;

    }

    private static void cadastrarProfessor() {
        try {

            System.out.println("Digite o nome do professor:");
            String nomeA = teclado.nextLine();
            System.out.println("Digite a senha do professor:");
            String senhaA = teclado.nextLine();
            new Professor( nomeA, senhaA);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void listaOfertas(){
        System.out.println("Lista de ofertas: ");
        for(Oferta oferta : DAO.ofertas){
            System.out.println(oferta.toString());
        }
    }

    static void cadastrarDisciplina(){
        try {
            Curso curso = buscaCurso();

            System.out.println("Digite o nome da disciplina: ");
            String nome = teclado.nextLine();

            System.out.println("Obrigatoria? ");
            System.out.println("1 - SIM");
            System.out.println("2 - NÃO");

            boolean obrigatoria = Integer.parseInt(teclado.nextLine()) == 1 ? true : false;

            System.out.println("Digite o preco da disciplina: ");
            Double preco = Double.parseDouble(teclado.nextLine());

            System.out.println("Digite o numero de creditos: ");
            int creditos = Integer.parseInt(teclado.nextLine());

            Disciplina d = new Disciplina(  nome,  obrigatoria,  preco,  creditos);

            curso.addDisciplina(d);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void cadadastrarCurso(){

        System.out.println("Digite o nome do curso: ");
        String nome = teclado.nextLine();

        System.out.println("Digite o numero de creditos total do curso: ");
        int creditos = Integer.parseInt(teclado.nextLine());

        new Curso(creditos, nome);
        System.out.println("Curso cadastrado com sucesso!");
    }

    static Curso buscaCurso() throws Exception {
        System.out.println("Cursos disponiveis: ");

        for (Curso curso : DAO.cursos ){
            System.out.println(" " + curso.getNome());
        }

        System.out.println("Digite o nome do curso: ");
        String nome = teclado.nextLine();
        Optional<Curso> curso = DAO.cursos.stream()
                .filter(c -> c.getNome()
                        .equalsIgnoreCase(nome)
                ).findAny();
        if (!curso.isPresent()){
            throw new Exception("Curso não encontrado");
        }
        return curso.get();
    }

    //tratar ofertas obrigatorias depois
    static Oferta buscaOferta() throws Exception {
        int i = 0;

        System.out.println("Ofertas disponiveis: ");
        for( i = 0; i < DAO.ofertas.size(); i++  ){
            System.out.println("Id: "+ i + " - " + DAO.ofertas.get(i).toString());
        }

        System.out.println("Digite o id desta oferta: ");
        i = teclado.nextInt();
        if(i > DAO.ofertas.size() || i < 0){
            throw new Exception("Oferta nao encontrada com o id " + i );
        }

        return DAO.ofertas.get(i);
    }

    static Oferta buscaOfertaOpcionais() throws Exception {
        int i = 0;

        System.out.println("Ofertas disponiveis: ");
        for( i = 0; i < DAO.ofertas.size(); i++  ){
            if(DAO.ofertas.get(i).getDisciplina().isObrigatoria() == false) {
                System.out.println("Id: " + i + " - " + DAO.ofertas.get(i).toString());
            }
        }

        System.out.println("Digite o id desta oferta: ");
        i = teclado.nextInt();
        if(i > DAO.ofertas.size() || i < 0){
            throw new Exception("Oferta nao encontrada com o id " + i );
        }

        return DAO.ofertas.get(i);
    }

    static void validaDisciplinas(){
        for (Oferta oferta : DAO.ofertas){
            System.out.println( oferta.getDisciplina().getNome() + " qtd alunos : " + oferta.getQtdAlunos());
            if( oferta.getQtdAlunos() > 3){
                System.out.println("DISCIPLINA ATIVA");
            }else{
                System.out.println("DISCIPLINA DESATIVADA");
            }
        }

    }


}