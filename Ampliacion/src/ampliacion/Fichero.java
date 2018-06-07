package ampliacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;

public class Fichero {

  //ATRIBUTOS DE INSTANCIA
  private String rutaDir;
  private String nombre;
  private File ficheroText;

  //CONSTRUCTOR
  public Fichero(String rutaDir, String nombre) {
    this.rutaDir = rutaDir;
    this.nombre = nombre; //EJEMPLO: fichero.csv o fichero.txt
  }

  public String getNombreFichero() {
    return this.nombre;
  }

  public void setNombreFichero(String nombre) {
    this.nombre = nombre;
  }

  //METODOS ESPECIFICOS/////////////////////////////////////////////////////////
  public void generaTCI(String rutadir, String nombreDiccionario) {

    String linea = "";
    List<String> resultadosObtenidos = new ArrayList<String>();

    try {

      //GENERO FICHERO ALEATORIO
      File tci = new File(rutaDir + "/" + this.nombre);

      if (tci.exists()) {
        tci.delete();
      }

      //Se lo paso a FileWriter para su escritura, lleva TRUE PARA QUE NO SOBREESCRIBA.
      FileWriter fw = new FileWriter(tci, true);

      //Este, es pasado a su vez a al buffer por medio de BufferedWriter
      BufferedWriter bw = new BufferedWriter(fw);

      for (int i = 0; i < ((int) (Math.random() * 1000 - 500 + 1) + 500); i++) { //1000 lineas
        linea = (RandomStringUtils.random(((int) (Math.random() * 200 - 50 + 1) + 50), new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'á', 'é', 'í', 'ó', 'ú'}));
        bw.write(linea + "\n");
      }

      bw.close();
      //FIN FICHERO ALEATORIO GENERADO

      //LOGICA QUE COMPARARA LAS LINEAS DEL FICHERO CON EL ARRAYLIST DE DICCIONARIO
      String lineaFicheroTCI = "";

      //Leo diccionario
      File diccionario = new File(rutaDir + "/" + nombreDiccionario);
      String lineaDicci = "";
//      BufferedReader bfdic = new BufferedReader(new FileReader(diccionario));
      BufferedReader bf = new BufferedReader(new FileReader(tci));

//      lineaDicci = bfdic.readLine();
      lineaFicheroTCI = bf.readLine();

      String salidahtml = "";
      int numLinea = 1;
      while (lineaFicheroTCI != null) {
        //System.out.println(linea);
        BufferedReader bfdic = new BufferedReader(new FileReader(diccionario));
        lineaDicci = bfdic.readLine();
        if (lineaFicheroTCI == null) {
          break;
        }

        while (lineaDicci != null) {

          if (lineaDicci == null) {
            break;
          }

          if (lineaFicheroTCI.contains(lineaDicci) && lineaDicci.length() >= 3) {
            System.out.println("ENCONTRADO:" + lineaDicci);
            resultadosObtenidos.add(lineaDicci);
            String[] lineaParaEditar = lineaFicheroTCI.split(lineaDicci);
            
            if (lineaParaEditar.length > 1) {

              salidahtml += "<h4>Linea " + numLinea + ":</h4><p>" + lineaParaEditar[0] + "<strong>" + lineaDicci + "</strong>" + lineaParaEditar[1] + "</p><br>";
            }
          }

          lineaDicci = bfdic.readLine();
        }

        lineaFicheroTCI = bf.readLine();
        numLinea++;
      }
      bf.close();
      String inicioContenidoHTML = "<!doctype html>"
        + "<html lang=\"en\">"
        + "<head>"
        + "<meta charset=\"UTF-8\">"
        + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">"
        + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css\" integrity=\"sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r\" crossorigin=\"anonymous\">"
        + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS\" crossorigin=\"anonymous\"></script>"
        + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
        + "<title>TRABAJO AMPLIACION</title>"
        + "<style>"
        + "strong{color: blue;}"
        + "</style>"
        + "</head>"
        + "<body><div style=\"text-align:center;\">";
      String finContenidoHTML = "</div></body></html>";
      //GENERO pagina html
      File paginaHTML = new File(rutaDir + "/resultados.html");

      if (paginaHTML.exists()) {
        paginaHTML.delete();
      }

      //Se lo paso a FileWriter para su escritura, lleva TRUE PARA QUE NO SOBREESCRIBA.
      FileWriter fwpag = new FileWriter(paginaHTML, true);

      //Este, es pasado a su vez a al buffer por medio de BufferedWriter
      BufferedWriter bwpag = new BufferedWriter(fwpag);
      bwpag.write(inicioContenidoHTML);
      bwpag.write("<h1>RESULTADOS</h1>");
      bwpag.write("<h3>Se han encontrado estas líneas coincidentes con alguna palabra:</h3>");

      BufferedReader bfencontrar = new BufferedReader(new FileReader(tci));
      String lineasficherotci = bfencontrar.readLine();

      bwpag.write(salidahtml);
      bwpag.write("<h3>FICHERO GENERADO:</h3></div><div class='container'>");

      BufferedReader entero = new BufferedReader(new FileReader(tci));
      String lineaimprimir = entero.readLine();

      while (lineaimprimir != null) {
        bwpag.write(lineaimprimir + "<br>");

        lineaimprimir = entero.readLine();
      }
      entero.close();

      bwpag.write(finContenidoHTML);
      bwpag.close();

      //System.out.println("[DEBUG]" + salidahtml);
    } catch (IOException ex) {
      Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
