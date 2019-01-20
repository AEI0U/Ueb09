import java.io.*;
import java.util.*;
    
/**
 *  Lager Klasse
 *
 * @version Ueb05
 * @author  JKrier, JVogt
 *
 */

public class Lager{
  
  public  static final int     GROESSE = 10;
  public  static final String  ORT= "Lager A";
  private static final String  GROESSE_MIND= " Hier bitte eine Lagergroeße >1 eingeben.";
  private static final String  LAGER_ORT= " Hier bitte einen Lagerort angeben.";
  private static final String  LAGER_VOLL=" Das Lager ist bereits voll.";
  private static final String  ARTIKEL_ANLEGEN=" Bitte Artikel erst anlegen.";
  private static final String  ARTIKEL_VORHANDEN =" Artikel ist bereits im Lager vorhanden.";
  private static final String  INDEX_UNGUELTIG =" Bitte einen gueltigen Index eingeben.";
  private static final String  BESTAND_POSITIV = " Bitte einen kleineren Bestandsabgang buchen.";
  
  private  Artikel[] lager;
  private  int       index;
  private  String    lagerOrt;
  private  int       lagerGroesse;

  /**
   * Der Standard-Konstruktor 
   *        
   */
  public Lager(){
      this.lager = new Artikel[GROESSE];
      this.lagerOrt = ORT;
      
      lager = new Artikel[GROESSE];
      index = -1;

      for (int i = 0; i < GROESSE; i++){
          lager[i] = null;
      }
  }
  
  /**
   *  Konstruktor bei Angabe einer Lagergroesse und dem Lagerort des Artikels
   *        
   * @param lagerGroesse, ist die Groesse des Lagers
   * @param lagerOrt, an dem sich der Artikel befindet
   */
  public Lager(int lagerGroesse, String lagerOrt){
      check((lagerGroesse >1),GROESSE_MIND);
      check ((lagerOrt.trim().length()!=0 && lagerOrt != null), LAGER_ORT);
   
      this.lagerOrt = new String(lagerOrt); 

      lager = new Artikel[lagerGroesse];
      index = -1;

      for (int i = 0; i < lagerGroesse; i++)
      {
          lager[i] = null;
      }
     
  }

  /**
   * Getter fuer den Lagerort
   *
   * @return lagerOrt
   */
  public String getLagerOrt(){
      return lagerOrt;
  }
  
  /**
   * Getter fuer die Lagergroesse
   *
   * @return lagerGroesse
   */
  public int getLagergroesse(){
      return lagerGroesse;
  }
  
  /**
   * Hilfsmethode fuer Dialogabfrage, ob bereits ein Artikel im Lager ist
   */ 
  public int getIndex(){
        return index;
  }
  
  /**
   * Setter fuer den lagerOrt
   *   
   * @param vorheriger lagerOrt
   */
  public void setLagerOrt(String lagerOrt){
      check((lagerOrt != null)||(lagerOrt.trim().length() != 0),LAGER_ORT);
      this.lagerOrt = lagerOrt.trim();
  }
  
  /**
   * Anlegen eines Artikels und einfuegen in das Lager
   *    
   * @param anzulegender Artikel
   */
  public void anlegenArtikel(Artikel artikel){
      check((indexArtikel(artikel.getNummer())==-1),ARTIKEL_VORHANDEN);
      check((index<lager.length),LAGER_VOLL);
      lager[++index] = artikel;
  }
  

  /**
   * Artikel aus dem Lager entfernen
   *    
   *@param ArtikelNummer des zu loeschenden Artikels
   *             
   */
  public void entferneArtikel(int artikelNr){
      int lagerIndex;
      lagerIndex = indexArtikel(artikelNr);
      check((lagerIndex!=-1),ARTIKEL_ANLEGEN);
      
      
      for(int i = lagerIndex; i < index; i++){
              lager[i]=lager[i+1];
      }
      lager[index]=null;
      index--; 
      } 
 
  
  /**
   *Artikelbestand erhoehen
   *    
   * @param artikelNr des Artikels, dessen Bestand erhoeht wird
   * @param artikelMenge, um die der Bestand erhoeht oder reduziert wird, 
   * Bestandsgroesse muss dabei aber immer >= 0 sein!
   */
  public void aenderArtikelbestand(int artikelNr, int artikelMenge)
  {
      int lagerIndex;
    
      lagerIndex = indexArtikel(artikelNr);
      check((lagerIndex!=-1),ARTIKEL_ANLEGEN);
      check((lager[lagerIndex].getBestand())+artikelMenge>=0,BESTAND_POSITIV);
      lager[lagerIndex].bestandAenderung(artikelMenge);
  }

  /**
   * aenderPreis: aendert den Preis aller Artikel um einen uebergebenen Prozentsatz
   *
   *@param aenderung: Prozentsatz, um den die Preise geandert werden. 
   *Fall1: aenderung ist positiv: Preiserhoehung, Fall2: aenderung ist negativ = Preisminderung 
   */
  public void aenderPreis(double aenderung){
      for (int i = 0;i <= index; i++ ){
          lager[i].prozentPreisaenderung(aenderung);
      }
  }

  /**
   * indexArtikel = Hilfsmethode um den Lagerindex eines Artikels zu finden
   * @param Artikelnummer des gesuchten Artikels
   * @return Lagerindex
   */
  private int indexArtikel(int artikelNr){
      int artikelIndex =-1;
      for (int i = 0;i <= index;i++){
          if ( lager[i].getNummer() == artikelNr){
              artikelIndex = i;
          }
     }
    return  artikelIndex;
    
  }
 
  
  /**
   * ausgebenBestandsListe() erzeugt einen String, der den Bestand des Lagers auflistet und zurueck gibt.
   *
   * @return bestandsListe des Lagers
   */
  public String ausgebenBestandsListe(){
        String bestandsListe = "";
        double insgesamt = 0;
        bestandsListe += "\nLagerort: ";
        bestandsListe += "\n\nArtNr  Beschreibung                                  Preis    Bestand  Gesamt   ";
        bestandsListe += "\n================================================================================";
        for (int i = 0; i <= index; i++) {
            bestandsListe += String.format("\n%-6d ",lager[i].getNummer());
            bestandsListe += String.format("%-45s ",lager[i].getBezeichnung());
            bestandsListe += String.format("%-7.2f ",lager[i].getPreis());
            bestandsListe += String.format("  %-7d ",lager[i].getBestand());
            double gesamt = lager[i].getPreis() * lager[i].getBestand();
            insgesamt += gesamt;
            bestandsListe += String.format("%-9.2f ",gesamt);
            bestandsListe += "\n";
        }    
        bestandsListe += "================================================================================\n";
        bestandsListe += String.format("Gesamtwert:                                                            %.2f\n",insgesamt);
        return bestandsListe;

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
   * toString() Methode erzeugt String mit den wichtigsten Informationen zur Lagerung
   *    
   * @return Lagerinformation als String
   */
  public String toString (){
      String ausgabe = new String("Folgende Artikel sind angelegt und vorraetig : \n");
      for (int i = 0; i <= index; i++){
          ausgabe +=  "\n" + lager[i];
      }
      return ausgabe;
  }
}

