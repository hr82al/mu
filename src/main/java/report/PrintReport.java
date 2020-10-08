package report;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import ru.haval.application.Main;
import ru.haval.config.Config;
import ru.haval.db._connect;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import ru.haval.share_class.s_class;


public class PrintReport extends JFrame {
	 private static final long serialVersionUID = 1L;
	 _connect cn = new _connect();
	 s_class scl = new s_class();
	 private String location;
	 Main mn = new Main();
		  
		@SuppressWarnings({ "unchecked", "static-access" })
		public void showReport(String str) throws JRException, ClassNotFoundException, SQLException {
	 
	    	//loc = System.getProperty("user.dir");
			//location = loc + "/Blank_Letter.jrxml";
//			location = "m:/08.USER/U.14.RG/Blank_Letter_xls.jrxml";
//			location = "C:\\Report\\Blank_Letter_xls.jrxml";
//			location = "\\\\hmmr-ds415\\hmmr_share\\Maintenance_and_Utility_Department\\08.USER\\U.14.RG\\Blank_Letter_xls.jrxml";			
			location = "\\\\" +
					Config.getInstance().getAddress() +
					"\\MU\\Blank_Letter_xls.jrxml";
	        String reportSrcFile = location;//"C:\\Work\\Blank_Letter.jrxml";
	        
	        // First, compile jrxml file.
	        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
	        jasperReport.setProperty(JRTextElement.PROPERTY_PRINT_KEEP_FULL_TEXT, "true");
	        // Fields for report
	        //HashMap<String, Object> parameters = new HashMap<String, Object>();
	 
	        //parameters.put("company", "MAROTHIA TECHS");
	        //parameters.put("receipt_no", "RE101".toString());
	        //parameters.put("name", "Khushboo");
	        //parameters.put("amount", "10000");
	        //parameters.put("receipt_for", "EMI Payment");
	        //parameters.put("date", "20-12-2016");
	        //parameters.put("contact", "98763178".toString());
	        @SuppressWarnings("rawtypes")
			Map map = new HashMap();
            map.put("Id", str);
            map.put("Tsk", "AP"+str);
	      	        
	        JasperPrint print = JasperFillManager.fillReport(jasperReport, map, cn.ConToDb1());
	        try {
	        	JasperPrintManager.printReport(print, false);
	        	scl._AlertDialog("Печать успешно завершена!", "Печать");
	        }
	        catch (Exception e) {
				scl._AlertDialog(e.getMessage(), "Ошибка печати!");
			}
	        // Make sure the output directory exists.
//	        File outDir = new File("m:/08.USER/U.14.RG/jasperoutput");
//	        File outDir = new File("C:\\Report\\jasperoutput");
//	        outDir.mkdirs();
	  
//	        JRXlsExporter exporter = new JRXlsExporter();
//	        exporter.setExporterInput(new SimpleExporterInput(print));
//	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("m:/08.USER/U.14.RG/jasperoutput/Task_Report"+conn_connector.USER_ID+".xls"));
//export excel	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C:\\Report\\jasperoutput\\Task_Report"+conn_connector.USER_ID+".xls"));
            
//export excel            exporter.exportReport();
            
//            Runtime runtime = Runtime.getRuntime();
//export excel        	try {
//				runtime.exec("excel " + "m:/08.USER/U.14.RG/jasperoutput/Task_Report"+conn_connector.USER_ID+".xls");
//				runtime.exec("excel " + "C:\\Report\\jasperoutput\\Task_Report"+conn_connector.USER_ID+".xls");
//export excel        		File excelFile = new File("C:\\Report\\jasperoutput\\Task_Report"+conn_connector.USER_ID+".xls");
//export excel        		mn._run_excel(excelFile);
				
//export excel			} catch (IOException e) {
				
//export excel				e.printStackTrace();
//export excel			}
        	//btn.setDisable(true);
			
	        // Export to PDF.
//	        JasperExportManager.exportReportToHtmlFile(print,
//	                "m:/08.USER/U.14.RG/jasperoutput/StyledTextReport"+conn_connector.USER_ID+".html");
//	        if (Desktop.isDesktopSupported()) {
//	            try {
//					Desktop.getDesktop().browse(new URI("m:/08.USER/U.14.RG/jasperoutput/StyledTextReport"+conn_connector.USER_ID+".html"));
//				} catch (IOException | URISyntaxException e) {
//					
//					e.printStackTrace();
//				}
//	        }
	        //JRViewer viewer = new JRViewer(print);
	        //viewer.setOpaque(true);
	        //viewer.setVisible(true);
	        //this.add(viewer);
	        ///this.setSize(700, 500);
	        //this.setVisible(true);
//	        System.out.print("Done!");
	 
	    }
}
