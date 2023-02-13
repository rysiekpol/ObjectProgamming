package projekt.rosliny;

import projekt.Organizm;
import projekt.Wypisanie;
import projekt.swiat.Swiat;
import projekt.zwierzeta.Zolw;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Trawa extends Roslina implements Wypisanie {
    public Trawa(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 0;
        inicjatywa = 0;
        kolor = Color.green;
        oznaczenie = 't';
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
        if(losuj>95){
            super.akcja();
        }
    }

    @Override
    public String Wypisz() {
        return "Trawa" + getKoncowka();
    }
}
