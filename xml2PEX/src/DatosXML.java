import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DatosXML {
	
	public DatosXML (File ficheroXML) {
		this.fichero = ficheroXML;		
				
		nombreFichero= fichero.getName();
		lista_Obj_S3e_NOMBRE.add("AG");
		lista_Obj_S3e_NOMBRE.add("CV");
		lista_Obj_S3e_NOMBRE.add("MO");
		lista_Obj_S3e_NOMBRE.add("SE");
		this.num_Obj_S3e = lista_Obj_S3e_NOMBRE.size();	
		
		
		for (int i=0; i<num_Obj_S3e; i++) 
			this.lista_Obj_S3e_CANTIDAD.add(0);

		
		// listaNombreObjetosRepetidosEnXML(0) 		= ("MCS_001", "MCS_001") en caso de que dos MCS tengan el mismo nombre "MCS_001"
		// listaNombreObjetosRepetidosEnXML(1) 		= ("EDVA1", "EDVA1")
		// listaNombreObjetosRepetidosEnXML(2) 		= vacio		
		// ...
		// listaNombreObjetosRepetidosEnXML(numObjS3e-1) = etc
		
		for (int i=0; i<num_Obj_S3e; i++) {
			this.obj_XML_NOMBRE.add(new ArrayList<String>());
			this.obj_XML_Id.add(new ArrayList<String>());	
		}		
		
		parseaXMLS3e();

	}

	
	public void parseaXMLS3e () {
		try {
			SAXParserFactory factory2 = SAXParserFactory.newInstance();
			SAXParser saxParser2 = factory2.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					String etiqueta = qName;
					// buscamos el startElement en nuestra lista de objetos a evaluar
					for (int startE=0; startE<num_Obj_S3e; startE ++) {
						
						boolean elementoSimple_analizado = false;
						//para recoger los de tipo "ED", "SD", "MO", ....
						if (etiqueta.equalsIgnoreCase(lista_Obj_S3e_NOMBRE.get(startE))) {						
							obj_XML_NOMBRE.get(startE).add(attributes.getValue("Nombre"));
							obj_XML_Id.get(startE).add(attributes.getValue("Identificador"));		
							elementoSimple_analizado = true;
						}
						
						if (true) {
							List<String> xxxx = new ArrayList<String>();
							xxxx		= utilidades.analizaObjArgs(lista_Obj_S3e_NOMBRE.get(startE));
							int nro_argumentos = (xxxx.size()-1)/3;
							
							xxxx.get(0);
							boolean cumpleCriterio = true;
							
							if (etiqueta.equalsIgnoreCase(xxxx.get(0))&&xxxx.size()>1) {
								// recorremos bucle para ver que se cumplen todos los pares de arg/val (teniendo en cuent al operador )
								// si todos los pares se cumplen, cumpleCriterio = true
								for (int j=0;j<nro_argumentos;j++) {
									String argumento_i = xxxx.get(3*j+1);
									String operador_i = xxxx.get(3*j+2);
									String valor_i = xxxx.get(3*j+3);
									
									String argumento_xml=attributes.getValue(argumento_i);
									
									switch (operador_i) {
									case "=":
										if (!valor_i.equalsIgnoreCase(argumento_xml)) {
											cumpleCriterio = false;
										}
										break;
									case "!":
										if (valor_i.equalsIgnoreCase(argumento_xml)) {
											cumpleCriterio = false;
										}
										break;
									}
								}
								
								if (cumpleCriterio) {
									obj_XML_NOMBRE.get(startE).add(attributes.getValue("Nombre"));
									obj_XML_Id.get(startE).add(attributes.getValue("Identificador"));
									//System.out.println(xxxx+ ": "+attributes.getValue("Nombre")+", " );
								}
							}
						}											
					}
				}				
			};
			saxParser2.parse(fichero, handler);
			
		} catch (Exception e) {
			System.out.println("main: " + e);
			e.printStackTrace();
		}
	}
	
	// variables del objeto
	private String nombreFichero;
	private String pathScriptsAutomaticos;
	private int num_Obj_S3e;		
	private List<String> lista_Obj_S3e_NOMBRE = new ArrayList<String>();
	private List<Integer> lista_Obj_S3e_CANTIDAD = new ArrayList<Integer>();
	
	private List<List<String>> obj_XML_NOMBRE = new ArrayList<List<String>>();
	private List<List<String>> obj_XML_Id = new ArrayList<List<String>>();		

	private File fichero = new File("");
	private File ficheroSicamPC = new File("");
	private File fileScriptsAutomaticos;

	
}
