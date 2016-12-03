package br.imd.zmplayer.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;

public class ManipuladorArquivo {
	private static final String PATHUSERS = "usuarios.zmu";
	public static final String PATHMUSICAS = "musicas.zmf";
	private static final String PATHFOLDERS = "folders.zmf";
	private static final String PATHVIPFILES = "./vipfiles/playlist/";
	
	
	/*
	 * public static void leitor(String path) throws IOException {
	 * BufferedReader buffRead = new BufferedReader(new FileReader(path));
	 * String linha = ""; while (true) { if (linha != null) {
	 * System.out.println(linha);
	 * 
	 * } else break; linha = buffRead.readLine(); } buffRead.close(); }
	 */

	/**
	 * Método que grava o usuário no arquivo .zmu
	 * 
	 * @param path
	 *            Caminho do local do arquivo
	 * @param user
	 *            Usuário a ser inserido
	 * @throws IOException
	 */
	public static void gravarUsuario(Usuario user) {
		BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter(PATHUSERS, true));
			String linha = user.getId() + ";" + user.getNome() + ";" + user.getSenha() + ";" + user.isVIP();
			buffWrite.write(linha + "\n");
			buffWrite.flush();
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	/**
	 * Método cria um arquivo (.zmf) de acesso a playlist de usuaŕios VIPs.
	 * 
	 * @param user
	 */
/*	public static void criarArquivoUserVip(String nome) {
		BufferedWriter buffWrite;
		String path = PATHVIPFILES+nome+".zmf";
		try {
			buffWrite = new BufferedWriter(new FileWriter(path, true));
			String linha = user.getId() + ";" + user.getNome() + ";" + user.getSenha() + ";" + user.isVIP();
			buffWrite.write(linha + "\n");
			buffWrite.flush();
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}*/

	/**
	 * Metodo que escreve na lista de Musicas compartilhadas
	 * 
	 * @param pathFile
	 */
	public static void gravarListaMusica(String pathFile) {
		BufferedWriter buffWrite;
		try {
			File file = new File(pathFile);
			buffWrite = new BufferedWriter(new FileWriter(PATHMUSICAS, true));
			String entrada = file.getName() + ";" + file.getPath();
			buffWrite.write(entrada + "\n");
			buffWrite.flush();
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public static void gravarListaFolder(String pathFile) {
		BufferedWriter buffWrite;
		try {
			File file = new File(pathFile);
			buffWrite = new BufferedWriter(new FileWriter(PATHFOLDERS, true));
			buffWrite.write(pathFile + "\n");
			buffWrite.flush();
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public static List<File> openListaMusica(String caminho) {
		BufferedReader buffRead;
		List<File> files = new ArrayList<File>();
		try {
			buffRead = new BufferedReader(new FileReader(caminho));
			if (buffRead != null) {
				String linha;
				while (true) {
					linha = buffRead.readLine();
					if (linha != null) {
						String musica[] = linha.split(";");

						files.add(new File(musica[1]));
					} else {
						break;
					}
				}
			}
			buffRead.close();
			return files;
		} catch (IOException e) {
			e.getMessage();
		}
		return null;
	}

	public static List<File> openListaFolder() {
		BufferedReader buffRead;
		List<File> diretorios = new ArrayList<File>();
		List<File> files = new ArrayList<File>();
		try {
			buffRead = new BufferedReader(new FileReader(PATHFOLDERS));
			if (buffRead != null) {
				String linha;
				while (true) {
					linha = buffRead.readLine();
					if (linha != null) {
						diretorios.add(new File(linha));
					} else {
						break;
					}
				}
				FilenameFilter mp3Filter = new FilenameFilter() {
					public boolean accept(File dir, String name) {
						String lowercaseName = name.toLowerCase();
						if (lowercaseName.endsWith(".mp3")) {
							return true;
						} else {
							return false;
						}
					}
				};
				for(File dir: diretorios){
					for(File file: dir.listFiles(mp3Filter)){
						files.add(file);
					}
				}
			}
			
			buffRead.close();
			return files;
		} catch (IOException e) {
			e.getMessage();
		}
		return null;
	}

	/**
	 * Método que ler o arquivo .zmu e cria a arvore binaria
	 * 
	 * @param path Caminho do local do arquivo
	 * @throws IOException
	 */
	public static void lerZmu(String path) {
		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(path));
			if (buffRead != null) {
				String linha;
				while (true) {
					linha = buffRead.readLine();
					if (linha != null) {
						String user[] = linha.split(";");
						boolean vip = (user[3].equals("true")) ? true : false;
						RepositorioUsuario.add(new Usuario(user[0], user[1], user[2], vip));
					} else {
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
	 * Método que reescreve o arquivo .zmu a partir da árvore.
	 * 
	 * @param user
	 */
	public static void reescreverArquivo() {

		ArrayList<NoBinaria> lista = new ArrayList<NoBinaria>();

		RepositorioUsuario.getInstance().arvoreToArrayList(lista);

		BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter(PATHUSERS));

			for (NoBinaria no : lista) {
				Usuario user = no.getUsuario();
				String linha = user.getId() + ";" + user.getNome() + ";" + user.getSenha() + ";" + user.isVIP();
				buffWrite.write(linha + "\n");
			}

			buffWrite.flush();
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}

	}

}
