import java.util.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UniversidadeDAO {
	private static String nomeArquivo = "universidades.bin";

	public ArrayList<Universidade> lerTodos() throws IOException {
		ArrayList<Universidade> objs = new ArrayList<Universidade>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(nomeArquivo);
			
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				objs.add((Universidade) ois.readObject());
			}
		} catch (EOFException e) {
			return objs;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(fis != null)
				fis.close();
		}
		return objs;
	}

	public void gravar(Universidade u) {
		try {
			ArrayList<Universidade> universidades = lerTodos();
			universidades.add(u);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
			
			for(Universidade uni : universidades){	
				oos.writeObject(uni);
			}

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
