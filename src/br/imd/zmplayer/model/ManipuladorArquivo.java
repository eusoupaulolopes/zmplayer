package br.imd.zmplayer.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
 
public class ManipuladorArquivo {
 
/*    public static void leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);
 
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
    }
 
    public static void escritor(String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        String linha = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Escreva algo: ");
        linha = in.nextLine();
        in.close();
        buffWrite.append(linha + "\n");
        buffWrite.close();
    }*/

	public static void gravarUsuario(String path, Usuario user) throws IOException {
			
	        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path,true));
			
	        
	        String linha = user.getId()+";"+user.getSenha()+";"+user.getNome()+";"+user.isVIP();
	        
	        buffWrite.write(linha + "\n");
	        buffWrite.flush();
	        buffWrite.close();
	}
 
}
