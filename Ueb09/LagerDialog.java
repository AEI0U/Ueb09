import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *    Die Dialog Klasse fuer die Lager-Klasse aus Ueb05
 *
 * @version  Ueb05
 * @author  JKrier, JVogt
 *
 */

public class LagerDialog{

    private Scanner input;
    private Lager lager;

    private final int ENDE =0;
    private final int STANDARD_LAGER_ANLEGEN = 1;
    private final int LAGER_ANLEGEN = 2;
    private final int ARTIKEL_ZUFUEGEN =1;
    private final int ARTIKEL_ENTFERNEN =2;
    private final int BESTANDSAENDERUNG =3;
    private final int PREIS =4;
    private final int BESTANDSLISTE =5;
    private final int LAGER_LOESCHEN =6;
    private final int BUCH=1;
    private final int CD=2;
    private final int VIDEO=3;
    private final int ANDERER=4;
    
    private final int NR_MIN =0;
    private final int NR_MAX =9999;
    private final int MIN_BESTAND = 0;
    private final static String DOUBLE_WERT =" Bitte eine Kommalzahl eingeben!";
    private final static String INT_WERT =" Bitte eine ganze Zahl eingeben!";
    private final static String ARTIKEL_NR = " Bitte eine gueltige Artikelnummer eingeben!";

    private final   String  MSG_NUMMER = " Die eingegebene ArtikelNummer ist nicht vierstellig!";
    private final   String  MSG_POSITIVER_WERT = " Hier bitte einen positiven Wert eingeben!";
    private final   String  MSG_BESTAND = " Hier bitte einen kleineren Wert eingeben! Mit dem eingegebenen Wert würde es einen negativen Bestand geben.";
    private final   String  MSG_BEZEICHNUNG = " Bitte Artikelbezeichnung eingeben.";
    private final   String  MSG_PROZENT = " Bitte gueltigen Prozentsatz (als Kommazahl) eingeben.";
    /**
     * Konstruktor fuer ArtikelDialog
     */
    public LagerDialog(){
        lager = null;
        input = new Scanner( System.in );
    }

    /**
     * run() Interaktives Testprogramm
     * 
     */
    public void run(){
        int wahl=-1;
        try{
            while (wahl != ENDE){
                
                if (lager==null){
                    wahl = readlnInt(" Bitte waehlen Sie aus: "+
                        "\n 1 = Neues Standard-Lager anlegen"+
                        "\n 2 = Neues Lager mit anderen Parameter anlegen"+
                        "\n 0 = Ende\n");
                    switch (wahl){

                        case ENDE:
                            System.out.println("Das Programm wurde beendet.");
                            break;

                        case STANDARD_LAGER_ANLEGEN:
                            lager= new Lager();
                            break;

                        case LAGER_ANLEGEN:
                            lager = new Lager (readGroesse(),readOrt());
                            break; 

                        default:
                            System.out.println("Ungueltige Eingabe!");
                            break;
                    }
                }
                else if (lager!=null&&lager.getIndex()==-1){
                    
                    wahl = readlnInt("\n 1 = Artikel ins Lager zufuegen"+ "\n 6 = Lager ausfloesen"+"\n 0 = Ende\n");
                    
                     switch (wahl){
                         case ENDE:
                            System.out.println("Das Programm wurde beendet.");
                            break;

                         case ARTIKEL_ZUFUEGEN:
                            anlegenArtikel();
                            break;
                            
                         case LAGER_LOESCHEN:
                             lager = null;
                             break;
                                
                         default:
                             System.out.println("Ungueltige Eingabe!");
                             break;
                     }
                }
                else if(lager!=null&&lager.getIndex()!=-1){   
                        wahl = readlnInt("\n Bitte waehlen Sie aus: "+
                        "\n 1 = Artikel ins Lager zufuegen"+
                        "\n 2 = Artikel aus dem Lager entfernen"+
                        "\n 3 = Bestandsaenderung eines Artikels buchen"+
                        "\n 4 = Preisaenderung"+
                        "\n 5 = BestandsListe ausgeben"+
                        "\n 6 = Lager aufloesen"+
                        "\n 0 = Ende\n");

                    switch (wahl){
                        case ENDE:
                            System.out.println("\nDas Programm wurde beendet.");
                            break;

                        case ARTIKEL_ZUFUEGEN:
                            anlegenArtikel();
                            break;

                        case ARTIKEL_ENTFERNEN:
                            lager.entferneArtikel(readArtikelNr());
                            if(lager.getIndex()==-1){
                                lager = null;
                            }
                            break;

                        case BESTANDSAENDERUNG:
                            lager.aenderArtikelbestand(readArtikelNr(),readMenge());
                            break;

                        case PREIS:
                            lager.aenderPreis(readProzent());
                            break;

                        case BESTANDSLISTE:
                            System.out.println(lager.ausgebenBestandsListe());
                            break;
                            
                        case LAGER_LOESCHEN:
                            lager = null;
                            break;

                        default:
                            System.out.println("\nUngueltige Eingabe!");
                            break;
                    }
                  }
                }
            }
           
        catch (AssertionError ae){
            System.err.println(ae);
        } 
        catch ( NumberFormatException nfe){
            System.err.println(nfe);                           
        }       
        catch (RuntimeException rex){
            System.err.println(rex);
        }
    }
    
