import java.io.IOException;
import java.util.*;

import java.time.LocalDateTime;

public class Menu {

    public static void renderizar(Usuario usuario, String nome, String senha) throws IOException {
        Scanner in = new Scanner(System.in);
        /*
         * TODO
         */

        if (usuario == null) {
            System.out.println("Não foi encontrado usuário com essa credencial, deseja cadastrar o mesmo?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            int resposta = in.nextInt();
            switch (resposta) {
                case 1:
                    System.out.println("Digite o id da Secretaria:");
                    int idS = in.nextInt();
                    Secretaria s = new Secretaria(idS, nome, senha);
                    s.cadastrar();
                    renderizar(s, nome, senha);
                    break;
                default:
                    System.out.println("Retornando ao login.");
                    login();
                    break;
            }
        } else if (usuario instanceof Secretaria) {
            System.out.println("Olá " + usuario.getNome() + " , o que gostaria de realizar?");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Cadastrar Professor");
            System.out.println("3 - Cadastrar Oferta");
            System.out.println("4 - Cadastrar Disciplina");
            System.out.println("5 - Cadastrar Curso");
            System.out.println("6 - Sair");
            int respostaOperacao = in.nextInt();

            switch (respostaOperacao) {
                case 1:
                    System.out.println("Digite o id do aluno:");
                    int idA = in.nextInt();
                    System.out.println("Digite o nome do aluno:");
                    in.nextLine();
                    String nomeA = in.nextLine();
                    System.out.println("Digite a senha do aluno:");
                    String senhaA = in.nextLine();
                    Aluno a = new Aluno(idA, nomeA, senhaA);
                    a.cadastrar();
                    renderizar(usuario, nome, senha);
                    break;
                case 2:
                    System.out.println("Digite o id do professor:");
                    int idP = in.nextInt();
                    System.out.println("Digite o nome do professor:");
                    in.nextLine();
                    String nomeP = in.nextLine();
                    System.out.println("Digite a senha do professor:");
                    String senhaP = in.nextLine();
                    Professor p = new Professor(idP, nomeP, senhaP);
                    p.cadastrar();
                    renderizar(usuario, nome, senha);
                    break;
                case 3:
                    System.out.println("Digite o id da oferta:");
                    int idO = in.nextInt();
                    Oferta o = new Oferta(idO);
                    o.cadastrar();
                    Professor professor = new Professor();
                    List<Usuario> professores = professor.consultar();
                    if (professores.size() > 0) {
                        System.out.println("Deseja vincular algum professor a essa oferta?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Nao");
                        int respD = in.nextInt();
                        if (respD == 1) {
                            System.out.println("Qual professor deseja vincular?");
                            System.out.println(professores.toString());
                            int escolhaD = in.nextInt();
                            o.setProfessor((Professor) professores.get(escolhaD));
                        }
                    }
                    renderizar(usuario, nome, senha);
                    break;
                case 4:
                    System.out.println("Digite o id da disciplina:");
                    int idD = in.nextInt();
                    System.out.println("Digite o nome da disciplina:");
                    String nomeD = in.nextLine();
                    System.out.println("A disciplina e obrigatoria?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Nao");
                    int respObrigatoria = in.nextInt();
                    boolean obrigatoria = (respObrigatoria == 1);
                    System.out.println("Digite o preco da disciplina:");
                    double precoD = in.nextDouble();
                    System.out.println("Digite os creditos da disciplina:");
                    int creditosD = in.nextInt();
                    Disciplina d = new Disciplina(idD, nomeD, obrigatoria, precoD, creditosD);
                    d.cadastrar();
                    renderizar(usuario, nome, senha);
                    break;
                case 5:
                    System.out.println("Digite o id do curso:");
                    int idC = in.nextInt();
                    System.out.println("Digite o nome do curso:");
                    in.nextLine();
                    String nomeC = in.nextLine();
                    Curso c = new Curso(idC, nomeC);
                    Disciplina disciplina = new Disciplina();
                    ArrayList<Disciplina> disciplinas = disciplina.consultar();
                    if (disciplinas.size() > 0) {
                        System.out.println("Deseja adicionar alguma disciplina?");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Nao");
                        int respD = in.nextInt();
                        while (respD == 1) {
                            if (respD == 1) {
                                System.out.println("Qual disciplina deseja adicionar?");
                                System.out.println(disciplinas.toString());
                                int escolhaD = in.nextInt();
                                c.addDisciplina(disciplinas.get(escolhaD));
                                System.out.println("Deseja adicionar outra disciplina?");
                                System.out.println("1 - Sim");
                                System.out.println("2 - Nao");
                                respD = in.nextInt();
                            }
                        }
                    }
                    c.cadastrar();
                    renderizar(usuario, nome, senha);
                    break;
                case 6:
                    login();
                break;
            }
        } else if (usuario instanceof Professor) {

        } else if (usuario instanceof Aluno) {
            System.out.println("Olá " + usuario.getNome() + " , o que gostaria de realizar?");
            System.out.println("1 - Realizar Matricula");
            System.out.println("2 - Consultar Matriculas");
            System.out.println("3 - Sair");

            int respostaOperacao = in.nextInt();

            switch (respostaOperacao) {
                case 1:
                    Oferta o = new Oferta();
                    ArrayList<Oferta> ofertas = o.consultar();
                    if (ofertas.size() > 0) {
                        System.out.println("Em qual dessas ofertas gostaria de realizar a matricula?");
                        System.out.println(ofertas.toString());
                        int escolha = in.nextInt();
                        String idM = ofertas.get(escolha).getId() + "" + usuario.getId();
                        LocalDateTime data = LocalDateTime.now();
                        Matricula m = new Matricula(Integer.parseInt(idM), data, false, ofertas.get(escolha),
                                (Aluno) usuario);
                        m.realizarMatricula();
                    } else {
                        System.out.println("Não foram disponibilizadas ofertas, consulte a secretaria.");
                    }
                    renderizar(usuario, nome, senha);
                    break;
                case 2:
                    ArrayList<Matricula> matriculas = ((Aluno) usuario).getMatriculas();
                    System.out.println(matriculas.toString());
                    renderizar((Usuario) usuario, nome, senha);
                    break;
                case 3:
                    login();
                    break;
            }
        }

        // tela de login

        // se secretaria:
        // - ação 1
        // - ação 2
        // - ação N
        // -sair

        // se aluno:
        // - ação 1
        // - ação 2
        // - ação N
        // -sair

        // se secretaria:
        // - ação 1
        // - ação 2
        // - ação N
        // -sair

        in.close();
    }

    public static void login() throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Digite o login:");
        String nome = in.nextLine();
        System.out.println("Digite a senha:");
        String senha = in.nextLine();
        Usuario u = new Aluno();
        u = u.autenticarLogin(nome, senha);

        renderizar(u, nome, senha);
        in.close();

    }

}
