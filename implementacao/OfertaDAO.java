import java.util.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class OfertaDAO {
	private static String nomeArquivo = "ofertas.bin";

	public void gravar(Oferta u) {
		try {
			ArrayList<Oferta> ofertas = lerTodos();
			ofertas.add(u);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
			
			for(Oferta m : ofertas){	
				oos.writeObject(m);
			}

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Oferta> lerTodos() throws IOException {
		ArrayList<Oferta> objs = new ArrayList<Oferta>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(nomeArquivo);
			
			while (true) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				objs.add((Oferta) ois.readObject());
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
