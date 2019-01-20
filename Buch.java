
import java.io.*;

/**
 * Klasse Buch
 *
 * @version	Ueb09
 * @author	JKrier, JVogt
 *
 */

 public class Buch extends Artikel{
     
    private static final String MSG_AUTOR = "Bitte geben Sie einen Autor ein.";
    private static final String MSG_TITEL = "Bitte geben Sie einen Titel ein.";
    private static final String MSG_VERLAG = "Bitte geben Sie einen Verlag ein.";

    private String autor;
    private String titel;
    private String verlag;

    /**
     * Konstruktor
     *
     * @param artikelNr, bezeichnung, bestand, preis, autor, titel, verlag
     */
    public Buch(int artikelNr, String bezeichnung, int bestand,double preis, String autor, String titel, String verlag){
        super(artikelNr, bezeichnung, bestand, preis);
        this.autor = autor;
        this.titel = titel;
        this.verlag = verlag;
    }

    /**
     * Setter-Methode fuer den Autor
     */
    public String getAutor(){
        return autor;
    }

    /**
     * Setter-Methode fuer den Autor
     *
     * @param autor
     */
    public void setAutor(String autor){
        check(((autor!=null)||(autor.trim().length()> 0)), MSG_AUTOR);
        this.autor = autor.trim();
    }

    /**
     * Getter-Methode fuer den Titel
     *
     * @return titel
     */
    public String getTitel(){
        return titel;
    }

    /**
     * Setter-Methode fuer den Titel
     *
     * @param titel
     */
    public void setTitel(String titel) 
    {
        check(((titel!=null)||(titel.trim().length()> 0)), MSG_TITEL);
        this.titel = titel.trim();
    }

    /**
     * Getter-Methode fuer den Verlag
     *
     * @return verlag
     */
    public String getVerlag(){
        return verlag;
    }

    /**
     * Setter-Methode fuer den Verlag
     *
     * @param verlag
     */
    public void setVerlag(String verlag){
        check(((verlag!=null)||(verlag.trim().length()> 0)), MSG_VERLAG);
        this.verlag = verlag;
    }

    /**
     * Getter-Methode fuer die Beschreibung als (Autor:Titel)
     *
     * @return beschreibung
     */
    public String getBeschreibung(){
        String beschreibung = autor+" : "+titel;
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
        String neu = original + "\tAutor:  "+autor+"\tTitel: "+titel + "\tVerlag: "+verlag;
        return neu;
    }

}
