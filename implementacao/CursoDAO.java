import java.util.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CursoDAO {

	private static String nomeArquivo = "cursos.bin";

	public void gravar(Curso u) {
		try {
			ArrayList<Curso> cursos = lerTodos();
			cursos.add(u);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
			
			for(Curso c : cursos){	
				oos.writeObject(c);
			}

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Curso> lerTodos() throws IOException {
		ArrayList<Curso> objs = new ArrayList<Curso>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(nomeArquivo);
			
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				objs.add((Curso) ois.readObject());
				
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
}
