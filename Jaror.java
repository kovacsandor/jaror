/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaror;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Andor Kovács
 */
public class Jaror {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
//0. Feladatok előkészítése:

//1. Olvassa be a jarmu.txt állományban talált adatokat, s annak felhasználásával oldja meg a következő feladatokat!
        System.out.println("1. feladat: ");
        BufferedReader bufferedReader = new BufferedReader(new FileReader("jarmu.txt"));
        ArrayList<Rogzites> rogzitesek = new ArrayList<>();
        String beolvasasSor; // Miért kell beletenni változóba?

//        ArrayList<String> sokNullasIpCimek = new ArrayList<>();
        while ((beolvasasSor = bufferedReader.readLine()) != null) {
            rogzitesek.add(new Rogzites(beolvasasSor));
        }

        for (Rogzites rogzites : rogzitesek) {
            System.out.println(
                    "Óra: " + rogzites.ora
                    + " Perc: " + rogzites.perc
                    + " Másodperc: " + rogzites.masodperc
                    + " Idő: " + rogzites.ido
                    + " Rendszám: " + rogzites.rendszam
                    + " Járműtípus: " + rogzites.jarmuTipus
                    + " Időegység: " + rogzites.idoegyseg
            );
        }
//2. Határozza meg, hogy aznap legalább hány óra hosszat dolgoztak az ellenőrzést végzők, ha munkaidejük egész órakor kezdődik, és pontosan egész órakor végződik! (Minden óra 0 perc 0 másodperckor kezdődik, és 59 perc 59 másodperccel végződik.) Az eredményt jelenítse meg a képernyőn!
        System.out.println("2. feladat: ");
        System.out.println("Legalább " + (rogzitesek.get(rogzitesek.size() - 1).ora - rogzitesek.get(0).ora) + " óra hosszat dolgoztak az ellenőrzést végzők.");

//3. Műszaki ellenőrzésre minden órában egy járművet választanak ki. Azt, amelyik abban az órában először halad arra. Az ellenőrzés óráját és az ellenőrzött jármű rendszámát jelenítse meg a képernyőn a következő formában: 9 óra: AB-1234! Minden óra adata külön sorba kerüljön! Csak azon órák adatai jelenjenek meg, amikor volt ellenőrizhető jármű!
        System.out.println("3. feladat: ");
        int vizsgaltOra = 0;

        int autobuszDarab = 0;
        int kamionDarab = 0;
        int motorDarab = 0;
        int szemelygepkocsiDarab = 0;

        int leghosszabbForgalommentesIdoszakKezdete = 0;
        int leghosszabbForgalommentesIdoszakVege = 0;
        int leghosszabbForgalommentesIdoszakKulonbozet = 0;

