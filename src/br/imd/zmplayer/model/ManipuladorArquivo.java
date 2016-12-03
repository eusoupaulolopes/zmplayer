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

import br.imd.zmplayer.controller.utils.OperationalController;
import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;
import javafx.stage.FileChooser;

public class ManipuladorArquivo {
	private static final String PATHUSERS = "usuarios.zmu";
	public static final String PATHMUSICAS = "musicas.zmf";
	private static final String PATHFOLDERS = "folders.zmf";
	private static final String PATHVIPFILES = "./vipfiles/";
	private static final String PATHPLAYLIST = "./vipfiles/playlist/";
	
	
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
	public static void criarArquivoUserVip(String nome) {
		BufferedWriter buffWrite;
		String path = PATHVIPFILES+nome+".zmf";		
		System.out.println(nome);
		try {
			File file = new File(path);	
			buffWrite = new BufferedWriter(new FileWriter(path, true));
			buffWrite.write(nome);
			buffWrite.flush();
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Método retorna um arquivo (.zmf) de com as playlists de usuaŕios VIPs.
	 * 
	 * @param user
	 */
	public static File getArquivoUserVip() {
		String path = PATHVIPFILES+OperationalController.getSessao().getUser().getId()+".zmf";
		return new File(path);
		
	}
	
	/**
	 * Método cria um arquivo (.zmp) referente a uma playlist de um usuário
	 * 
	 * @param user
	 */
	public static String criarPlaylist(String nome) {
		BufferedWriter buffWrite;
		String path = PATHPLAYLIST+OperationalController.getSessao().getUser().getId()+"_"+nome+".zmp";
		
		try {
			buffWrite = new BufferedWriter(new FileWriter(path, true));
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return path;
	}

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
	public static void addPlaylistToUserFile(String playlistName, String playlistPath) {
		BufferedWriter bw;
		System.out.println("adcionando no file: "+playlistName);
		try {
			File file = getArquivoUserVip();
			bw = new BufferedWriter(new FileWriter(file.getPath(),true));
			bw.write(playlistName+";"+playlistPath.substring(1)+"\n");
			bw.flush();
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
