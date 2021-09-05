import java.io.*;
import java.util.ArrayList;

public class UsuarioDAO {
	/*
	 TODO
	*/

    public static void gravar(ArrayList<Usuario> usuarios){

        try {
            new FileWriter("Usuario.bin", false).close(); // limpa os dados para não duplicar quando salvar novamente

            FileOutputStream  fileOutputStream = new FileOutputStream("Usuario.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Usuario usuario : usuarios) {
                objectOutputStream.writeObject(usuario);
            }

            objectOutputStream.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public static ArrayList<Usuario> ler() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("Usuario.bin");
            ObjectInputStream input = new ObjectInputStream(fis);
            try  {
            while (true) {
                usuarios.add((Usuario) input.readObject());
                }
            }catch (EOFException e) {}
            input.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return usuarios;
    }
}
