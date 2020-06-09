import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;



import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MainFunciones {

  
	 static void tratar_XML(int nivelTraza, File ficheroSalida, File ficheroXML){

			String texto = "Entramos en tratarXML";
			escribeResultados.escribe(texto+"\n", ficheroSalida, nivelTraza);
			DatosFicheroXML datosXML = new DatosFicheroXML(ficheroXML); 
			String nombreFichero = ficheroXML.getName();


			DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder2;
			try {
				builder2 = factory2.newDocumentBuilder();
				Document documento = builder2.parse(ficheroXML);
				NodeList listaMO = documento.getElementsByTagName("MO");
				NodeList listaSCR = documento.getElementsByTagName("SCR");
				NodeList listaCV = documento.getElementsByTagName("CV");
				NodeList listaAG = documento.getElementsByTagName("AG");
				NodeList listaSE = documento.getElementsByTagName("SE");
				NodeList listaDZ = documento.getElementsByTagName("DZ");
				
	            for (int i = 0; i < listaMO.getLength(); i++) {
	                // Cojo el nodo actual
	                Node nodoMO = listaMO.item(i);
	                // Compruebo si el nodo es un elemento
	                if (nodoMO.getNodeType() == Node.ELEMENT_NODE) {

	                    Element elemMO = (Element) nodoMO;	                    
						for (int atributo_i=0;atributo_i<datosXML.objMO_listaAtributos.size();atributo_i++) {
							String atrName = datosXML.objMO_listaAtributos.get(atributo_i);
							System.out.print(elemMO.getAttribute(atrName)+", ");							
							datosXML.objMO_valorAtributos.get(atributo_i).add(elemMO.getAttribute(atrName));										
						}
						System.out.println("");  

	                    NodeList hijosMO = elemMO.getChildNodes();
	                    for (int j = 0; j < hijosMO.getLength(); j++) {
	                        Node hijoMO = hijosMO.item(j);
	                        

	                        
	                        //Lista_DZ_MO
	                        if ((hijoMO.getNodeType() == Node.ELEMENT_NODE)&&(hijoMO.getNodeName()=="Lista_DZ_MO")) {

	                        	Element elemHijoMO = (Element) hijoMO;
	                            System.out.print(hijoMO.getNodeName()+": ");
	                            
	                            NodeList hijos3 = elemHijoMO.getChildNodes();
	                            int nDeslizamiento = 0;
	                            for (int j2 = 0; j2 < hijos3.getLength(); j2++) {
	                            	Node hijo3 = hijos3.item(j2);
	                            	if (hijo3.getNodeType() == Node.ELEMENT_NODE) {
		                            	Element elemHijoMO3 = (Element) hijo3;	
		                            	System.out.print(elemHijoMO3.getAttribute("IdentDeslizamiento")+", ");
		                            	nDeslizamiento++;
	                            	}
	                            }
	                            
	                            int indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, "nDeslizamiento");
	                            datosXML.objMO_valorAtributos.get(indice_atr).set(i, nDeslizamiento+"");

	                        }	
	                        
	                        //Lista_SEIntermedia
	                        if ((hijoMO.getNodeType() == Node.ELEMENT_NODE)&&(hijoMO.getNodeName()=="Lista_SEIntermedia")) {

	                        	Element elemHijoMO = (Element) hijoMO;
	                            System.out.print(hijoMO.getNodeName()+": ");
	                            
	                            NodeList hijos3 = elemHijoMO.getChildNodes();
	                            int nSEIntermedia = 0;
	                            for (int j2 = 0; j2 < hijos3.getLength(); j2++) {
	                            	Node hijo3 = hijos3.item(j2);
	                            	if (hijo3.getNodeType() == Node.ELEMENT_NODE) {
		                            	Element elemHijoMO3 = (Element) hijo3;	
		                            	System.out.print(elemHijoMO3.getAttribute("SEIntermedia_Ident")+", ");
		                            	nSEIntermedia++;
	                            	}
	                            }
	                            
	                            int indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, "nSEIntermedia");
	                            datosXML.objMO_valorAtributos.get(indice_atr).set(i, nSEIntermedia+"");

	                        }	 
	                    }
	                    System.out.println("");
	                }	 
	            }				

	            for (int i = 0; i < listaSCR.getLength(); i++) {
	                // Cojo el nodo actual
	                Node nodoSCR = listaSCR.item(i);
	                // Compruebo si el nodo es un elemento
	                if (nodoSCR.getNodeType() == Node.ELEMENT_NODE) {

	                    Element elemSCR = (Element) nodoSCR;	                    
						for (int atributo_i=0;atributo_i<datosXML.objSCR_listaAtributos.size();atributo_i++) {
							String atrName = datosXML.objSCR_listaAtributos.get(atributo_i);
							//String atrValue = attributes.getValue(atrName);
							//System.out.print(elemSCR.getAttribute(atrName)+", ");							
							datosXML.objSCR_valorAtributos.get(atributo_i).add(elemSCR.getAttribute(atrName));										
						}
						//System.out.println("");
	                }	 
	            }	

	            for (int i = 0; i < listaCV.getLength(); i++) {
	                // Cojo el nodo actual
	                Node nodoCV = listaCV.item(i);
	                // Compruebo si el nodo es un elemento
	                if (nodoCV.getNodeType() == Node.ELEMENT_NODE) {

	                    Element elemCV = (Element) nodoCV;	                    
						for (int atributo_i=0;atributo_i<datosXML.objCV_listaAtributos.size();atributo_i++) {
							String atrName = datosXML.objCV_listaAtributos.get(atributo_i);
							//String atrValue = attributes.getValue(atrName);
							//System.out.print(elemCV.getAttribute(atrName)+", ");							
							datosXML.objCV_valorAtributos.get(atributo_i).add(elemCV.getAttribute(atrName));										
						}
						//System.out.println("");
	                }	 
	            }
				
				
			} catch (ParserConfigurationException | SAXException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			creaExcel(datosXML);				
	 }
	 
	 static void creaExcel(DatosFicheroXML datosXML){
		 String fileName = datosXML.getNombreFichero();
		 String filePath = "C:\\Temp\\" + fileName+".xlsx";
		 //Seteando el nombre de la hoja donde agregaremos los items
		 String hoja_MO_name = "MO"; 
		 String hoja_CV_name = "CV"; 
		 String hoja_SCR_name = "SRC"; 
		 String hoja_DZ_name = "DZ"; 
		 
		 int MO_nro = datosXML.objMO_valorAtributos.get(0).size();
		 int CV_nro = datosXML.objCV_valorAtributos.get(0).size();
		 int SCR_nro = datosXML.objSCR_valorAtributos.get(0).size();
		 int DZ_nro = datosXML.objDZ_valorAtributos.get(0).size();
		 
		 
		 //Creando objeto libro de Excel
		 XSSFWorkbook book = new XSSFWorkbook();

		 XSSFSheet hoja_MO = book.createSheet(hoja_MO_name);
		 XSSFSheet hoja_CV = book.createSheet(hoja_CV_name);
		 XSSFSheet hoja_SCR = book.createSheet(hoja_SCR_name);
		 XSSFSheet hoja_DZ = book.createSheet(hoja_DZ_name);
		 
		 List<String> MO_header = new ArrayList<String>();
		 MO_header.add("Id");
		 MO_header.add("Nombre");
		 MO_header.add("TipoMovimiento");
		 MO_header.add("SubtipoMovimiento");
		 MO_header.add("TipoDisolucion");
		 MO_header.add("SCR");
		 MO_header.add("SCR_N");
		 MO_header.add("nDeslizamiento");
		 MO_header.add("nSEIntermedia");
		 
		 
		 List<String> SCR_header = new ArrayList<String>();
		 SCR_header.add("Id");
		 SCR_header.add("Nombre");
		 SCR_header.add("XXX");
		 SCR_header.add("XX1");
		 
		 List<String> CV_header = new ArrayList<String>();
		 CV_header.add("Id");
		 CV_header.add("Nombre");
		 CV_header.add("XXX");
		 CV_header.add("XX1");
		 
		 List<String> DZ_header = new ArrayList<String>();
		 DZ_header.add("Id");
		 DZ_header.add("Nombre");
		 DZ_header.add("DZ");
		 DZ_header.add("DZ");
			
		 //Aplicando estilo color negrita a los encabezados
		 CellStyle style = book.createCellStyle();
		 Font font = book.createFont();
		 font.setBold(true);	//Seteando fuente negrita al encabezado del archivo excel
		 style.setFont(font);
 

		
			//Hoja MO-------------------
			XSSFRow row_MO = hoja_MO.createRow(0);
			XSSFCell cell_MO = row_MO.createCell(0);
			
			//Rellenamos cabecera
			for (int i=0; i<MO_header.size(); i ++) {
				cell_MO = row_MO.createCell(i);
				cell_MO.setCellStyle(style);
				cell_MO.setCellValue(MO_header.get(i));
			}
	
			
			
			int indice_atr = -1;
			String tipoObj ="";
			String atributo ="";
			String valor ="";
			
			for (int elem_i = 0; elem_i <MO_nro; elem_i++) {
				XSSFRow row_MO_data = hoja_MO.createRow(elem_i+1);
				XSSFCell cell_data = row_MO_data.createCell(0);
				
				int columna = 1;
				
				//MO.Identificador
				atributo="Identificador";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_MO_data.createCell(columna);
				columna++;
				
				//MO.Nombre
				atributo="Nombre";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_MO_data.createCell(columna);
				columna++;
				
				
				//MO.TipoMovimiento
				atributo="TipoMovimiento";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_MO_data.createCell(columna);
				columna++;

				//MO.SubtipoMovimiento
				atributo="SubtipoMovimiento";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_MO_data.createCell(columna);
				columna++;

				//MO.TipoDisolucion
				atributo="TipoDisolucion";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_MO_data.createCell(columna);
				columna++;
				
				
				//MO.IdentSCR
				atributo="IdentSCR";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_MO_data.createCell(columna);
				columna++;
				
				//MO.IdentSCR(Nombre)
				atributo="IdentSCR";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				int indice_objeto = datosXML.devuelveIndice_ObjS3e(datosXML.objSCR_valorAtributos, valor);
				// get(1) porque 1 es el nombre
				valor = datosXML.objSCR_valorAtributos.get(1).get(indice_objeto);
				cell_data.setCellValue(valor);
				cell_data = row_MO_data.createCell(columna);
				columna++;
				
				//MO.nDeslizamiento
				atributo="nDeslizamiento";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_MO_data.createCell(columna);
				columna++;
				
				//MO.nSEIntermedia
				atributo="nSEIntermedia";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_MO_data.createCell(columna);
				columna++;
				
			}

			//Hoja SCR-------------------
			XSSFRow row_SCR = hoja_SCR.createRow(0);
			XSSFCell cell_SCR = row_SCR.createCell(0);
			
			//Rellenamos cabecera
			for (int i=0; i<SCR_header.size(); i ++) {
				cell_SCR = row_SCR.createCell(i);
				cell_SCR.setCellStyle(style);
				cell_SCR.setCellValue(SCR_header.get(i));
			}
	
			for (int elem_i = 0; elem_i <SCR_nro; elem_i++) {
				XSSFRow row_SCR_data = hoja_SCR.createRow(elem_i+1);
				XSSFCell cell_data = row_SCR_data.createCell(0);
				int columna = 1;			
				//SCR.Identificador
				atributo="Identificador";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_SCR_data.createCell(columna);
				columna++;
				
				//CV.Nombre
				atributo="Nombre";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_SCR_data.createCell(columna);
				columna++;					

			}
			
			
			//Hoja CV-------------------
			XSSFRow row_CV = hoja_CV.createRow(0);
			XSSFCell cell_CV = row_CV.createCell(0);
			
			//Rellenamos cabecera
			for (int i=0; i<CV_header.size(); i ++) {
				cell_CV = row_CV.createCell(i);
				cell_CV.setCellStyle(style);
				cell_CV.setCellValue(CV_header.get(i));
			}
	
			for (int elem_i = 0; elem_i <CV_nro; elem_i++) {
				XSSFRow row_CV_data = hoja_CV.createRow(elem_i+1);
				XSSFCell cell_data = row_CV_data.createCell(0);

				int columna = 1;
				//CV.Identificador
				atributo="Identificador";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_CV_data.createCell(columna);
				columna++;
				
				//CV.Nombre
				atributo="Nombre";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_CV_data.createCell(columna);
				columna++;				
			}
			

			//Hoja DZ-------------------
			XSSFRow row_DZ = hoja_DZ.createRow(0);
			XSSFCell cell_DZ = row_DZ.createCell(0);
			
			//Rellenamos cabecera
			for (int i=0; i<DZ_header.size(); i ++) {
				cell_DZ = row_DZ.createCell(i);
				cell_DZ.setCellStyle(style);
				cell_DZ.setCellValue(DZ_header.get(i));
			}
	
			for (int elem_i = 0; elem_i <DZ_nro; elem_i++) {
				XSSFRow row_DZ_data = hoja_DZ.createRow(elem_i+1);
				XSSFCell cell_data = row_DZ_data.createCell(0);
				int columna = 1;

				//DZ.Identificador
				atributo="Identificador";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_DZ_data.createCell(columna);
				columna++;
				
				//DZ.Nombre
				atributo="Nombre";
				indice_atr=datosXML.devuelveIndice_nombreAtr(datosXML.objMO_listaAtributos, atributo);
				valor = datosXML.objMO_valorAtributos.get(indice_atr).get(elem_i);
				cell_data.setCellValue(valor);
				cell_data = row_DZ_data.createCell(columna);
				columna++;				
			}
			
		File excelFile;
		excelFile = new File(filePath); // Referenciando a la ruta y el archivo Excel a crear
		try (FileOutputStream fileOuS = new FileOutputStream(excelFile)) {
		    if (excelFile.exists()) { // Si el archivo existe lo eliminaremos
		        excelFile.delete();
		        System.out.println("Archivo eliminado.!");
		    }
		    book.write(fileOuS);
		    fileOuS.flush();
		    fileOuS.close();
		    System.out.println("Archivo Creado.!");
		 
		        } catch (Exception e) {
		            e.printStackTrace();
		        }	 
			 }
}
