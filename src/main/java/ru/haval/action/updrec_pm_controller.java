package ru.haval.action;

import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import ru.haval.application.conn_connector;
import ru.haval.db._query;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import ru.haval.share_class.s_class;

public class updrec_pm_controller {
	@FXML
	ComboBox<String> ninst_pm_upd, ool_pm_upd, otv_pm_upd, group_eq_upd, list_otv_isp_upd, shop_pm_upd, groupeq_pm_upd, lm_pm_upd, os_pm_upd, equip_pm_upd; //shop_pm_upd, group_pm_upd, lm_pm_upd, os_pm_upd, equip_pm_upd, pmname_pm_upd, pmcycle_pm_upd, pmtype_pm_upd, 
	
	@FXML
	TextField num_pm_upd;
	
	@FXML
	JFXButton confirm_pm_upd, cancel_pm_upd;
	
	@FXML
	Label lbl_head_pm, lbl_ninst_pm, lbl_ool_pm, lbl_otv_pm, lbl_group_eq, lbl_otv_isp, lbl_otv_isp1;//lbl_shop_pm, lbl_lm_pm, lbl_os_pm, lbl_equip_pm, lbl_group_pm, lbl_pmname_pm, lbl_pmcycle_pm, lbl_pmtype_pm, 
	
	private Stage stage;	
	_query qr = new _query();
	s_class sclass = new s_class();
	pm_controller pc = new pm_controller();
	Tooltip tip;
		
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		sclass._style(confirm_pm_upd);
		sclass._style(cancel_pm_upd);
		
		confirm_pm_upd.setDisable(true);
		//инициализируем данные комбобоксов
				ninst_pm_upd.setItems(qr._select_instr_pm());
				
				//Устанавливаем фокус и делаем фильтр по последней введенной инструкции
				ninst_pm_upd.requestFocus();
				
				FilteredList<String> filteredItems = new FilteredList<String>(qr._select_instr_pm(), p -> true);
				
				ninst_pm_upd.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
		            final TextField editor = ninst_pm_upd.getEditor();
		            final String selected = ninst_pm_upd.getSelectionModel().getSelectedItem();
		            
		            editor.requestFocus();
		                      
