package br.imd.zmplayer.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.imd.zmplayer.model.tad.ArvoreBinaria;
 
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
    }*/

	/**
	 * Método que grava o usuário no arquivo .zmu
	 * @param path Caminho do local do arquivo
	 * @param user Usuário a ser inserido
	 * @throws IOException
	 */
	public static void gravarUsuario(String path, Usuario user) throws IOException {
			
	        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path,true));
			
	        
	        String linha = user.getId()+";"+user.getNome()+";"+user.getSenha()+";"+user.isVIP();
	        
	        buffWrite.write(linha + "\n");
	        buffWrite.flush();
	        buffWrite.close();
	}
	
	/**
	 * Método que ler o arquivo .zmu e cria a arvore binaria
	 * @param path Caminho do local do arquivo
	 * @throws IOException
	 */
	public static void lerZmu(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        
        String linha;
        while (true) {
        	linha = buffRead.readLine();
        	
            if (linha != null) {     	
            	
            	String user[] = linha.split(";");
            	boolean vip = (user[3].equals("true"))?true:false; 	
            	RepositorioUsuario.add(new Usuario(user[0],user[1],user[2],vip));            	
 
            } else{
                break;
            }
            
        }
        buffRead.close();
    }
 
}
