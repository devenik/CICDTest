package devenik.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		
		//ExtentSparkReporter - Configurations
				String path = System.getProperty("user.dir")+"//reports//index.html";
				ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
				sparkReporter.config().setReportName("Web Automation Reports");
				sparkReporter.config().setDocumentTitle("Test Results");
				
				//ExtentReports - Main Report
				ExtentReports extReports = new ExtentReports();
				extReports.attachReporter(sparkReporter);
				extReports.setSystemInfo("Tester", "Devenik");
				return extReports;
		
	}

}
