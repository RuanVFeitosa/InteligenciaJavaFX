package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Agentes;

public class AgentesRepository {
	private List<Agentes> files;
	private File database = new File("database-files.txt");;

	public AgentesRepository() {
		
		
		this.database = new File("database-files.txt");
		this.files = new ArrayList<>();

		// Carregar os dados
		loadFiles();
	}
	
	// Sobreescrever o arquivo
	private void saveFiles() {
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(database, false))) {
			for (Agentes agente : files) {
				String data = agente.getId() + ";" + agente.getNome() + ";" + agente.getInicioAgente() + ";"
						+ agente.getFimAgente();
				// Escreve uma linha e passa para a proxima
				writer.println(data);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado! Tente novamente");

		}
	}

	// Carregar
	private void loadFiles() {
		try (Scanner sc = new Scanner(database)) {
			while (sc.hasNextLine()) {

				
				String[] data = sc.nextLine().split(";");
				if (data.length >= 4) {
					// 0 -> id, 1 -> nome, 2 -> inicio, 3 -> fim
					Agentes agente = new Agentes();
					agente.setId(Integer.parseInt(data[0]));
					agente.setNome(data[1]);
					agente.setInicioAgente(data[2]);
					agente.setFimAgente(data[3]);
					files.add(agente);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado! Criando um novo arquivo");
			createIfFileNotExists();
		}
	}

	
	private void createIfFileNotExists() {
		try {
			if(!database.exists()) {
				database.createNewFile();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// CRUD -> Create, Read, Update and Delete

	// Update - Atualizar
	public void updateAgente(Agentes updatedAgente) {
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).getId() == updatedAgente.getId()) {
				files.set(i, updatedAgente);
				saveFiles();
				break;
			}
		}
		saveFiles();
	}

	// Buscar unico
	public Agentes getAgenteById(int id) {
		for (Agentes agente : files) {
			if (agente.getId() == id) {
				return agente;
			}
		}
		return null;
	}

	// Buscar todos
	public List<Agentes> buscarTodos() {
		return new ArrayList<>(files);

	}

	// Deletar Objeto Especifico
	public void deletarAgente(int id) {
		// Percorrer todo o Array, caso seja igual, ele vai remover
		files.removeIf(agente -> agente.getId() == id);
		saveFiles();
	}

	// Criar Agente
	public void addAgente(Agentes agente) {
		// Vai faltar o id
		agente.setId(1);
		files.add(agente);
		// Sobrescrever o arquivo database
		saveFiles();

	}

	public int getNextId() {
		int maxId = 0;
		for (Agentes agente : files) {
			if (agente.getId() > maxId) {
				maxId = agente.getId();
			}
		}
		return maxId + 1;
	}

}
