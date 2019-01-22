import java.io.*;
    
/**
 *  Klasse Video
 *
 * @version Ueb09
 * @author JKrier, JVogt
 *
 */

public class Video extends Artikel{
      
    public static final int MIN_JAHR = 1950;
    public static final int MAX_JAHR = 2014;
    
    private static final String  MSG_TITEL = "Bitte geben Sie einen Titel ein.";
    private static final String  MSG_DAUER = "Bitte geben Sie eine gueltige (positive) Spieldauer ein.";
    private static final String  MSG_JAHR = "Bitte geben Sie ein Erscheinungsjahr an, das zwischen "+MIN_JAHR+" und "+MAX_JAHR+" liegt.";
  
    private  String  titel;
    private  double  dauer;
    private  int     jahr;
    
    /**
     * Konstruktor
     *
     * @param artikelNr, bezeichnung, bestand, preis, titel, dauer, jahr
     */
    public Video (int artikelNr, String bezeichnung, int bestand, double preis, String titel, double dauer, int jahr){
      super(artikelNr, bezeichnung, bestand, preis);
      this.titel = titel;
      this.dauer = dauer;
      this.jahr = jahr;
      check(((jahr > MIN_JAHR)&&(jahr < MAX_JAHR)), MSG_JAHR);
    }  

    /**
     * Getter-Methode fuer den Titel
     *    
     * @return    Dvd-Titel
     */
    public String getTitel( ){
        return titel;
    }

    /**
     * Setter-Methode fuer den Titel
     *    
     * @param titel
     */
    public void setTitel(String titel){
        check(((titel!=null)||(titel.trim().length()> 0)), MSG_TITEL);
        this.titel = titel.trim();
    }
    
    /**
     * Getter-Methode fuer die Dauer eines Videos
     *    
     * @return dauer
     */
     public double getDauer( ){
         return dauer;
    }

    /**
     * Setter-Methode fuer die Dauer eines Videos
     *    
     * @param dauer
     */
    public void setDauer(double dauer){
        check((dauer>0.0), MSG_DAUER);
        this.dauer = dauer;
    } 
  
    /**
     * Getter-Methode fuer das Erscheinungsjahr des Videos
     *    
     * @return jahr, in dem das Video erschienen ist
     */
    public int getJahr(){
        return jahr;
    }

    /**
     * Setter-Methode fuer das Erscheinungsjahr des Videos
     *    
     * @param jahr, in dem das Video erschienen ist
     */
    public void setJahr(int jahr){
        check(((jahr > MIN_JAHR)&&(jahr < MAX_JAHR)), MSG_JAHR);
        this.jahr = jahr;
     }
     
    /**
     * Getter-Methode fuer die Beschreibung als (Titel)
     *
     * @return beschreibung
     */
    public String getBezeichnung(){
        return titel;
    }
    
    /**
     * Check-Methode um Fehler zu erkennen und als 
     * IllegalArgumentException auszuwerfen.
     */
    private static void check(boolean bedingung, String msg){
        if (!bedingung)
            throw new IllegalArgumentException(msg);
    }
    
    /**
     * toString()-Methode: zur Ausgabe von Autor, Titel und Verlag
     *
     * @return die String-Repraesentation der Buch
     */
    public String toString(){
        String original = super.toString();
        String neu = original + "\tTitel: "+titel;
        return neu;
    }
}
