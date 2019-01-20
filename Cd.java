import java.io.*;
    
/**
 * Klasse Cd
 *   
 * @version Ueb09
 * @author JKrier, JVogt
 *
 */

public class Cd extends Artikel{
    
    private static final String  MSG_ARTIST = "Bitte geben Sie einen Interpreten ein.";
    private static final String  MSG_TITEL = "Bitte geben Sie einen Titel ein.";
    private static final String  MSG_ANZAHL = "Bitte geben Sie eine gueltige (positive) Anzahl der Musiktitel ein."; 
  
    private  String  artist;
    private  String  titel;
    private  int     anzahl;
    
    /**
     * Konstruktor
     *
     * @param artikelNr, bezeichnung, bestand, preis, artist ,titel, anzahl
     */
     public Cd (int artikelNr, String bezeichnung, int bestand, double preis, String artist, String titel, int anzahl){
         super(artikelNr, bezeichnung, bestand, preis);
         this.artist = artist;
         this.titel = titel;
         this.anzahl=anzahl;
    }  
    
    /**
     * Getter-Methode fuer den Interpreten
     *    
     * @return artist
     */
     public String getArtist(){
        return artist;
    }

    /**
     * Setter-Methode fuer den Interpreten
     *    
     * @param artist
     */
    public void setArtist (String artist){
       check(((artist!=null)||(artist.trim().length()> 0)), MSG_ARTIST);
       this.artist = artist.trim();
    } 
    
    /**
     * Getter-Methode fuer den Titel
     *    
     * @return titel
     */
    public String getTitel ( ){
      return titel;
    } 
    
    /**
     * Setter-Methode fuer den Titel
     *    
     * @param titel
     */
     public void setTitel (String titel){
         check(((titel!=null)||(titel.trim().length()> 0)), MSG_TITEL);
         this.titel = titel.trim();
    }
    
    /**
     * Getter-Methode fuer die Anzahl der Musiktitel
     *    
     * @return anzahl
     */
    public int getAnzahl(){
        return anzahl;
    }

    /**
     * Setter-Methode fuer die Anzahl der Musiktitel
     *    
     * @param anzahl
     */
    public void setAnzahl(int anzahl){
        check((anzahl>0), MSG_ANZAHL);
        this.anzahl = anzahl;
    }
  
    /**
     * Getter-Methode fuer die Beschreibung als (Interpret : Titel)
     *
     * @return beschreibung
     */
    public String getBeschreibung(){
        String beschreibung = artist+" : "+titel;
        return beschreibung;
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
    public String toString() {
        String original = super.toString();
        String neu = original + "\tArtist: "+artist+ "\tTitel: "+titel + "\tAnzahl "+anzahl;
        return neu;
    }

}
