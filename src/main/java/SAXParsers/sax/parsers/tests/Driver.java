/**
 * Driver --- SAX Parser Tests (contains main method to test other classes)
 * @author Bhaskar Kalia 
 */

package SAXParsers.sax.parsers.tests;

public class Driver {

	public static void main(String argv[]) {
		//create instance of XMLReader class
		XMLReader reader = new XMLReader(2,"/home/bhaskar/Documents/work/Apache-POI/SAX-Parser-Tests/sheet1.xml");
		reader.readXML();
	}

}
