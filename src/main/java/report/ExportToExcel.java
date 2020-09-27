package report;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import ru.haval.application.Main;
import ru.haval.application.conn_connector;
import ru.haval.db._connect;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import ru.haval.share_class.s_class;


public class ExportToExcel extends JFrame {
	 private static final long serialVersionUID = 1L;
	 _connect cn = new _connect();
	 s_class scl = new s_class();
	 private String location;
	 Main mn = new Main();
		  
		@SuppressWarnings("unchecked")
		public void showReport(String str) throws JRException, ClassNotFoundException, SQLException {
	 
			location = "\\\\10.168.150.74\\MU\\Blank_Letter_xls.jrxml";
	        String reportSrcFile = location;
	        
	        // First, compile jrxml file.
	        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
	        jasperReport.setProperty(JRTextElement.PROPERTY_PRINT_KEEP_FULL_TEXT, "true");
	        
	        @SuppressWarnings("rawtypes")
			Map map = new HashMap();
            map.put("Id", str);
            map.put("Tsk", "AP"+str);
	      	        
	        JasperPrint print = JasperFillManager.fillReport(jasperReport, map, cn.ConToDb1());
	        
	        File outDir = new File("\\\\10.168.150.74\\MU\\jasperoutput");
	        outDir.mkdirs();
	  
	        JRXlsExporter exporter = new JRXlsExporter();
	        exporter.setExporterInput(new SimpleExporterInput(print));
	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("\\\\10.168.150.74\\MU\\jasperoutput\\Task_Report"+conn_connector.USER_ID+".xls"));
            
            exporter.exportReport();
        	try {
        		File excelFile = new File("\\\\10.168.150.74\\MU\\jasperoutput\\Task_Report"+conn_connector.USER_ID+".xls");
        		mn._run_excel(excelFile);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
}
