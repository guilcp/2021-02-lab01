import java.util.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UsuarioDAO {
	private static String nomeArquivo = "usuarios.bin";

	public ArrayList<Usuario> lerTodos() throws IOException {
		// ArrayList<Usuario> objs = new ArrayList<Usuario>();
		// FileInputStream fis = null;
		// try {
		// 	fis = new FileInputStream(nomeArquivo);
			
		// 	while (true) {
		// 		ObjectInputStream ois = new ObjectInputStream(fis);
		// 		objs.add((Usuario) ois.readObject());
		// 	}
		// } catch (EOFException e) {
		// 	return objs;
		// } catch (ClassNotFoundException | IOException e) {
		// 	e.printStackTrace();
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// } finally{
		// 	if(fis != null)
		// 		fis.close();
		// }
		// return objs;

		ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(nomeArquivo);
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

	public void gravar(Usuario u) {
		try {
			ArrayList<Usuario> usuarios = lerTodos();
			usuarios.add(u);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
			
			for(Usuario usu : usuarios){	
				oos.writeObject(usu);
			}
			

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
