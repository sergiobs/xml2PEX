
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;





public class main {
	
	public static void main(String argv[])   {		
		String texto="";		
		Scanner entradaEscaner = new Scanner (System.in);		
		String acciones ="";		
		int nivelTraza = 2;
		
		// indicamos la ruta donde vamos a buscar los xml o bien la recogemos de argumento 1
		String rutaBase = "C:\\temp";	
		String nombreFichero = "ROUTE.xml";
		
		if (argv.length> 0) {
			rutaBase = argv[0];
		}
		File ruta = new File(rutaBase+"\\"+nombreFichero);

		if (argv.length> 1) {
			acciones = argv[1];
		}		
			
		File ficheroSalida = new File(rutaBase + "\\analisisXML2PEX"+".txt");
		MainFunciones.tratar_XML(nivelTraza, ficheroSalida, ruta);
		//MainFunciones.creaExcel(); 
    }		
		
	
}
