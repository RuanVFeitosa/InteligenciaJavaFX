package controller;

import java.io.BufferedReader;
import java.io.FileReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Agentes;
import repository.AgentesRepository;

public class AgentesController {

	// Crtrl + Espaço para importar

	@FXML
	private TableView<Agentes> tabelaAgentes;

	@FXML
	private TableColumn<Agentes, String> cAgente;

	@FXML
	private TableColumn<Agentes, String> cPatente;

	@FXML
	private TableColumn<Agentes, String> cIniciacao;

	@FXML
	private TextField nomeAgente;

	@FXML
	private TextField patenteAgente;

	@FXML
	private TextField iniciacaoAgente;

	@FXML
	private AgentesRepository repoAgentes;

	@FXML
	private ObservableList<Agentes> data;

	@FXML
	public void initialize() {
		cAgente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cPatente.setCellValueFactory(new PropertyValueFactory<>("inicioAgente"));
		cIniciacao.setCellValueFactory(new PropertyValueFactory<>("fimAgente"));

		// Instanciando lista obsarvable
		data = FXCollections.observableArrayList();

		// Tableview aceita somente ObservableList
		tabelaAgentes.setItems(data);
		repoAgentes = new AgentesRepository();

		// Preencher lista
		carregarDadosSalvos();
	}

	public void carregarDadosSalvos() {
		try (BufferedReader br = new BufferedReader(new FileReader("database-files.txt"))) {
			String line;

			// Caso linha não tenha registro, o while para
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (parts.length >= 4) {
					Agentes agente = new Agentes();

					agente.setId(Integer.parseInt(parts[0]));
					agente.setNome(parts[1]);
					agente.setInicioAgente(parts[2]);
					agente.setFimAgente(parts[3]);

					// Adicionar no observableList
					data.add(agente);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cadastrar() {
		Agentes agente = new Agentes();
		agente.setNome(nomeAgente.getText());
		agente.setInicioAgente(patenteAgente.getText());
		agente.setFimAgente(iniciacaoAgente.getText());
		repoAgentes.addAgente(agente);
		clearFields();

		System.out.println("Clicou no botão cadastrar");
	}

	public void clearFields() {
		nomeAgente.clear();
		patenteAgente.clear();

	}

	public void limpar() {
		System.out.println("Clicou no botão limpar");
	}

}
