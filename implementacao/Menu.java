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
//        else if (usuario instanceof Professor) {
//
//        }


    }

    public static void login(Usuario usuario) {

        Scanner teclado = ScannerSingleton.getInstance().getTeclado();
        System.out.println("Digite a senha: ");
        if(usuario.efetuarLogin(teclado.nextLine())){
            System.out.println("Login realizado com sucesos!");
            System.out.println("Renderizar permissões de " + usuario.getTipo());
            userLogado = usuario;
            renderizar();
        }else{
            System.out.println("Senha inválida");
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
                //System.out.println("2 - Cadastrar Professor");
                System.out.println("3 - Cadastrar Oferta");
                System.out.println("4 - lista ofertas");
                System.out.println("5 - Cadastrar Disciplina");
                System.out.println("6 - Cadastrar Curso");
                //System.out.println("8 vincular disciplina no curso");
                System.out.println("8 - Sair");
                opcao = teclado.nextInt();
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

                opcao = teclado.nextInt();
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

                case 3:
                    listaMatriculas();
                    pausa();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void matricular() throws Exception {
        Aluno aluno = ((Aluno) userLogado);
        try {
            System.out.println(" 1 - Discplinas obrigatorias ");
            System.out.println(" 2 - Disciplinas opcionais ");
            int opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    Long qtdDisciplinasObrigatorias = 0l;

                    if(aluno.getMatriculas() != null) {
                         qtdDisciplinasObrigatorias = aluno.getMatriculas().stream().filter(
                                matricula -> matricula.getOferta().getDisciplina().isObrigatoria()
                        ).count();
                    }

                    if(qtdDisciplinasObrigatorias < 4) {
                        Oferta oferta = buscaOferta();

                        LocalDate lt = LocalDate.now();
                        Date agora = Date.from(lt.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        Matricula matricula = new Matricula(agora, false, oferta, aluno);
                        aluno.addMatricula(matricula);

                    }else{
                        throw new Exception("Numero de matriculas obrigatorias excedidos");
                    }
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void listaMatriculas(){
        Aluno aluno = (Aluno) userLogado;
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
            teclado.nextLine();
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
        //                    System.out.println("Digite o id do professor:");
        //                    int idP = in.nextInt();
        //                    System.out.println("Digite o nome do professor:");
        //                    in.nextLine();
        //                    String nomeP = in.nextLine();
        //                    System.out.println("Digite a senha do professor:");
        //                    String senhaP = in.nextLine();
        //                    Professor p = new Professor(idP, nomeP, senhaP);
        //                    p.cadastrar();
        //                    renderizar(usuario, nome, senha);
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
        Optional<Curso> curso = DAO.cursos.stream()
                .filter(c -> c.getNome()
                        .equalsIgnoreCase(teclado.nextLine())
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


}