package br.imd.zmplayer.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;
 
public class ManipuladorArquivo {
	private static final String path = "usuarios.zmu"; 
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
	public static void gravarUsuario(Usuario user) {
			
	        BufferedWriter buffWrite;
			try {
				buffWrite = new BufferedWriter(new FileWriter(path,true));
				String linha = user.getId()+";"+user.getNome()+";"+user.getSenha()+";"+user.isVIP();		  
		        buffWrite.write(linha + "\n");
		        buffWrite.flush();
		        buffWrite.close();
			} catch (IOException e) {
				e.getMessage();
			}        	              
			
	}
	
	/**
	 * Método que ler o arquivo .zmu e cria a arvore binaria
	 * @param path Caminho do local do arquivo
	 * @throws IOException
	 */
	public static void lerZmu(String path) {
        BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(path));
			if(buffRead != null){
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
				} 	
			buffRead.close();
		} catch (IOException e) {
			e.getMessage();
		}
        
       
        
    }
	/**
	 * Método 	que reescreve o arquivo .zmu a partir da árvore.
	 * 
	 * @param user
	 */	
	public static void reescreverArquivo() {
		
		ArrayList<NoBinaria> lista = new ArrayList<NoBinaria>();
		
		RepositorioUsuario.getInstance().arvoreToArrayList(lista);
		
        BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter(path));
			
			for(NoBinaria no: lista){
				Usuario user = no.getUsuario();
				String linha = user.getId()+";"+user.getNome()+";"+user.getSenha()+";"+user.isVIP();		  
		        buffWrite.write(linha + "\n");
			}
			
	        buffWrite.flush();
	        buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}        	              
		
}
 
}


