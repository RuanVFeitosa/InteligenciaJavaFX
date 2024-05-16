package repository;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import model.Agentes;

public class AgentesRepository {
	private List <Agentes> files;
	private File database;
	
	public AgentesRepository() {
		this.database = new File("database-files.txt"); 
		this.files = new ArrayList<>();
	}
	
	// CRUD -> Create, Read, Update and Delete
	
	//Buscar todos
	public List<Agentes> buscarTodos() {
		return new ArrayList<>(files);
		
	}
	
	//Deletar Objeto Especifico
	public void deletarAgente(int id) {
		//Percorrer todo o Array, caso seja igual, ele vai remover
		files.removeIf(agente -> agente.getId() == id);
	}
	
	//Criar Agente
	public void addAgente(Agentes agente) {
		// Vai faltar o id
		agente.setId(1);
		files.add(agente);
		
		
	}
	

}
