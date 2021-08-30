import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    //Singleton: geadrar instância da Universidade
    Universidade puc = new Universidade("PUC");
    Menu.login();
  }
}