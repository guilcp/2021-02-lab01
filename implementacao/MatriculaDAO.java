import java.util.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MatriculaDAO {
	private static String nomeArquivo = "matriculas.bin";

	public void gravar(Matricula u) {
		try {
			ArrayList<Matricula> matriculas = lerTodos();
			matriculas.add(u);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
			
			for(Matricula m : matriculas){	
				oos.writeObject(m);
			}

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Matricula> lerTodos() throws IOException {
		ArrayList<Matricula> objs = new ArrayList<Matricula>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(nomeArquivo);
			
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				objs.add((Matricula) ois.readObject());
			}
		} catch (EOFException e) {
			return objs;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// e.printStackTrace();
			return objs;
		} finally{
			if(fis != null)
				fis.close();
		}
		return objs;
	}
}
