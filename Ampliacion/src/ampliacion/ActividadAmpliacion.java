package ampliacion;

public class ActividadAmpliacion {
    public static void main(String[] args) {
        //Ruta donde guardar el fichero generado con caracteres aleatorios
        String rutadir = ".";
        //Indicar nombre del diccionario que tengo en la raiz del proyecto
        String nombreDiccionario = "lemario.txt";
        //Fichero aleatorio
        Fichero tci = new Fichero(rutadir,"tci.txt");
        
        tci.generaTCI(rutadir, nombreDiccionario);
        
        
    }
    
}
