import java.util.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DisciplinaDAO {
	private static String nomeArquivo = "disciplinas.bin";

	public void gravar(Disciplina u) {
		try {
			ArrayList<Disciplina> disciplinas = lerTodos();
			disciplinas.add(u);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
			
			for(Disciplina d : disciplinas){	
				oos.writeObject(d);
			}

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Disciplina> lerTodos() throws IOException {
		ArrayList<Disciplina> objs = new ArrayList<Disciplina>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(nomeArquivo);
			
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				objs.add((Disciplina) ois.readObject());
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
