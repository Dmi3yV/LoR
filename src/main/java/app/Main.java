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
	private Stage stage; //����
	private Scene scene; //�����
	private AnchorPane pane; //�������� ���������
	
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
		this.stage = stage; //��������� ����
		try {
			pane = (AnchorPane) FXMLLoader.load(Main.class.getResource("main.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(pane, 600, 400); //������� �����
		stage.setResizable(false); //������ ��������� ������� ����
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.jpg"))); // ������ ����
        stage.setTitle("������ ������ ��"); //��������� ����
		stage.setScene(scene); //��������� ����� � ����
		stage.show(); //�������� ����
	}
	
	public static void main(String[] args) {
		launch(args);		
	}

	//���� ����� ���������� ����� ����� ���� ��� ����� ���������� ����� �� fxml �����
	@FXML
	public void initialize() {
		
		//������������� ������� ������
		LevelofLoss xsloss = new LevelofLoss(1,"�����","�������� � �������������� ������� ������������ �������, ������� ������ �����������������, ��� � ��������������� ������� �� ��������� ��������");
		LevelofLoss sloss = new LevelofLoss(2,"���������","�������� �������� ������ ������������ ������� ��� ��������� ������� �� ��������� ��������");
		LevelofLoss mloss = new LevelofLoss(3,"C������","�������� � ������������ ������� ������������ ������� ��� ������������� ����� ��������� ��������");
		LevelofLoss lloss = new LevelofLoss(4,"�������","�������� ������� ������ ������������ ������� � ������� ������� ���� ��������� ��������");
		LevelofLoss xlloss = new LevelofLoss(5,"�����������","�������� � ����������� ������� ������������ ������� ��� � ������ ������ ��������� �������� �� �����");

		//�������� ������� ������� ������
		ObservableList<LevelofLoss> losses = FXCollections.observableArrayList(xsloss, sloss, mloss, lloss, xlloss);
		
		//���������� ������� ������� ������ � choiceBox
		cb1.setItems(losses);
		
		//���������, ������� ����� ��������� �������� � choiceBox'e
		cb1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LevelofLoss>() {
			public void changed(ObservableValue<? extends LevelofLoss> ov, LevelofLoss old_value, LevelofLoss new_value) {
				text1.setText(new_value.getDescription());}
		});
		
		// ���������� ������� ������ -> �������������		
		LevelofProbabilityAttack xsattack = new LevelofProbabilityAttack(1,"����� ������","����� ����������� ������� �� ����� ���������. ����������� ����� - [0,0.15]");
		LevelofProbabilityAttack sattack = new LevelofProbabilityAttack(2,"������","����������� ���������� ����� ���������� ������ - (0.15,0.35]");
		LevelofProbabilityAttack mattack = new LevelofProbabilityAttack(3,"�������","����������� ���������� ����� - (0.35,0.65]");
		LevelofProbabilityAttack lattack = new LevelofProbabilityAttack(4,"�������","����� ������ ����� ����� ���������. ����������� ����� - (0.65,0.85]");
		LevelofProbabilityAttack xlattack = new LevelofProbabilityAttack(5,"����� �������","����� ����� ��������� ����� ���������. ����������� ����� - (0.85,1]");
		//�������� �������
		ObservableList<LevelofProbabilityAttack> attacks = FXCollections.observableArrayList(xsattack, sattack, mattack, lattack, xlattack);
		//���������� ���������
		cb2.setItems(attacks);
		//�������
		cb2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LevelofProbabilityAttack>() {
			public void changed(ObservableValue<? extends LevelofProbabilityAttack> ov, LevelofProbabilityAttack old_value, LevelofProbabilityAttack new_value) {
				text2.setText(new_value.getDescription());}
		});		
	}
		
	//���������� ������� -- ������� �� ������ "���������"
	@FXML 
	public void report() {	
			
		if (cb1.getValue()==null||cb2.getValue()==null)
			{text.setText("ERROR!!! �������� �������� �� ������!!!");}
		else {
			LevelofLoss a = new LevelofLoss(cb1.getValue());
			LevelofProbabilityAttack b = new LevelofProbabilityAttack(cb2.getValue());

			if (((a.getId()==1||a.getId()==2||a.getId()==3)&&b.getId()==1)||((a.getId()==1||a.getId()==2)&&b.getId()==2)||(a.getId()==1&&b.getId()==3))
				{text.setText("�� �������:\n * " + a.getLevel() + " ������� ������\n * " + b.getLevel() + " ������� ����������� ����� \n��� ��������� -----> ������ ���� ��");}
			else if ((a.getId()==5&&(b.getId()==2||b.getId()==3||b.getId()==4||b.getId()==5))||(b.getId()==5&&(a.getId()==2||a.getId()==3||a.getId()==4||a.getId()==5)))
				{text.setText("�� �������:\n * " + a.getLevel() + " ������� ������\n * " + b.getLevel() + " ������� ����������� �����\n��� ��������� -----> ������� ���� ��");}
			else
				{text.setText("�� �������:\n * " + a.getLevel() + " ������� ������\n * " + b.getLevel() + " ������� ����������� ����� \n��� ��������� -----> ������� ���� ��");}
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
	
	//�������
	@FXML
	public void ref() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFORMATION about this program");
		alert.setHeaderText("��������� ������������� ��� ���������� ������ ����� �������������� ������������\n"
				+ "�� ������ ��������� ������ �� ������ ������ � ����������� ���������� �����");
		alert.setContentText("This program by VDV version 1.1");
		alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
			public void accept(ButtonType rs) {
			    rs = ButtonType.OK;
			}
		});
	}
	
	//���������� ������
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