		            // This needs run on the GUI thread to avoid the error described
		            // here: https://bugs.openjdk.java.net/browse/JDK-8081700.
		            Platform.runLater(() -> {
		                // If the no item in the list is selected or the selected item
		                // isn't equal to the current input, we refilter the list.
		                if (selected == null || !selected.equals(editor.getText())) {
		                    filteredItems.setPredicate(item -> {
		                        // We return true for any items that starts with the
		                        // same letters as the input. We use toUpperCase to
		                        // avoid case sensitivity.
		                        if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
		                            return true;
		                        } else {
		                            return false;
		                        }
		                    });
		                }
		            });
		        });
				
				ninst_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(ninst_pm_upd.getValue());
						Point2D p = ninst_pm_upd.localToScreen(ninst_pm_upd.getLayoutBounds().getMaxX(), ninst_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(ninst_pm_upd, p.getX(), p.getY());
					}
				});
				ninst_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				
				//Заполняем ComboBox Группа в зависимотси от переодичности, т.е в этот ComboBox попадут только те группы которые соответствуют выбранному периоду ППР, а период
				//мы узнаем по номеру инструкции из таблицы pm_inst
				ninst_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						group_eq_upd.setItems(qr._select_pm_group(sclass.parser_str(ninst_pm_upd.getValue(), 0)));				
					}
				});
				list_otv_isp_upd.setItems(qr._select_fio_for_ap(2, apwr_controller.SHOP_NAME));
				shop_pm_upd.setItems(qr._select_shop_pm());
				try {
					shop_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
							groupeq_pm_upd.valueProperty().set(null);
							lm_pm_upd.valueProperty().set(null);
							os_pm_upd.valueProperty().set(null);
							equip_pm_upd.valueProperty().set(null);
							if(shop_pm_upd.getValue().toString().length() != 0)
								groupeq_pm_upd.setItems(qr._select_group_pm(sclass.parser_str(shop_pm_upd.getValue(), 0)));
							chk_btn();
						}
					});
				} catch (Exception e) {
					
				}
				
				groupeq_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						lm_pm_upd.valueProperty().set(null);
						os_pm_upd.valueProperty().set(null);
						equip_pm_upd.valueProperty().set(null);
						try {
							if(groupeq_pm_upd.getValue().toString().length() != 0)
								lm_pm_upd.setItems(qr._select_lm_pm(sclass.parser_str(groupeq_pm_upd.getValue(), 0)));
							chk_btn();
							//if(lm_wr_add.getValue().toString().length() != 0)
							//	os_wr_add.setItems(qr._select_os_pm(sclass.parser_str(shop_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
							} catch (Exception e) {
								
							}
						
					}
				});
				groupeq_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(groupeq_pm_upd.getValue());
						Point2D p = groupeq_pm_upd.localToScreen(groupeq_pm_upd.getLayoutBounds().getMaxX(), groupeq_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(groupeq_pm_upd, p.getX(), p.getY());
					}
				});
				groupeq_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				
				lm_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							if(lm_pm_upd.getValue().toString().length() != 0)
								os_pm_upd.setItems(qr._select_os_pm(sclass.parser_str(groupeq_pm_upd.getValue(), 0), sclass.parser_str(lm_pm_upd.getValue(), 0)));
							chk_btn();
							} catch (Exception e) {
								
							}
					}
				});
				lm_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(lm_pm_upd.getValue());
						Point2D p = lm_pm_upd.localToScreen(lm_pm_upd.getLayoutBounds().getMaxX(), lm_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(lm_pm_upd, p.getX(), p.getY());
					}
				});
				lm_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
					
				
				os_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							if(os_pm_upd.getValue().toString().length() != 0)
								equip_pm_upd.setItems(qr._select_equip_pm(sclass.parser_str(os_pm_upd.getValue(), 0), sclass.parser_str(groupeq_pm_upd.getValue(), 0), sclass.parser_str(lm_pm_upd.getValue(), 0)));
							chk_btn();
							} catch (Exception e) {
							}
					}
				});
				os_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(os_pm_upd.getValue());
						Point2D p = os_pm_upd.localToScreen(os_pm_upd.getLayoutBounds().getMaxX(), os_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(os_pm_upd, p.getX(), p.getY());
					}
				});
				os_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				
				equip_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
							otv_pm_upd.setItems(qr._select_fio_for_ap(1, sclass.parser_str(shop_pm_upd.getValue(), 0)));
							chk_btn();
						}
					});
				equip_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(equip_pm_upd.getValue());
						Point2D p = equip_pm_upd.localToScreen(equip_pm_upd.getLayoutBounds().getMaxX(), equip_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(equip_pm_upd, p.getX(), p.getY());
					}
				});
				equip_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});
				/*ninst_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
						if(ninst_pm_upd.getValue().toString().length() != 0)
							mtt_pm_upd.setItems(qr._select_mtt_pm(ninst_pm_upd.getValue()));
						} catch (Exception e) {
							
						}
					}
				});
				mtt_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip = new Tooltip(mtt_pm_upd.getValue());
						Point2D p = mtt_pm_upd.localToScreen(mtt_pm_upd.getLayoutBounds().getMaxX(), mtt_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(mtt_pm_upd, p.getX(), p.getY());
					}
				});
				mtt_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						tip.hide();
					}
				});*/
				
				String line1 = new String("ON");
				String line2 = new String("OFF");
				otv_pm_upd.setItems(qr._select_fio_for_ap(1, apwr_controller.SHOP_NAME));
				ool_pm_upd.getItems().addAll(line1, line2);
				
				ninst_pm_upd.getSelectionModel().select(pc._ninst_pm_upd);
				//shop_pm_upd.getSelectionModel().select(pc._eq_id_upd);
				//group_pm_upd.getSelectionModel().select(pc._group_pm_upd);
				ool_pm_upd.getSelectionModel().select(pc._ool_pm_upd);
				otv_pm_upd.getSelectionModel().select(pc._otv);
				num_pm_upd.setText(pc._id_pm);
				//equip_pm_upd.setText(qr._select_fillpm_equip(pc._eq_id_upd, "hmmr_pm"));
				group_eq_upd.getSelectionModel().select(pc._group_pm_upd);
				list_otv_isp_upd.getSelectionModel().select(pc._pm_exec);
				
				ninst_pm_upd.setItems(filteredItems);
				ninst_pm_upd.getEditor().requestFocus();
				Platform.runLater(new Runnable() {
	                @Override
	                public void run() {
	                	ninst_pm_upd.getEditor().positionCaret(ninst_pm_upd.getEditor().getText().length());
	                }
	           });
				shop_pm_upd.getSelectionModel().select(pc._shop_pm);
				groupeq_pm_upd.getSelectionModel().select(pc._groupeq_pm);
				lm_pm_upd.getSelectionModel().select(pc._lm_pm);
				os_pm_upd.getSelectionModel().select(pc._os_pm);
				equip_pm_upd.getSelectionModel().select(pc._equip_pm);
				
				/*pmname_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				});
				pmcycle_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				});
				pmtype_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						chk_btn();
					}
				});*/
				ool_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						chk_btn();
					}
				});
				otv_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						chk_btn();
					}
				});
				group_eq_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						chk_btn();
					}
				});
				shop_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						chk_btn();
					}
				});
				group_eq_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						chk_btn();
					}
				});
				lm_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						chk_btn();
					}
				});
				os_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						chk_btn();
					}
				});
				equip_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						chk_btn();
					}
				});
