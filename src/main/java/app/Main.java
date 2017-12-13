package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	@SuppressWarnings("unused")
	private Stage stage; //окно
	private Scene scene; //сцена
	private AnchorPane pane; //менеджер отрисовки
	
	@FXML
	private Button btn; 
	@FXML
	private Button start; 
	@FXML
	private TextArea text;
	@FXML
	private TextArea text1;
	@FXML
	private TextArea text2;
	@FXML
	private MenuItem close;
	@FXML 
	public ChoiceBox<LevelofLoss> cb1;
	@FXML 
	public ChoiceBox<LevelofProbabilityAttack> cb2;
	
	@Override
	public void start(Stage stage) {
		this.stage = stage; //сохранить окно
		try {
			pane = (AnchorPane) FXMLLoader.load(Main.class.getResource("main.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(pane, 600, 400); //создать сцену
		stage.setResizable(false); //запрет изменений размера окна
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.jpg"))); // иконка окна
        stage.setTitle("Расчет рисков ИБ"); //заголовок окна
		stage.setScene(scene); //поместить сцену в окно
		stage.show(); //показать окно
	}
	
	public static void main(String[] args) {
		launch(args);		
	}

	//этот метод вызывается сразу после того как будет подгружена сцена из fxml файла
	@FXML
	public void initialize() {
		
		//инициализация уровней ущерба
		LevelofLoss xsloss = new LevelofLoss(1,"Малый","Приводит к незначительным потерям материальных активов, которые быстро восстанавливаются, или к незначительному влиянию на репутацию компании");
		LevelofLoss sloss = new LevelofLoss(2,"Умеренный","Вызывает заметные потери материальных активов или умеренное влияние на репутацию компании");
		LevelofLoss mloss = new LevelofLoss(3,"Cредний","Приводит к существенным потерям материальных активов или значительному урону репутации компании");
		LevelofLoss lloss = new LevelofLoss(4,"Большой","Вызывает большие потери материальных активов и наносит большой урон репутации компании");
		LevelofLoss xlloss = new LevelofLoss(5,"Критический","Приводит к критическим потерям материальных активов или к полной потере репутации компании на рынке");

		//создание массива уровней ущерба
		ObservableList<LevelofLoss> losses = FXCollections.observableArrayList(xsloss, sloss, mloss, lloss, xlloss);
		
		//добавление массива уровней ущерба в choiceBox
		cb1.setItems(losses);
		
		//слушатель, который ловит изменение значения в choiceBox'e
		cb1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LevelofLoss>() {
			public void changed(ObservableValue<? extends LevelofLoss> ov, LevelofLoss old_value, LevelofLoss new_value) {
				text1.setText(new_value.getDescription());}
		});
		
		// аналогично уровням ущерба -> инициализация		
		LevelofProbabilityAttack xsattack = new LevelofProbabilityAttack(1,"Очень низкий","Атака практически никогда не будет проведена. Вероятность атаки - [0,0.15]");
		LevelofProbabilityAttack sattack = new LevelofProbabilityAttack(2,"Низкий","Вероятность проведения атаки достаточно низкая - (0.15,0.35]");
		LevelofProbabilityAttack mattack = new LevelofProbabilityAttack(3,"Средний","Вероятность проведения атаки - (0.35,0.65]");
		LevelofProbabilityAttack lattack = new LevelofProbabilityAttack(4,"Высокий","Атака скорее всего будет проведена. Вероятность атаки - (0.65,0.85]");
		LevelofProbabilityAttack xlattack = new LevelofProbabilityAttack(5,"Очень высокий","Атака почти наверняка будет проведена. Вероятность атаки - (0.85,1]");
		//создание массива
		ObservableList<LevelofProbabilityAttack> attacks = FXCollections.observableArrayList(xsattack, sattack, mattack, lattack, xlattack);
		//заполнение чойзбокса
		cb2.setItems(attacks);
		//лисенер
		cb2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LevelofProbabilityAttack>() {
			public void changed(ObservableValue<? extends LevelofProbabilityAttack> ov, LevelofProbabilityAttack old_value, LevelofProbabilityAttack new_value) {
				text2.setText(new_value.getDescription());}
		});		
	}
		
	//обработчик события -- нажатие на кнопку "Расчитать"
	@FXML 
	public void report() {	
			
		if (cb1.getValue()==null||cb2.getValue()==null)
			{text.setText("ERROR!!! Выберите значения из списка!!!");}
		else {
			LevelofLoss a = new LevelofLoss(cb1.getValue());
			LevelofProbabilityAttack b = new LevelofProbabilityAttack(cb2.getValue());

			if (((a.getId()==1||a.getId()==2||a.getId()==3)&&b.getId()==1)||((a.getId()==1||a.getId()==2)&&b.getId()==2)||(a.getId()==1&&b.getId()==3))
				{text.setText("ВЫ ВЫБРАЛИ:\n * " + a.getLevel() + " уровень ущебра\n * " + b.getLevel() + " уровень вероятности атаки \nВАШ РЕЗУЛЬТАТ -----> НИЗКИЙ РИСК ИБ");}
			else if ((a.getId()==5&&(b.getId()==2||b.getId()==3||b.getId()==4||b.getId()==5))||(b.getId()==5&&(a.getId()==2||a.getId()==3||a.getId()==4||a.getId()==5)))
				{text.setText("ВЫ ВЫБРАЛИ:\n * " + a.getLevel() + " уровень ущебра\n * " + b.getLevel() + " уровень вероятности атаки\nВАШ РЕЗУЛЬТАТ -----> ВЫСОКИЙ РИСК ИБ");}
			else
				{text.setText("ВЫ ВЫБРАЛИ:\n * " + a.getLevel() + " уровень ущебра\n * " + b.getLevel() + " уровень вероятности атаки \nВАШ РЕЗУЛЬТАТ -----> СРЕДНИЙ РИСК ИБ");}
		}
	}
	
	public int test(LevelofLoss a, LevelofProbabilityAttack b) {
		
		int i;		
		if (((a.getId()==1||a.getId()==2||a.getId()==3)&&b.getId()==1)||((a.getId()==1||a.getId()==2)&&b.getId()==2)||(a.getId()==1&&b.getId()==3))
			i = 1;
		else if ((a.getId()==5&&(b.getId()==2||b.getId()==3||b.getId()==4||b.getId()==5))||(b.getId()==5&&(a.getId()==2||a.getId()==3||a.getId()==4||a.getId()==5)))
			i = 3;
		else
			i = 2;		
		return i;		
	}
	
	//справка
	@FXML
	public void ref() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFORMATION about this program");
		alert.setHeaderText("Программа предназначена для вычисления уровня риска информационной безопасности\n"
				+ "на основе имеющихся данных об уровне ущерба и вероятности реализации атаки");
		alert.setContentText("This program by VDV version 1.1");
		alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
			public void accept(ButtonType rs) {
			    rs = ButtonType.OK;
			}
		});
	}
	
	//сохранение отчета
    @FXML
    public void save(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(stage);
        
        if(file != null){
                FileWriter fileWriter = null;
                 
                try {
					fileWriter = new FileWriter(file);
	                fileWriter.write(text.getText());
	                fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
    }
}