    /**
     * anlegenArtikel() legt einen Artikel basierend auf dem ausgewaehlten Artikeltyps (Buch, Cd,Video oder Anderer, wenn man keinen davon anlegen will) im Lager an.
     */
    public void anlegenArtikel(){
        int wahl=-1;
        Artikel artikel;
        int     anzahl;
        int     bestand;
        int     jahr;
        int     nummer;
        double  dauer;
        double  preis;
        String  artist;
        String  autor;
        String  bezeichnung;
        String  titel;
        String  verlag;
        
        try{
            System.out.println("\nBitte waehlen Sie aus welcher Artikel-Typ ins Lager zugefuegt werden soll: ");
            wahl = readlnInt("\n 1 = Buch"+ "\n 2 = Cd"+ "\n 3 = Video"+ "\n 4 = Anderer Artikel-Typ");
            nummer = readlnInt("\nArtikelNummer eingeben: ");
            String s = Integer.toString(nummer);
            check (((nummer >= 0000) && (nummer <= 9999)&&(s.length()==4)),MSG_NUMMER);
            System.out.print("Artikel-Beschreibung eingeben: ");
            bezeichnung = input.nextLine();
            check (((!bezeichnung.trim().equals("") && bezeichnung != null)), MSG_BEZEICHNUNG);
            bestand = readlnInt("ArtikelBestand eingeben: ");
            check ((bestand >=MIN_BESTAND),MSG_POSITIVER_WERT);
            preis = readlnDouble("Preis eingeben: ");
            
            check((preis>=0),MSG_POSITIVER_WERT);
            
            
            
            
            switch (wahl){
                default:
                    System.out.println("\nUngueltige Eingabe!");
                    break;
                
                case BUCH:
                    System.out.print("Buch-Autor eingeben: ");
                    autor = input.nextLine();
                    System.out.print("Buch-Titel eingeben: ");
                        titel = input.nextLine();
                        System.out.print("Buch-Verlag eingeben: ");
                        verlag =  input.nextLine();
                        lager.anlegeArtikel(new Buch(nummer,bezeichnung, bestand, preis, autor, titel, verlag));
                        break;
            
                    case CD:
                        System.out.print("Cd-Interpret eingeben: ");
                        artist = input.nextLine();
                        System.out.print("Cd-Titel eingeben: ");
                        titel = input.nextLine();
                        anzahl= readlnInt("Anzahl der Musiktitel auf einer Cd eingeben: ");
                        lager.anlegeArtikel(new Cd(nummer, bezeichnung, bestand, preis, artist, titel, anzahl));
                        break;
            
                    case VIDEO:
                        System.out.print("Video-Titel eingeben: ");
                        titel = input.nextLine();
                        dauer = readlnDouble("Video-Spieldauer eingeben: ");
                        jahr = readlnInt("Video-Erscheinungsjahr eingeben: ");
                        lager.anlegeArtikel(new Video(nummer, bezeichnung, bestand, preis, titel, dauer, jahr));
                        break;
                        
                    case ANDERER:
                        lager.anlegeArtikel(new Artikel(nummer, bezeichnung, bestand, preis));
                        break;
                    }
                
            }
            catch (AssertionError ae){
                System.err.println(ae);
            } 
            catch ( NumberFormatException nfe){
                System.err.println(nfe);                           
            }       
            catch (RuntimeException rex){
                System.err.println(rex);
            }
    }
                            
    /**
     * readOrt: Methode zum Einlesen des Lagerortes
     * @return Lagerort als String
     */
    private String readOrt(){
        String ort;
        System.out.print("Lagerort eingeben: ");
        ort = input.nextLine();
        return ort;
     }

    /**
     * readGroesse: Methode zum Einlesen der Lagergroesse
     * @return Groesse des Lager(arrays)
     */
    private int readGroesse(){
        int groesse;
        return readlnInt("Lagergroesse des neuen Lagers eingeben: ");
    }

    /**
     * readArtikelNr(): Methode zum Einlesen der Artikelnummer, für die eine der folgenden methoden durchgefuehrt werden soll: 
     * Entfernen, Bestandserhoehung oder Bestandsverringerung
     * @return int ArtikelNummer
     */
    private int readArtikelNr(){
        int artikelNr; 
        artikelNr = readlnInt("\nWaehlen Sie eine gueltige Artikelnummer aus: ");
        String s = Integer.toString(artikelNr);
        check (((artikelNr >= NR_MIN) && (artikelNr <= NR_MAX)&&(s.length()==4)),ARTIKEL_NR);
        return artikelNr;
    }
 
    /**
     * readMenge(): Methode zum Einlesen der Menge zur Bestandsaenderung
     */
    private int readMenge(){
        int menge;
        return menge = readlnInt("\nMenge eingeben: ");
    }
  
    /**
     * readProzent()
     */
    private double readProzent(){
        double prozent;
        return prozent=readlnDouble("Prozentuale Preisaenderung eingeben (als Kommazahl)");
    }
  
    /**
     * Einlesen einzugebener Double Werte.
     *  
     *  @param  Eingabeaufforderung Der Eingabe-Aufforderungs-Text
     *  @return die Eingabe-Zahl
     */
    private double readlnDouble(String eingabe){
        double wert = 0;
        try {
            System.out.print(eingabe);
            wert= input.nextDouble();
            input.nextLine();
            }
        catch (InputMismatchException e){
            System.err.println(e+ DOUBLE_WERT);
         } 
        return wert;
    }
    
    /**
     * Einlesen einzugebener Integer Werte.
     *  
     *  @param  Eingabeaufforderung Der Eingabe-Aufforderungs-Text
     *  @return die Eingabe-Zahl
     */
    private int readlnInt(String eingabe){
        int wert = 0;
        try {
            System.out.print(eingabe);
            wert= input.nextInt();
            input.nextLine();
        }
        catch (InputMismatchException e) {
              System.err.println(e+ INT_WERT);
        } 
        return wert;
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
     *  main Methode Ausfuehren des interaktiven Tests.
     *  @param args Kommandozeilenparameter
     */  
    public static void main(String[] args){
        LagerDialog dialog = new LagerDialog();
        dialog.run();
    }
}