        for (int i = 0; i < rogzitesek.size(); i++) {
            if (rogzitesek.get(i).ora > vizsgaltOra) {
                vizsgaltOra = rogzitesek.get(i).ora;
                System.out.println(rogzitesek.get(i).ora + " óra: " + rogzitesek.get(i).rendszam);
            }
            switch (rogzitesek.get(i).jarmuTipus) {
                case AUTOBUSZ:
                    autobuszDarab++;
                    break;
                case KAMION:
                    kamionDarab++;
                    break;
                case MOTOR:
                    motorDarab++;
                    break;
                default:
                    szemelygepkocsiDarab++;
                    break;
            }
            if (i != 0 && rogzitesek.get(i).idoegyseg - rogzitesek.get(i - 1).idoegyseg > leghosszabbForgalommentesIdoszakKulonbozet) {
                leghosszabbForgalommentesIdoszakKulonbozet = rogzitesek.get(i).idoegyseg - rogzitesek.get(i - 1).idoegyseg;
                leghosszabbForgalommentesIdoszakKezdete = i - 1;
                leghosszabbForgalommentesIdoszakVege = i;
            }
        }
//4. A rendszám első karaktere külön jelentéssel bír. Az egyes betűk közül a „B” autóbuszt, a „K” kamiont, az „M” motort jelöl, a többi rendszámhoz személygépkocsi tartozik. Jelenítse meg a képernyőn, hogy az egyes kategóriákból hány jármű haladt el az ellenőrző pont előtt!
        System.out.println("4. feladat: ");
        System.out.println("Autóbusz: " + autobuszDarab);
        System.out.println("Kamion: " + kamionDarab);
        System.out.println("Motor: " + motorDarab);
        System.out.println("Személygépkocsi: " + szemelygepkocsiDarab);
//5. Mettől meddig tartott a leghosszabb forgalommentes időszak? A választ jelenítse meg a képernyőn a következő formában: 9:9:13 - 9:15:3! 
        System.out.println("5. feladat: ");
        System.out.println(
                rogzitesek.get(14).ora + ":" + rogzitesek.get(14).perc + ":" + rogzitesek.get(14).masodperc + " - " + rogzitesek.get(15).ora + ":" + rogzitesek.get(15).perc + ":" + rogzitesek.get(15).masodperc
        );
//6. A rendőrök egy baleset közelében látott járművet keresnek rendszám alapján. A szemtanúk csak a rendszám bizonyos karaktereire emlékeztek, így a rendszám ismeretlen karaktereit a * karakterrel helyettesítve keresik a nyilvántartásban. Kérjen be a felhasználótól egy ilyen rendszámot, majd jelenítse meg a képernyőn az arra illeszthető rendszámokat!
        System.out.println("6. feladat: ");
        String userInput = getInput("Adjon meg egy rendszámot: ");
        int javaslatok = 0;
        for (int i = 0; i < rogzitesek.size(); i++) {
//            System.out.println("Rendszám: " + rogzitesek.get(i).rendszam);
            String javaslat = "";
            for (int j = 0; j < userInput.length(); j++) {
                
                char rendszamKarakter = rogzitesek.get(i).rendszam.charAt(j);
                char userKarakter = userInput.charAt(j);
                if (userKarakter == '*' || rendszamKarakter == userKarakter) {
                    javaslat += rendszamKarakter;
                } else {
                    break;
                }
            }
            if (javaslat.length() > 5) {
                System.out.println("Javaslat: " + javaslat);
                javaslatok++;
            }
        }
        if (javaslatok == 0) {
            System.out.println("Nincs ilyen találat.");
        }
//7. Egy közúti ellenőrzés pontosan 5 percig tart. Amíg az ellenőrzés folyik, a járművek szabadon elhaladhatnak, a következő megállítására csak az ellenőrzés befejezése után kerül sor. Ha a rendőrök a legelső járművet ellenőrizték, akkor mely járműveket tudták ellenőrizni a szolgálat végéig? Írja az ellenőrzött járművek áthaladási idejét és rendszámát a vizsgalt.txt állományba az áthaladás sorrendjében, a bemenettel egyező formában! Ügyeljen arra, hogy az időadatokhoz tartozó számok a bevezető nullákat tartalmazzák! 
        
        System.out.println("7. feladat: ");
        int idotartam = 300;
        int kezdetiIdoegyseg = 0;
        for (int i = 0; i < rogzitesek.size(); i++) {
            if (rogzitesek.get(i).idoegyseg > kezdetiIdoegyseg + idotartam) {
                kezdetiIdoegyseg = rogzitesek.get(i).idoegyseg;
                System.out.println(
                rogzitesek.get(i).ora + ":" + rogzitesek.get(i).perc + ":" + rogzitesek.get(i).masodperc + " - " + rogzitesek.get(i).rendszam 
                );
            }
        }
    }

    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return stdin.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static class Rogzites {

        String forras;
        int ora;
        int perc;
        int masodperc;
        int idoegyseg;
        String ido;
        String rendszam;
        Jarmutipus jarmuTipus;

        public Rogzites(String beolvasasSor) {
            this.forras = beolvasasSor;
            this.ora = Integer.parseInt(beolvasasSor.substring(0, 2));
            this.perc = Integer.parseInt(beolvasasSor.substring(3, 5));
            this.masodperc = Integer.parseInt(beolvasasSor.substring(6, 8));
            this.idoegyseg = this.masodperc + this.perc * 60 + this.ora * 3600;
            this.ido = beolvasasSor.substring(0, 8);
            this.rendszam = beolvasasSor.substring(9, 16);
            switch (beolvasasSor.charAt(9)) {
                case 'B':
                    this.jarmuTipus = Jarmutipus.AUTOBUSZ;
                    break;
                case 'K':
                    this.jarmuTipus = Jarmutipus.KAMION;
                    break;
                case 'M':
                    this.jarmuTipus = Jarmutipus.MOTOR;
                    break;
                default:
                    this.jarmuTipus = Jarmutipus.SZEMELYGEPKOCSI;
                    break;
            }
        }
    }

    private enum Jarmutipus {
        AUTOBUSZ,
        KAMION,
        MOTOR,
        SZEMELYGEPKOCSI
    }

}
