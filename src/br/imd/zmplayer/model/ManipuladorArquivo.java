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

import br.imd.zmplayer.controller.PlaylistController;
import br.imd.zmplayer.controller.TabelaControler;
import br.imd.zmplayer.controller.musictable.MusicaTable;
import br.imd.zmplayer.controller.utils.OperationalController;
import br.imd.zmplayer.model.tabela.PlaylistTabela;
import br.imd.zmplayer.model.tad.ArvoreBinaria;
import br.imd.zmplayer.model.tad.NoBinaria;
import javafx.stage.FileChooser;

public class ManipuladorArquivo {
	private static final String PATHUSERS = "usuarios.zmu";
	public static final String PATHMUSICAS = "musicas.zmf";
	private static final String PATHFOLDERS = "folders.zmf";
	private static final String PATHVIPFILES = "./vipfiles/";
	private static final String PATHPLAYLIST = "./vipfiles/playlist/";
	
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
						RepositorioUsuario.getArvoreUsuario().inserir(new NoBinaria(new Usuario(user[0], user[1], user[2], vip)));
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
	 */
	public static void reescreverArquivoZmu() {

		ArrayList<NoBinaria> lista = new ArrayList<NoBinaria>();

		RepositorioUsuario.getArvoreUsuario().arvoreToArrayList(lista);

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
	
	
	
	//------------------------------------------ Inicio - Métodos Playlist -------------------------------
	/**
	 * Método cria um arquivo (.zmf) de acesso a playlist de usuaŕios VIPs.
	 * 
	 * @param user
	 */
	public static void criarArquivoUserVip(String nome) {
		BufferedWriter buffWrite;
		String path = PATHVIPFILES+nome+".zmf";		
		System.out.println("criando arquivo zmf de"+nome);
		
		try {
			File file = new File(path);	
			buffWrite = new BufferedWriter(new FileWriter(path, true));
			buffWrite.write(nome+"\n");
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
	public static void excluirArquivoUserVip(String nome) {
		BufferedWriter buffWrite;
		String path = PATHVIPFILES+nome+".zmf";		
		System.out.println("excluindo arquivo zmf de "+nome);
		File file = new File(path);	
		file.delete();
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
	 * Método que carrega a ObservableList da tabela de My Playlists
	 */
	public static void lerArquivoUserVip(Usuario user) {
		BufferedReader buffRead;
		String path = PATHVIPFILES+user.getId()+".zmf";
		try {
			buffRead = new BufferedReader(new FileReader(path));
			if (buffRead != null) {
				String linha;
				while (true) {
					linha = buffRead.readLine();
					if (linha != null) {
						String pĺaylist[] = linha.split(";");
						PlaylistTabela novo = new PlaylistTabela(pĺaylist[0], pĺaylist[1]);
						PlaylistController.getInstance().inserirListaPT(novo);

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
	 * Método lê um arquivo (.zmp) referente a uma playlist de um usuário
	 * 
	 * @param user
	 */
	public static void lerPlaylist(String nome) {
		BufferedReader buffRead;
		String path = PATHPLAYLIST+OperationalController.getSessao().getUser().getId()+"_"+nome+".zmp";		
		int cont = 1;
		try {
			buffRead = new BufferedReader(new FileReader(path));
			if (buffRead != null) {
				String linha;
				while (true) {
					linha = buffRead.readLine();
					if (linha != null) {
						String musica[] = linha.split(";");
						MusicaTable novo = new MusicaTable(cont,musica[0], musica[1]);
						PlaylistController.getInstance().inserirListaMTPlaylist(novo);
						cont++;

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
	 * Método exclui todas as playlist do usuário
	 */
	public static void excluirTodasPlaylist(String nomeUsuario) {
		File pasta = new File(PATHPLAYLIST);    
		File[] arquivos = pasta.listFiles();    
		for(File arquivo : arquivos) {
		    if(arquivo.getName().startsWith(nomeUsuario) && arquivo.getName().endsWith("zmp")) {
		        arquivo.delete();
		    }
		}
	}
	
	public static void excluirPlaylist(String nomePlaylist){
		String nomeUsuario = OperationalController.getSessao().getUser().getId();
		String path = PATHPLAYLIST+nomeUsuario+"_"+nomePlaylist+".zmp";		
		System.out.println("excluindo pĺaylist"+nomePlaylist+" de "+nomeUsuario);
		File file = new File(path);	
		file.delete();
	}
	
	/**
	 * Método retorna um arquivo de playlist(.zmp) com as musicas cadastradas.
	 * 
	 * @param user
	 */
	public static File getArquivoPlaylist(String nomePlaylist) {
		String nomeUsuario = OperationalController.getSessao().getUser().getId();
		String path = PATHPLAYLIST+nomeUsuario+"_"+nomePlaylist+".zmp";
		return new File(path);
		
	}
	
	/**
	 * Método retorna o caminho do arquivo de playlist(.zmp).
	 * 
	 * @param user
	 */
	public static String getPathArquivoPlaylist(String nomePlaylist) {
		String nomeUsuario = OperationalController.getSessao().getUser().getId();
		String path = PATHPLAYLIST+nomeUsuario+"_"+nomePlaylist+".zmp";
		return path;
		
	}
	
	public static void addMusicToPlaylistFile(MusicaTable musicaNova, String playlistPath) {
		BufferedWriter bw;
		System.out.println("adcionando musica no file: "+musicaNova.getNome());
		try {
			File file = new File(playlistPath);
			bw = new BufferedWriter(new FileWriter(file.getPath(),true));
			bw.write(musicaNova.getNome()+";/"+musicaNova.getLocal()+"\n");
			bw.flush();
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
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
	
	public static void removePlaylistOfUserFile(String nomePlaylist,String pathUserfile) {
		BufferedReader buffRead;
		BufferedWriter buffWrite;
		
		ArrayList <String> linhasNaoRemovidas = new ArrayList<String>();
		
		//Lendo arquivo e salvando as linhas que devem permanecer no arquivo
		try {
			buffRead = new BufferedReader(new FileReader(pathUserfile));
			if (buffRead != null) {
				String linha;
				while (true) {
					linha = buffRead.readLine();
					if (linha != null) {
						String nome[] = linha.split(";");
						//se o nome do arquivo nao for o que deve ser removido, coloca no array
						if(!nome[0].equals(nomePlaylist)){
							linhasNaoRemovidas.add(linha);
						}
					} else {
						break;
					}
				}
			}
			buffRead.close();
		} catch (IOException e) {
			e.getMessage();
		}
		//exclui o arquivo user file
		excluirArquivoUserVip(OperationalController.getSessao().getUser().getId());
		
		//cria novamente
		try {
			buffWrite = new BufferedWriter(new FileWriter(pathUserfile));
			for(String linha: linhasNaoRemovidas){
				buffWrite.write(linha + "\n");
				buffWrite.flush();
			}			
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}
		
	}
	//------------------------------------------ Fim - Métodos Playlis -------------------------------

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

	

	

}
