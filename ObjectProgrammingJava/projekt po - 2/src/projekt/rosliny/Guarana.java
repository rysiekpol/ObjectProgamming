package projekt.rosliny;

import projekt.Organizm;
import projekt.Wypisanie;
import projekt.swiat.Swiat;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Guarana extends Roslina implements Wypisanie {
    public Guarana(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 99;
        inicjatywa = 0;
        kolor = new Color(0, 227, 255);
        oznaczenie = 'g';
        this.swiat = swiat;
        this.czyZyje = czyZyje;
        this.czyMozeRozmnazac = 0;
        wiek = 0;
    }

    @Override
    public void akcja() {
        przygotujOrganizm();
        Random random = new Random();
        int losuj = random.nextInt(100);
        if(losuj>97){
            super.akcja();
        }
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        String status;
        Vector<String> kom = swiat.getKomentarz();
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        swiatOrganizmow[polozenieX][polozenieY] = atakujacy;
        swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = null;
        atakujacy.setPolozenieX(polozenieX);
        atakujacy.setPolozenieY(polozenieY);
        atakujacy.setSila(atakujacy.getSila()+3);
        czyZyje = false;
        status = atakujacy.Wypisz() + " ZJADL " + Wypisz() + " I ZWIEKSZYL SILE O 3";
        kom.add(status);
    }

    @Override
    public String Wypisz() {
        return "Guarana" + getKoncowka();
    }
}
