import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

class Main {

  public static void main(String[] args){
    DAO.carregaDados();
 
    System.out.println("Sistema de matriculas da " + DAO.universidade.getNome());
    logar();

    DAO.salvaDados();

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
        usuario = null;
        System.out.println(e);
      }
    }

  }

  static Usuario buscaUsuario(String nome) throws Exception{
    Optional<Usuario> usuarioOpt = DAO.usuarios.stream()
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