import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

class Main {

  private static ArrayList<Usuario> usuarios = new ArrayList<>();
  private static Universidade universidade = Universidade.getInstance();

  public static void main(String[] args){
    carregaDados();

    System.out.println("Sistema de matriculas da " + universidade.getNome());
    logar();

    salvaDados();

  }


  static void salvaDados(){
  UsuarioDAO.gravar(usuarios);
  }

  static void carregaDados(){
    usuarios = UsuarioDAO.ler();
    System.out.println("O sistema possui " + usuarios.size() + " usuarios cadastrados." );
    if(usuarios.size() == 0) {
      usuarios.add(new Secretaria(1, "admin", "admin"));
    }
  }

  static void logar(){

    Scanner teclado = ScannerSingleton.getInstance().getTeclado();
    Usuario usuario = null;
    while(usuario == null) {
      System.out.println("Digite o nome do usuário: ");
      try {
        usuario = buscaUsuario(teclado.nextLine());
        Menu.login(usuario);
      } catch (Exception e) {
        System.out.println(e);
      }
    }

  }

  static Usuario buscaUsuario(String nome) throws Exception{
    Optional<Usuario> usuarioOpt = usuarios.stream()
            .filter(usuario -> usuario.getNome().equalsIgnoreCase(nome))
            .findAny();
    if(usuarioOpt.isPresent()) {
      return usuarioOpt.get();
    }
    else throw new Exception("Usuario não encontrado");
  }

  static void pausa(Scanner teclado){
    System.out.println("Enter para continuar.");
    teclado.nextLine();
  }


}