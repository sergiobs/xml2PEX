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



public class DatosFicheroXML {
	
	// getters
	public String getNombreFichero() {
		return nombreFichero;
	}
	public String getPathScriptsAutomaticos() {
		return pathScriptsAutomaticos;
	}
	public File getFichero() {
		return fichero;
	}
	public File getFileScriptsAutomaticos() {
		return fileScriptsAutomaticos;
	}
	public List<Integer> getListaNumeroObjetosS3e() {
		return lista_Obj_S3e_CANTIDAD;
	}	

	public List<List<String>> getListaIdentificadorObjetosEnXML() {
		return obj_XML_Id;
	}
	public List<List<String>> getListaNombreObjetosEnXML() {
		return obj_XML_NOMBRE;
	}
	public List<String> getListaTipoObj_S3e() {
		return lista_tipoObj_S3e;
	}
	
	public Integer getNumeroObjetosS3e() {
		return num_Obj_S3e;
	}

	public void add_obj_XML_NOMBRE(int posicion, String valor) {
		obj_XML_NOMBRE.get(posicion).add(valor);
	}
	public void add_obj_XML_ID(int posicion, String valor) {
		obj_XML_Id.get(posicion).add(valor);
	}	

	
	// constructor
		public DatosFicheroXML (File ficheroXML) {			
	
			objMO_valorAtributos.add(new ArrayList<String>());
			objMO_listaAtributos.add("Identificador");
			objMO_listaAtributos.add("Nombre");
			objMO_listaAtributos.add("IdentSCR");
			objMO_listaAtributos.add("TipoMovimiento");
			objMO_listaAtributos.add("SubtipoMovimiento");
			objMO_listaAtributos.add("TipoDisolucion");
			objMO_listaAtributos.add("SEIntermedia");
			objMO_listaAtributos.add("nSEIntermedia");
			objMO_listaAtributos.add("nDeslizamiento");
			
			for (int i=0; i<objMO_listaAtributos.size(); i++) {
				this.objMO_valorAtributos.add(new ArrayList<String>());		
			}				
			
			objSCR_valorAtributos.add(new ArrayList<String>());
			objSCR_listaAtributos.add("Identificador");
			objSCR_listaAtributos.add("Nombre");
			for (int i=0; i<objSCR_listaAtributos.size(); i++) {
				this.objSCR_valorAtributos.add(new ArrayList<String>());		
			}

			objCV_valorAtributos.add(new ArrayList<String>());
			objCV_listaAtributos.add("Identificador");
			objCV_listaAtributos.add("Nombre");
			for (int i=0; i<objCV_listaAtributos.size(); i++) {
				this.objCV_valorAtributos.add(new ArrayList<String>());		
			}

			
			objDZ_valorAtributos.add(new ArrayList<String>());
			objDZ_listaAtributos.add("Identificador");
			objDZ_listaAtributos.add("Nombre");
			for (int i=0; i<objDZ_listaAtributos.size(); i++) {
				this.objDZ_valorAtributos.add(new ArrayList<String>());		
			}
			
			this.num_Obj_S3e = lista_tipoObj_S3e.size();	
			this.fichero = ficheroXML;
					
			nombreFichero= fichero.getName();
					
			// se crea una lista de numObjS3e elementos que contendra el numero de objetos de cada 
			for (int i=0; i<num_Obj_S3e; i++) 
				this.lista_Obj_S3e_CANTIDAD.add(0);

			for (int i=0; i<num_Obj_S3e; i++) {
				this.obj_XML_NOMBRE.add(new ArrayList<String>());
				this.obj_XML_Id.add(new ArrayList<String>());		
			}	
		}
	
	// setters
	public void setFichero(File fichero) {
		this.fichero = fichero;
	}
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
	public void addNombreObjetoEnXML(String nombreObjetoEnXML, int indice) {
		this.obj_XML_NOMBRE.get(indice).add(nombreObjetoEnXML);
	}	
	
	public void addIdentificadorObjetoEnXML(String identificadorObjetoEnXML, int indice) {
		this.obj_XML_Id.get(indice).add(identificadorObjetoEnXML);
	}			

	public void cuentaObjetosS3e () {
		for (int i=0; i<lista_Obj_S3e_CANTIDAD.size(); i++) 
		{
			this.lista_Obj_S3e_CANTIDAD.set(i,obj_XML_NOMBRE.get(i).size());
		}
	}
		
	public String imprimelistaNumeroObjetosS3e () {
		String texto2print ="";
		for (int aa=0; aa<num_Obj_S3e;aa++) {						
			texto2print+=lista_tipoObj_S3e.get(aa) + ": " + lista_Obj_S3e_CANTIDAD.get(aa) + "\n";
		}
		return texto2print;
	}	

	
	public int devuelveIndice_nombreAtr(List<String> atributos, String nombreAtr) {

		for (int i = 0; i<atributos.size(); i++) {
			if (atributos.get(i).equals(nombreAtr)) return i;			
		}		
		return -1;
	}
	
	
	
	public int devuelveIndice_tipoObjS3e(String nombre_tipoObjS3e) {
		// te devuelve el indice de "MO", "SE", etc
		for (int i = 0; i<num_Obj_S3e; i++) {
			if (lista_tipoObj_S3e.get(i).equals(nombre_tipoObjS3e)) return i;			
		}		
		return -1;
	}
	
	public int devuelveIndice_ObjS3e(List<List<String>> matrizValores, String nombre_ObjetoS3e) {
		// te devuelve el indice de "I E1 S11", etc del tipo "MO"
		//int ind_tipo = devuelveIndice_tipoObjS3e(nombre_tipoObjS3e);
		for (int i = 0; i<matrizValores.get(0).size(); i++) {
			if (matrizValores.get(0).get(i).equals(nombre_ObjetoS3e)) 
				return i;			
		}	
		return -1;
	
	}
	
	// variables del objeto
	private String nombreFichero;
	private String pathScriptsAutomaticos;
	int num_Obj_S3e;		
	private List<String> lista_tipoObj_S3e = new ArrayList<String>();
	private List<Integer> lista_Obj_S3e_CANTIDAD = new ArrayList<Integer>();	
	private List<List<String>> obj_XML_NOMBRE = new ArrayList<List<String>>();
	private List<List<String>> obj_XML_Id = new ArrayList<List<String>>();	
	
	List<List<String>> objMO_valorAtributos = new ArrayList<List<String>>();	
	List<String> objMO_listaAtributos = new ArrayList<String>();
	List<List<String>> objSCR_valorAtributos = new ArrayList<List<String>>();	
	List<String> objSCR_listaAtributos = new ArrayList<String>();
	List<List<String>> objCV_valorAtributos = new ArrayList<List<String>>();	
	List<String> objCV_listaAtributos = new ArrayList<String>();
	List<List<String>> objDZ_valorAtributos = new ArrayList<List<String>>();	
	List<String> objDZ_listaAtributos = new ArrayList<String>();
	
	private File fichero = new File("");
	private File fileScriptsAutomaticos;	
}
