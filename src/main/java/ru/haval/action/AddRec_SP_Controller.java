package ru.haval.action;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;

import  ru.haval.application.conn_connector;
import  ru.haval.db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class AddRec_SP_Controller {
	
	@FXML
	TextField txt_HMMR_Material_Num, txt_Manufacturer, txt_Model, txt_Article, txt_Risk_Breakage, txt_Delivery_Time, txt_Replacement_Model, txt_Identity_SP, txt_Coefficient, txt_Price, 
	txt_Kind, txt_SP_Part_Type, txt_SP_Sub_Part_Type, txt_Part_Characteristic_1, txt_Part_Characteristic_2, txt_Part_Characteristic_3, txt_Part_Characteristic_4, txt_Qty_S, 
	txt_Qty_W, txt_Qty_P, txt_Qty_A, txt_Key_No_Backup_Yes, txt_Key_No_Backup_No, txt_Key_Yes_Backup_Yes, txt_Key_Yes_Backup_No, txt_Qty_Interchangeability, txt_Qty_Identify_SP,
	txt_MIN, txt_BATCH;
	
	@FXML
	TextArea txt_SP_MU_Description_RUS, txt_SP_FD_Description, txt_SP_Supplier_Description;
	
	@FXML
	ComboBox<String> list_Manufacturer, list_Model, list_Single_Complex_Sub, list_part_type;
	
	@FXML
	JFXButton btn_add_rec, btn_cancel, btn_mtc, btn_mtcsp;
	
	@FXML
	Label lbl_title_sp;
	
	@FXML
	Tab general, info;
	
	ObservableList<String> list_type = FXCollections.observableArrayList();
	ObservableList<String> list_Material_Num = FXCollections.observableArrayList();
	ObservableList<String> list_interchangeable_identify = FXCollections.observableArrayList();
	ObservableList<String> list_parttype = FXCollections.observableArrayList();
		
	_query qr = new _query();
	s_class scl = new s_class();
	SP_Controller spc = new SP_Controller();
	Stage stage;
	
	String _id_pchar, _part_type = "0", _part_sub_type = "0";
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		scl._style(btn_add_rec);
		scl._style(btn_cancel);
		scl._style1(btn_mtc);
		scl._style1(btn_mtcsp);
		
		txt_HMMR_Material_Num.setDisable(true);
		
		btn_add_rec.setDisable(true);
		
		list_Manufacturer.setItems(qr._select_list_str("hmmr_sp_db", "Manufacturer"));
		list_Model.setItems(qr._select_list_str("hmmr_sp_db", "Model"));
		list_Material_Num.addAll(qr._select_list_str("hmmr_sp_db", "HMMR_Material_Num"));
		list_interchangeable_identify.addAll(qr._select_recArr("hmmr_sp_db", "SP_MU_Description_RUS", "Model", "del_rec"));
		list_part_type.setItems(qr._select_recArr("hmmr_part_characteristic", "Part_Type", "Part_Sub_Type", "del_rec"));
				
		list_type.add("Single");
		list_type.add("Complex");
		list_type.add("Sub-part");
		
		list_Single_Complex_Sub.setItems(list_type);
		
		
		if(!spc._flag_sp_window) {
			lbl_title_sp.setText("Обновление записи SP DB");
			info.setDisable(false);
			btn_mtc.setDisable(true);
			btn_mtcsp.setDisable(true);
			
			String _sql_rez = qr._select_for_update_sp(spc._id_sp);
			txt_HMMR_Material_Num.setText(scl.parser_str_str_str(_sql_rez, 0));
			txt_Manufacturer.setText(scl.parser_str_str_str(_sql_rez, 1));
			txt_Model.setText(scl.parser_str_str_str(_sql_rez, 2));
			txt_Article.setText(scl.parser_str_str_str(_sql_rez, 3));
			list_Single_Complex_Sub.getSelectionModel().select(scl.parser_str_str_str(_sql_rez, 4));
			txt_SP_MU_Description_RUS.setText(scl.parser_str_str_str(_sql_rez, 5));
			txt_SP_FD_Description.setText(scl.parser_str_str_str(_sql_rez, 6));
			txt_SP_Supplier_Description.setText(scl.parser_str_str_str(_sql_rez, 7));
			txt_Price.setText(scl.parser_str_str_str(_sql_rez, 8));
			txt_Risk_Breakage.setText(scl.parser_str_str_str(_sql_rez, 9));
			txt_Delivery_Time.setText(scl.parser_str_str_str(_sql_rez, 10));
			txt_Replacement_Model.setText(scl.parser_str_str_str(_sql_rez, 11));
			txt_Identity_SP.setText(scl.parser_str_str_str(_sql_rez, 12));
			txt_Coefficient.setText(scl.parser_str_str_str(_sql_rez, 13));
			_id_pchar = scl.parser_str_str_str(_sql_rez, 14);
			txt_Qty_S.setText(scl.parser_str_str_str(_sql_rez, 15));
			txt_Qty_W.setText(scl.parser_str_str_str(_sql_rez, 16));
			txt_Qty_P.setText(scl.parser_str_str_str(_sql_rez, 17));
			txt_Qty_A.setText(scl.parser_str_str_str(_sql_rez, 18));
			txt_Key_No_Backup_Yes.setText(scl.parser_str_str_str(_sql_rez, 19));
			txt_Key_No_Backup_No.setText(scl.parser_str_str_str(_sql_rez, 20));
			txt_Key_Yes_Backup_Yes.setText(scl.parser_str_str_str(_sql_rez, 21));
			txt_Key_Yes_Backup_No.setText(scl.parser_str_str_str(_sql_rez, 22));
			
			if(!_id_pchar.equals("0")) {
				String _sql_rez_rez = qr._select_for_update_partchar(_id_pchar);
				
				txt_SP_Part_Type.setText(scl.parser_str_str_str(_sql_rez_rez, 0));
				txt_Kind.setText(scl.parser_str_str_str(_sql_rez_rez, 1));
				txt_SP_Sub_Part_Type.setText(scl.parser_str_str_str(_sql_rez_rez, 2));
				txt_Part_Characteristic_1.setText(scl.parser_str_str_str(_sql_rez_rez, 4));
				txt_Part_Characteristic_2.setText(scl.parser_str_str_str(_sql_rez_rez, 5));
				txt_Part_Characteristic_3.setText(scl.parser_str_str_str(_sql_rez_rez, 6));
				txt_Part_Characteristic_4.setText(scl.parser_str_str_str(_sql_rez_rez, 7));
			}
			else
			{
				txt_SP_Part_Type.setText("-");
				txt_Kind.setText("-");
				txt_SP_Sub_Part_Type.setText("-");
				txt_Part_Characteristic_1.setText("-");
				txt_Part_Characteristic_2.setText("-");
				txt_Part_Characteristic_3.setText("-");
				txt_Part_Characteristic_4.setText("-");
			}
			
		}
		else {
			lbl_title_sp.setText("Добавление записи к SP DB");
			info.setDisable(true);
		}
		
		TextFields.bindAutoCompletion(txt_HMMR_Material_Num, list_Material_Num);
		TextFields.bindAutoCompletion(txt_Replacement_Model, list_interchangeable_identify);
		TextFields.bindAutoCompletion(txt_Identity_SP, list_interchangeable_identify);
		
		btn_mtc.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				btn_mtc.setDisable(true);
				btn_mtcsp.setDisable(false);
				txt_HMMR_Material_Num.setText(num_sum(qr._select_last_matnum(11)));
				qr._insert_sp(txt_HMMR_Material_Num.getText(), "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-");
				
			}
		});
		btn_mtcsp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				btn_mtc.setDisable(false);
				btn_mtcsp.setDisable(true);
				txt_HMMR_Material_Num.setText(num_sum(qr._select_last_matnum(14)));
				qr._insert_sp(txt_HMMR_Material_Num.getText(), "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-");
			}
		});
				
		list_Manufacturer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				txt_Manufacturer.setText(list_Manufacturer.getValue());
			}
		});
		list_Model.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				txt_Model.setText(list_Model.getValue());
			}
		});
		list_part_type.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					txt_SP_MU_Description_RUS.setText(scl.parser_str(list_part_type.getValue(), 0));
					_part_type = scl.parser_str(list_part_type.getValue(), 0);
					_part_sub_type = scl.parser_str(list_part_type.getValue(), 1);
				}
				catch (Exception e) {
					
				}
			}
		});
		
		txt_HMMR_Material_Num.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Manufacturer.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Model.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Article.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		list_Single_Complex_Sub.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		txt_SP_MU_Description_RUS.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_SP_FD_Description.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_SP_Supplier_Description.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Risk_Breakage.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Delivery_Time.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Replacement_Model.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Identity_SP.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Coefficient.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Price.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		
		
		btn_add_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String Id_Intercangeable = "-", Id_Identify = "-";
				if(!txt_Replacement_Model.getText().equals("-")) {
					String Desc_interchangeable = scl.parser_str(txt_Replacement_Model.getText(), 0);
					String Model_Interchangeable = scl.parser_str(txt_Replacement_Model.getText(), 1);
					if(!Model_Interchangeable.equals("null"))
						Id_Intercangeable = qr._select_recStr("hmmr_sp_db", "id", "del_rec", "SP_MU_Description_RUS", Desc_interchangeable, "Model", Model_Interchangeable);
					else
						scl._AlertDialog("ВНИМАНИЕ!!! Модель для "+Desc_interchangeable+" не определена!\nПоиск не возможен!", "Внимание!");
				}
				if(!txt_Identity_SP.getText().equals("-")) {
					String Desc_Identify = scl.parser_str(txt_Identity_SP.getText(), 0);
					String Model_Identify = scl.parser_str(txt_Identity_SP.getText(), 1);
					if(!Model_Identify.equals("null"))
						Id_Identify = qr._select_recStr("hmmr_sp_db", "id", "del_rec", "SP_MU_Description_RUS", Desc_Identify, "Model", Model_Identify);
					else
						scl._AlertDialog("ВНИМАНИЕ!!! Модель для "+Desc_Identify+" не определена!\nПоиск не возможен!", "Внимание!");
				}
				
				if(!qr._select_for_pchar(_part_type, _part_sub_type).equals("NULL"))
					_id_pchar = qr._select_for_pchar(_part_type, _part_sub_type);
				else
					_id_pchar = "0";
					
				
				if(spc._flag_sp_window) {
					qr._update_sp_ins(txt_HMMR_Material_Num.getText(), txt_Manufacturer.getText(), txt_Model.getText(), txt_Article.getText(), list_Single_Complex_Sub.getValue(), txt_SP_MU_Description_RUS.getText(), txt_SP_FD_Description.getText(), txt_SP_Supplier_Description.getText(), _id_pchar, txt_Price.getText(), txt_Risk_Breakage.getText(), txt_Delivery_Time.getText(), Id_Intercangeable, Id_Identify, txt_Coefficient.getText());
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_sp_db") + " в SP DB");
				}
				else {
					qr._update_sp(spc._id_sp, txt_HMMR_Material_Num.getText(), txt_Manufacturer.getText(), txt_Model.getText(), txt_Article.getText(), list_Single_Complex_Sub.getValue(), txt_SP_MU_Description_RUS.getText(), txt_SP_FD_Description.getText(), txt_SP_Supplier_Description.getText(), txt_Price.getText(), txt_Risk_Breakage.getText(), txt_Delivery_Time.getText(), Id_Intercangeable, Id_Identify, txt_Coefficient.getText());
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + spc._id_sp + " в SP DB");
				}
				if(spc._flag_sort_spdb == 0)
					spc._table_update_sp.addAll(qr._select_data_sp());
				else if(spc._flag_sort_spdb == 1)
					spc._table_update_sp.addAll(qr._select_data_sp_sort(spc._value_sort_manuf));
				
				stage = (Stage) btn_add_rec.getScene().getWindow();
				stage.close();
			}
		});
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) btn_cancel.getScene().getWindow();
				stage.close();
			}
		});
		
		btn_mtc.requestFocus();
	}
	
	String num_sum(String num)
	{
		boolean isS = num.startsWith("MTC");
		String S_Rez = "NULL";
		if(isS)
		{
			boolean isSP = num.startsWith("SP", 3);
			//Если номер начинается с MTCSP
			if(isSP)
			{
				String s_cut = num.substring(5);
				
				for(int i = 0; i < s_cut.length(); i++)
				{
					String Cnt_Zero = "0";
					
					if(i == 1)
						Cnt_Zero = "0";
					if(i == 2)
						Cnt_Zero = "00";
					if(i == 3)
						Cnt_Zero = "000";
					if(i == 4)
						Cnt_Zero = "0000";
					if(i == 5)
						Cnt_Zero = "00000";
					if(i == 6)
						Cnt_Zero = "000000";
					if(i == 7)
						Cnt_Zero = "0000000";
					if(!s_cut.substring(i, i+1).equals("0"))
					{
						int Rez = Integer.parseInt(s_cut) + 1;
						S_Rez = "MTCSP" + Cnt_Zero + Rez;
						break;
					}
				}
			}
			else
			{
				String s_cut = num.substring(3);
				
				for(int i = 0; i < s_cut.length(); i++)
				{
					String Cnt_Zero = "0";
					
					if(i == 1)
						Cnt_Zero = "0";
					if(i == 2)
						Cnt_Zero = "00";
					if(i == 3)
						Cnt_Zero = "000";
					if(i == 4)
						Cnt_Zero = "0000";
					if(i == 5)
						Cnt_Zero = "00000";
					if(i == 6)
						Cnt_Zero = "000000";
										
					if(!s_cut.substring(i, i+1).equals("0"))
					{
						int Rez = Integer.parseInt(s_cut) + 1;
						S_Rez = "MTC" + Cnt_Zero + Rez;
						break;
					}
				}
			}
		}
		return S_Rez;
	}
	
	void chk_btn()
	{
		try {
			if(txt_HMMR_Material_Num.getText().length() != 0 && txt_Manufacturer.getText().length() != 0 && txt_Model.getText().length() != 0 && txt_Article.getText().length() != 0 && 
					list_Single_Complex_Sub.getValue().length() != 0 && txt_SP_MU_Description_RUS.getText().length() != 0 && txt_SP_FD_Description.getText().length() != 0 && 
					txt_SP_Supplier_Description.getText().length() != 0 && txt_Risk_Breakage.getText().length() != 0 && txt_Delivery_Time.getText().length() != 0 && 
					txt_Replacement_Model.getText().length() != 0 && txt_Identity_SP.getText().length() != 0 && txt_Coefficient.getText().length() != 0 && txt_Price.getText().length() != 0)
				btn_add_rec.setDisable(false);
			else
				btn_add_rec.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}

}