/*				dexp_pm_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						chk_btn();
					}
				});*/
				
				cancel_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						stage = (Stage) cancel_pm_upd.getScene().getWindow();
						stage.close();
					}
				});
				confirm_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						String eq_id_total = qr._select_data_filter_ps_id(sclass.parser_str(shop_pm_upd.getValue(), 0), sclass.parser_str(groupeq_pm_upd.getValue(), 0), sclass.parser_str(lm_pm_upd.getValue(), 0), sclass.parser_str(os_pm_upd.getValue(), 0), sclass.parser_str(equip_pm_upd.getValue(), 0));
						qr._update_rec_pm(pc._id_pm, sclass.parser_str(ninst_pm_upd.getValue(), 0), eq_id_total, group_eq_upd.getValue(), sclass.parser_str(otv_pm_upd.getValue(), 0), sclass.parser_str(ool_pm_upd.getValue(), 0), sclass.parser_str(list_otv_isp_upd.getValue(), 0));
							
						qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновиле запись № = " + pc._id_pm + " в таблице PM");
							
						pc._table_update_pm.addAll(qr._select_data_pm2());
							
						stage = (Stage) confirm_pm_upd.getScene().getWindow();
						stage.close();
					}
				});
				group_eq_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						chk_btn();
					}
				});
				otv_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						chk_btn();
					}
				});
				confirm_pm_upd.setDisable(true);
	}
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		lbl_ninst_pm.setText(lngBndl.getString("col_ninst_inst")+":");
		//lbl_shop_pm.setText(lngBndl.getString("lbl_shop_ap"));
		//lbl_group_pm.setText(lngBndl.getString("lbl_group_ap"));
		//lbl_lm_pm.setText(lngBndl.getString("lbl_lm_ap"));
		//lbl_os_pm.setText(lngBndl.getString("lbl_os_ap"));
		//lbl_equip_pm.setText(lngBndl.getString("lbl_equip_ap"));
		//lbl_pmname_pm.setText(lngBndl.getString("col_group_eq")+":");
		//lbl_pmcycle_pm.setText(lngBndl.getString("col_pmc_pm")+":");
		//lbl_pmtype_pm.setText(lngBndl.getString("col_pmtype_pm")+":");
		lbl_ool_pm.setText(lngBndl.getString("col_ool_pm")+":");
		lbl_otv_pm.setText(lngBndl.getString("lbl_otv_ap")+":");
//		lbl_dexp_pm.setText(lngBndl.getString("lbl_dexp_pm")+":");
		lbl_group_eq.setText(lngBndl.getString("col_group_eq")+":");
		lbl_head_pm.setText(lngBndl.getString("lbl_head_pm_upd"));
		lbl_otv_isp.setText(lngBndl.getString("lbl_otv_ap"));
		lbl_otv_isp1.setText(lngBndl.getString("lbl_otv_ap1"));
		confirm_pm_upd.setText(lngBndl.getString("lbl_apply"));
		cancel_pm_upd.setText(lngBndl.getString("cancel_tsk"));
	}
	private void chk_btn()
	{
		try {
			if(ninst_pm_upd.getValue().length() != 0 && group_eq_upd.getValue().length() != 0 &&  
					ool_pm_upd.getValue().length() != 0 && otv_pm_upd.getValue().length() != 0 && shop_pm_upd.getValue().length() != 0 && group_eq_upd.getValue().length() != 0 &&
					lm_pm_upd.getValue().length() != 0 && os_pm_upd.getValue().length() != 0 && equip_pm_upd.getValue().length() != 0)
				confirm_pm_upd.setDisable(false);
			else
				confirm_pm_upd.setDisable(true);
			}
			catch (Exception e) {
				
			}
	}
}
