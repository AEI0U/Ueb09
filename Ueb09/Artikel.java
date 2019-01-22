/**
 * Klasse Artikel
 *
 * @author JKrier, JVogt
 * @version Ueb02 / Ueb05
 */
public class Artikel
{
    private int     artikelNummer;
    private String  artikelBezeichnung;
    private int     artikelBestand;
    private double  artikelPreis;
    
    private final String  MSG_NUMMER = " Bitte geben Sie eine vierstellige Artikelnummer ein.";
    private final String  MSG_POSITIVER_WERT = "Bitte geben Sie hier einen positiven Wert ein.";
    private final String  MSG_BESTAND = " Hier bitte einen kleineren Wert eingeben! Mit dem eingegebenen Wert würde es einen negativen Bestand geben.";
    private final String  MSG_BEZEICHNUNG = " Bitte geben Sie eine Artikelbezeichnung ein.";
    private final String  MSG_PROZENT = " Bitte geben Sie einen gueltigen Prozentsatz ein.";
    
    private final int    BESTAND_MIN  = 0;
    private final double PREIS_MIN    = 0.0;
    private final double PROZENT_MIN  = -100.0;

    /**
     * Konstruktor mit drei Argumenten
     * 
     * @param nummer Muss vierstellig sein.
     * @param bezeichnung Muss vorhanden sein. (!= null)
     * @param bestand Muss positiv sein.
     */
    public Artikel(int nummer, String bezeichnung, int bestand, double preis){
        String s = Integer.toString(nummer);
        check (((nummer >= 0000) && (nummer <= 9999)&&(s.length()==4)),MSG_NUMMER);
        check (((!bezeichnung.trim().equals("") && bezeichnung != null)), MSG_BEZEICHNUNG);
        check ((bestand >=BESTAND_MIN),MSG_POSITIVER_WERT);
        check((preis>=0),MSG_POSITIVER_WERT);
        
        this.artikelNummer = nummer;
        this.artikelBezeichnung = new String(bezeichnung);
        this.artikelBestand = bestand;
        this.artikelPreis = preis;
    }
    
    /**
     * Konstruktor mit zwei Argumenten: Wenn ein Artikel neu im Sortiment 
     * angelegt werden soll, aber noch kein Artikel vorhanden ist
     * und noch kein Preis bekannt ist.
     * 
     * @param nummer Muss vierstellig sein.
     * @param bezeichnung Muss vorhanden sein. (!= null)
     */
    public Artikel(int nummer, String bezeichnung){
        this(nummer,bezeichnung,0,0);
    }
    
    /**
     * Methode um eine Bestandsaenderung zu buchen.
     * Fall 1: Menge ist negativ: Bestandsabgang, 
     * Fall 2: Menge ist positiv: Bestandszugang
     * @return artikelBestand.
     */
    public int bestandAenderung(int menge){
        artikelBestand += menge;                
        check ((artikelBestand-menge>BESTAND_MIN),MSG_BESTAND);
        return artikelBestand;
    }
    
    /**
     * Methode um eine Preisaenderung zu buchen.
     *
     * @param  prozent: Prozentuale Aenderung des Preises. 
     * Fall1: prozent ist positiv: Preiserhoehung, Fall2: prozent ist negativ = Preisminderung 
     */
    public void prozentPreisaenderung(double prozent){
        check ((prozent>PROZENT_MIN),MSG_PROZENT);
        artikelPreis += artikelPreis * prozent/100.0;
    }
  
    /**
     * Getter Methoden der Attribute Nummer, Bestand, Bezeichnung sowie Preis
     *
     * @return die jeweiligen Attributwerte.
     */
    public int getNummer(){
        return artikelNummer;
    }
    public int getBestand(){
        return artikelBestand;
    }
    public String getBezeichnung(){
        return artikelBezeichnung;
    }
    public double getPreis(){
        return artikelPreis;
    }
     
    /**
     * Setter Methodefuer das Attribut Bezeichnung
     * @param artikelBezeichnung uebernimmt den im Methodenaufruf mitgegebenen "neueBezeichnung" Stringwert.
     * (Der Setter fuer die artikelNummer wird verzichtet, da Artikel IDs nicht veraendert werden sollten.)
     */
    public void setBezeichnung(String neueBezeichnung){
        check (((!neueBezeichnung.trim().equals("") || neueBezeichnung != null)), MSG_BEZEICHNUNG);
        artikelBezeichnung = neueBezeichnung;
    }
    
    /**
     * Setter Methode fuer das Attribut Preis
     * @param artikelPreis uebernimmt den im Methodenaufruf mitgegebenen "neuerPreis" .
     */
    public void setPreis(double neuerPreis){
        check ((neuerPreis>0), MSG_POSITIVER_WERT);
        artikelPreis = neuerPreis;
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
     * toString Methode, bei der ein String generiert wird, der bei einer KonsolenAusgabe ausgegeben wird.
     */
    public String toString (){
        return "Artikel: "+artikelNummer+"\tBezeichnung: "+artikelBezeichnung+"\tBestand: "+artikelBestand+"\tPreis: "+artikelPreis+"\tGesamt: "+artikelBestand*artikelPreis;
    } 
}