package view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Jogo;
import model.Time;
import utility.Dados;

public class PrincipalController implements Initializable {

    Time teste2, teste1;
    private List<Time> lstPrinc = new ArrayList<Time>();
    private List<Jogo> lstAux = new ArrayList<Jogo>();
    private Dados dados;

    @FXML
    private TableView tblVwTimes;
    @FXML
    private TableView tblVwJogos;
    @FXML
    private Label lblNomeTime;
    @FXML
    private StackPane pnJogos;
    @FXML
    private MenuItem mnJogos;
    @FXML
    private Button btnAbrir;
    @FXML
    private MenuItem mnJogosFechar;
    @FXML
    private MenuItem mnTimes;
    @FXML
    private MenuItem mnFiltrar;
    @FXML
    private Button btnEmCasa;
    @FXML
    private Button btnGanhou;
    @FXML
    private Button btnPerdeu;
    @FXML
    private Button btnGanhouCasa;
    @FXML
    private Button btnPerdeuCasa;
    @FXML
    private Button btnExibirTudo;
    @FXML
    private Button btnSalvar;
    
    @FXML
    private void SalvarClick(){
        if(tblVwTimes.getSelectionModel().getSelectedItem()!=null){
            dados.gravarJogosTime((Time)tblVwTimes.getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    private void AbrirClick() {
        final Stage stage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha o seu arquivo:");
        fileChooser.setInitialDirectory(new File("C:\\Engenharia-de-Software\\2-Ano\\Linguagens-de-Programacao"));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Text Files", "*.txt"
        );
        fileChooser.getExtensionFilters().add(extFilter);

        dados = new Dados(String.valueOf(fileChooser.showOpenDialog(stage)));
        // Cria o objeto Dados na memória passando por parâmetro o nome.

        lstPrinc = dados.ler();
        mnJogos.setDisable(false);

//        teste1 = new Time("Inter", 2, 1, 1, 10, 5);
//        teste1.setClas((byte) 1);
//        lstPrinc.add(teste1);
//
//        teste2 = new Time("Gremio", 3, 1, 2, 13, 7);
//        teste2.setClas((byte) 2);
//        lstPrinc.add(teste2);
        tblVwTimes.setItems(FXCollections.observableList(lstPrinc));

    }

    @FXML
    private void tblVwTimesClick(Event event) {
        Time timeSel = null;
        MouseEvent me = null;
        if (event.getEventType() == MOUSE_CLICKED) {
            me = (MouseEvent) event;
            if (me.getClickCount() == 2) {
                //Nova rotina mostra time
                mostraJogosTime((Time) tblVwTimes.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    private void mnJogosClick() {
        if (tblVwTimes.getSelectionModel().getSelectedItem() != null) {
            mostraJogosTime((Time) tblVwTimes.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void mostraJogosTime(Time timeSelecionado) {
        pnJogos.setVisible(true);
        tblVwTimes.setVisible(false);
        btnAbrir.setVisible(false);
        mnFiltrar.setDisable(false);
        mnJogosFechar.setDisable(false);
        mnJogos.setDisable(true);
        lblNomeTime.setText(timeSelecionado.getNome());

        tblVwJogos.setItems(FXCollections.observableList(timeSelecionado.getJogos()));
        System.out.println(timeSelecionado.getJogos());
    }

    @FXML
    private void FecharJogosClick() {
        pnJogos.setVisible(false);
        btnAbrir.setVisible(true);
        mnFiltrar.setDisable(true);
        tblVwTimes.setVisible(true);
        mnJogosFechar.setDisable(true);
        mnJogos.setDisable(false);
        tblVwTimes.getSelectionModel().clearSelection();
    }

    @FXML
    private void FiltrarEmCasaClick() {
        Time timeSelecionado;
        timeSelecionado = (Time) tblVwTimes.getSelectionModel().getSelectedItem();
        lstAux.clear();
        
        for (Jogo j : timeSelecionado.getJogos()) {
            if (j.getTimeA().equals(timeSelecionado.getNome())) {
                lstAux.add(j);
            }
        }
        tblVwJogos.getSelectionModel().clearSelection();
        tblVwJogos.setItems(FXCollections.observableList(lstAux));
    }

    @FXML
    private void FiltrarVisitanteClick() {
        Time timeSelecionado;
        timeSelecionado = (Time) tblVwTimes.getSelectionModel().getSelectedItem();
        lstAux.clear();
        for (Jogo j : timeSelecionado.getJogos()) {
            if (j.getTimeB().equals(timeSelecionado.getNome())) {
                lstAux.add(j);
            }
        }
        tblVwJogos.getSelectionModel().clearSelection();
        tblVwJogos.setItems(FXCollections.observableList(lstAux));

    }

    @FXML
    private void FiltrarPerdeuClick() {
        Time timeSelecionado;
        lstAux.clear();
        timeSelecionado = (Time) tblVwTimes.getSelectionModel().getSelectedItem();
       
        for (Jogo j : timeSelecionado.getJogos()) {
            if ((j.getTimeA().equals(timeSelecionado.getNome()) && (j.getGolA() < j.getGolB())) || (j.getTimeB().equals(timeSelecionado.getNome()) && (j.getGolB() < j.getGolA()))) {
                lstAux.add(j);
            }
        }
        tblVwJogos.getSelectionModel().clearSelection();
        tblVwJogos.setItems(FXCollections.observableList(lstAux));

    }

    @FXML
    private void FiltrarPerdeuCasaClick() {
        Time timeSelecionado;
        lstAux.clear();
        timeSelecionado = (Time) tblVwTimes.getSelectionModel().getSelectedItem();
        for (Jogo j : timeSelecionado.getJogos()) {
            if ((j.getTimeA().equals(timeSelecionado.getNome()) && (j.getGolA() < j.getGolB()))) {
                lstAux.add(j);
            }
        }
        tblVwJogos.getSelectionModel().clearSelection();
        tblVwJogos.setItems(FXCollections.observableList(lstAux));

    }

    @FXML
    private void FiltrarGanhouClick() {
        Time timeSelecionado;
        timeSelecionado = (Time) tblVwTimes.getSelectionModel().getSelectedItem();
        lstAux.clear();
        for (Jogo j : timeSelecionado.getJogos()) {
            if ((j.getTimeA().equals(timeSelecionado.getNome()) && (j.getGolA() > j.getGolB())) || (j.getTimeB().equals(timeSelecionado.getNome()) && (j.getGolB() > j.getGolA()))) {
                lstAux.add(j);
            }
        }
        tblVwJogos.getSelectionModel().clearSelection();
        tblVwJogos.setItems(FXCollections.observableList(lstAux));

    }

    @FXML
    private void FiltrarGanhouCasaClick() {
        Time timeSelecionado;
        timeSelecionado = (Time) tblVwTimes.getSelectionModel().getSelectedItem();
        lstAux.clear();
        for (Jogo j : timeSelecionado.getJogos()) {
            if ((j.getTimeA().equals(timeSelecionado.getNome()) && (j.getGolA() > j.getGolB()))) {
                lstAux.add(j);
            }
        }
        tblVwJogos.getSelectionModel().clearSelection();
        tblVwJogos.setItems(FXCollections.observableList(lstAux));

    }

    @FXML
    private void mnFecharClick() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pnJogos.setVisible(false);
        mnTimes.disableProperty().bind(tblVwTimes.getSelectionModel().selectedItemProperty().isNull());
        
//Interceptando e fabricando uma nova linha
        tblVwTimes.setRowFactory(TableView
                -> {
            TableRow<Time> row = new TableRow<>();
            row.itemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null
                        && (newValue.getClas() <= 3)) {
                    row.getStyleClass().add("itemDestaque");
                } else {
                    row.getStyleClass().remove("itemDestaque");
                }
            });
            return row;
        });
    }
}
