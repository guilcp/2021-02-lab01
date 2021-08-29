
import java.util.Scanner;
public class ScannerSingleton {
    private static ScannerSingleton instancia = null;
    private Scanner teclado;
    private ScannerSingleton() { teclado = new Scanner(System.in); }
    public static ScannerSingleton getInstance() {
        if (instancia == null)
            instancia = new ScannerSingleton();
        return instancia;
    }

    public Scanner getTeclado() {
        return this.teclado;
    }
}
