import java.util.Scanner;

public class Menu {
    
    public static void renderizar(int id) {
    
    	/*
		 TODO
		*/

        //tela de login

        //se secretaria:
        //- ação 1
        //- ação 2
        //- ação N
        //-sair

        //se aluno:
        //- ação 1
        //- ação 2
        //- ação N
        //-sair

        //se secretaria:
        //- ação 1
        //- ação 2
        //- ação N
        //-sair
    }

    public static void login(Usuario usuario) {
        Scanner teclado = ScannerSingleton.getInstance().getTeclado();
        System.out.println("Digite a senha: ");
        if(usuario.efetuarLogin(teclado.nextLine())){
            System.out.println("Login realizado com sucesos!");
            System.out.println("Renderizar permissões de " + usuario.getTipo());
        }else{
            System.out.println("Senha inválida");
        }
        //renderizar(usuario.getId());

    	/*
		 TODO
		*/
        
    }

    static void pausa(Scanner teclado){
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

}
