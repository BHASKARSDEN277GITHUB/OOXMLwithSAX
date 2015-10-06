/**
 * XMLReader --- To read and process limit number of records from XML file (using SAXParser)
 * @author Bhaskar Kalia 
 */

package SAXParsers.sax.parsers.tests;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLReader {
	private int limit;
	private String XMLFilePath;
	private InputSource XMLInputSource = null;

	// constructor filepath
	public XMLReader(int limit, String XMLFilePath) {
		this.limit = limit;
		this.XMLFilePath = XMLFilePath;
	}

	// constructor with input source
	public XMLReader(int limit, InputSource XMLInputSource) {
		this.limit = limit;
		this.XMLInputSource = XMLInputSource;
	}

	// Reading and processing the XML based upon limit value
	public void readXML() {
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				private int counter = 0;
				private int rowCount = 0;

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

					int numberOfAttributes = attributes.getLength();
					// increment counter for a particular tag
					if (qName.equals("row")) {
						counter++;
						rowCount++;

						System.out.println("");
						System.out.println("");
						System.out.println("Row Number :" + rowCount);
						System.out.println("");
						System.out.println("");
					}
					// print tagname
					System.out.println("StartElementName  " + qName);

					// print attributes (if exist)
					if (numberOfAttributes > 0) {
						for (int i = 0; i < numberOfAttributes; i++) {
							System.out.println("Attribute " + (i + 1)
									+ "	Name: " + attributes.getQName(i)
									+ "	Value : " + attributes.getValue(i));
						}
					}

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					// print end element tag
					System.out.println("EndElementName  " + qName);

				}

				public void characters(char ch[], int start, int length)
						throws SAXException {
					// check if counter exceeds the specified limit
					if (counter < limit) {

						System.out.println("value  "
								+ new String(ch, start, length));
						System.out.println("");
						System.out.println("");

					} else { // if limit is reached , do whatever processing is
								// to be done
						// reset the counter
						counter = 0;

						// whatever processing after 'limit' number of records
						// have been read
						System.out.println("\n\n\n\n\n Processing Recent"
								+ limit + " rows");
						System.out.println("");
						System.out.println("");
						for (int i = 0; i < 10; i++) {
							System.out.println("processing ...... ");
						}
						System.out.println("");
						System.out.println("");
						System.out.println("");
					}

				}

			};

			if (XMLInputSource == null) {
				saxParser.parse(XMLFilePath, handler);
			} else {
				System.out.println("Reading using inputsource");
				saxParser.parse(XMLInputSource, handler);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
