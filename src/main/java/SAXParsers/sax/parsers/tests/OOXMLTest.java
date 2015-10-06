/**
 * OOXMLTest --- Reading parts of OPCPackage (/xl/worksheets/sheet*)
 * @author Bhaskar Kalia 
 */

package SAXParsers.sax.parsers.tests;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.xml.sax.InputSource;

public class OOXMLTest {
	public static void main(String[] args) {
		try {
			System.out.println("Test Start");
			// OPC : open packaging conventions
			OPCPackage pkg=OPCPackage.open("/home/bhaskar/Documents/work/Apache-POI/SAX-Parser-Tests/sample.xlsx");
			//can't open xls 
			ArrayList<PackagePart> partList = pkg.getParts();
			for(PackagePart p : partList){
				
				if(p.getPartName().toString().matches("/xl/worksheets/sheet[0-9]+.xml")){ //match part name using regex
					System.out.println("Name : "+p.getPartName() + "	ContentType "+p.getContentType());
					try {
						/**
						 * Start Parsing using XMLReader 
						 */
						InputSource iSource = new InputSource(p.getInputStream());
						//create instance of XMLReader class
						XMLReader reader = new XMLReader(2,iSource);
						reader.readXML();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			/*System.out.println(pkg.getParts());*/
			
			/*ContentType type = new ContentType("application/vnd.openxmlformats-package.relationships+xml");*/
			/*System.out.println(pkg.getPartsByContentType("application/vnd.openxmlformats-package.relationships+xml"));*/
			
			//target : get packagepart reference
			
			/*PackageRelationship coreRel = pkg.getRelationshipsByType(PackageRelationshipTypes.CORE_DOCUMENT).getRelationship(0);	
			PackagePart packagePart= pkg.getPart(coreRel);
			System.out.println(packagePart.getPartName());*/
			
			System.out.println("Test End");
			pkg.close();
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  /*XSSFWorkbook wb=new XSSFWorkbook(pkg);
		  for (  XSSFMap map : wb.getCustomXMLMappings()) {
		    XSSFExportToXml exporter=new XSSFExportToXml(map);
		    ByteArrayOutputStream os=new ByteArrayOutputStream();
		    exporter.exportToXML(os,true);
		    String xml=os.toString("UTF-8");
		    System.out.println(xml);
		  }*/ catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
