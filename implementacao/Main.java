import DAO.UsuarioDAO;

class Main {
  public static void main(String[] args) {
    System.out.println("ola mundo!");

    //Singleton: gerar instância da Universidade
    Universidade puc = new Universidade("PUC");
    
    Usuario usuario = new Secretaria (0, "admin", "admin");

    Menu.login(usuario);
  }
}