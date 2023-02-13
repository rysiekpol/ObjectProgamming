package projekt.zwierzeta;

import projekt.Organizm;
import projekt.Wypisanie;
import projekt.obsluga.Para;
import projekt.swiat.Swiat;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Zolw extends Zwierze implements Wypisanie {

    public Zolw(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 2;
        inicjatywa = 1;
        kolor = new Color(20, 73, 15);
        oznaczenie = 'Z';
        this.swiat = swiat;
        this.czyZyje = czyZyje;
        this.czyMozeRozmnazac = 0;
        wiek = 0;
    }

    @Override
    public void akcja() {
        Random random = new Random();
        int szansa = random.nextInt(100);
        if(szansa>75){
            super.akcja();
        }else{
            przygotujOrganizm();
        }
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        String status = "";
        Vector<String> kom = swiat.getKomentarz();
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        if(!(atakujacy.getSila() < 5) || atakujacy instanceof Zolw){
            super.kolizja(atakujacy);
        }else {
            status = Wypisz() + " ODBIL ATAK " + atakujacy.Wypisz();
        }
        kom.add(status);
    }

    @Override
    public String Wypisz() {
        return "Zolw" + getKoncowka();
    }
}
