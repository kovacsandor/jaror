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

            String forras = beolvasasSor;
            System.out.println(forras);
            int ora = Integer.parseInt(beolvasasSor.substring(0, 2));
            System.out.println(ora);
            int perc = Integer.parseInt(beolvasasSor.substring(3, 5));
            System.out.println(perc);
            int masodperc = Integer.parseInt(beolvasasSor.substring(6, 8));
            System.out.println(masodperc);
            String ido = beolvasasSor.substring(0, 8);
            System.out.println(ido);
            String rendszam = beolvasasSor.substring(9, 16);
            System.out.println(rendszam);
            Jarmutipus jarmuTipus = Jarmutipus.AUTOBUSZ;
            System.out.println(jarmuTipus);

            rogzitesek.add(new Rogzites(beolvasasSor, ora, perc, masodperc, ido, rendszam, jarmuTipus));
        }

        for (Rogzites rogzites : rogzitesek) {
            System.out.println(
                    "Óra: " + rogzites.ora
                    + " Perc: " + rogzites.perc
                    + " Másodperc: " + rogzites.masodperc
                    + " Idő: " + rogzites.ido
                    + " Rendszám: " + rogzites.rendszam
                    + " Járműtípus: " + rogzites.jarmuTipus
                    + " Forrás: " + rogzites.forras
            );
        }
//2. Határozza meg, hogy aznap legalább hány óra hosszat dolgoztak az ellenőrzést végzők, ha munkaidejük egész órakor kezdődik, és pontosan egész órakor végződik! (Minden óra 0 perc 0 másodperckor kezdődik, és 59 perc 59 másodperccel végződik.) Az eredményt jelenítse meg a képernyőn!
        System.out.println("2. feladat: ");
//3. Műszaki ellenőrzésre minden órában egy járművet választanak ki. Azt, amelyik abban az órában először halad arra. Az ellenőrzés óráját és az ellenőrzött jármű rendszámát jelenítse meg a képernyőn a következő formában: 9 óra: AB-1234! Minden óra adata külön sorba kerüljön! Csak azon órák adatai jelenjenek meg, amikor volt ellenőrizhető jármű!
        System.out.println("3. feladat: ");
//4. A rendszám első karaktere külön jelentéssel bír. Az egyes betűk közül a „B” autóbuszt, a „K” kamiont, az „M” motort jelöl, a többi rendszámhoz személygépkocsi tartozik. Jelenítse meg a képernyőn, hogy az egyes kategóriákból hány jármű haladt el az ellenőrző pont előtt!
        System.out.println("4. feladat: ");
//5. Mettől meddig tartott a leghosszabb forgalommentes időszak? A választ jelenítse meg a képernyőn a következő formában: 9:9:13 - 9:15:3! 
        System.out.println("5. feladat: ");
//6. A rendőrök egy baleset közelében látott járművet keresnek rendszám alapján. A szemtanúk csak a rendszám bizonyos karaktereire emlékeztek, így a rendszám ismeretlen karaktereit a * karakterrel helyettesítve keresik a nyilvántartásban. Kérjen be a felhasználótól egy ilyen rendszámot, majd jelenítse meg a képernyőn az arra illeszthető rendszámokat!
        System.out.println("6. feladat: ");
//7. Egy közúti ellenőrzés pontosan 5 percig tart. Amíg az ellenőrzés folyik, a járművek szabadon elhaladhatnak, a következő megállítására csak az ellenőrzés befejezése után kerül sor. Ha a rendőrök a legelső járművet ellenőrizték, akkor mely járműveket tudták ellenőrizni a szolgálat végéig? Írja az ellenőrzött járművek áthaladási idejét és rendszámát a vizsgalt.txt állományba az áthaladás sorrendjében, a bemenettel egyező formában! Ügyeljen arra, hogy az időadatokhoz tartozó számok a bevezető nullákat tartalmazzák! 
        System.out.println("7. feladat: ");
    }

    private static class Rogzites {

        String forras;
        int ora;
        int perc;
        int masodperc;
        String ido;
        String rendszam;
        Jarmutipus jarmuTipus;

        public Rogzites(String beolvasasSor, int ora, int perc, int masodperc, String ido, String rendszam, Jarmutipus jarmuTipus) {
            this.forras = beolvasasSor;
            this.ora = ora;
            this.perc = perc;
            this.masodperc = masodperc;
            this.ido = ido;
            this.rendszam = rendszam;
            this.jarmuTipus = jarmuTipus;
        }
    }

    private enum Jarmutipus {
        AUTOBUSZ,
        KAMION,
        MOTOR,
        SZEMELYGEPKOCSI
    }

}
