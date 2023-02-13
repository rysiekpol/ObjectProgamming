package projekt.zwierzeta;

import projekt.Organizm;
import projekt.Wypisanie;
import projekt.swiat.Swiat;

import java.awt.*;

public class Wilk extends Zwierze implements Wypisanie {

    public Wilk(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 9;
        inicjatywa = 5;
        kolor = Color.black;
        oznaczenie = 'W';
        this.swiat = swiat;
        this.czyZyje = czyZyje;
        this.czyMozeRozmnazac = 0;
        wiek = 0;
    }

    @Override
    public String Wypisz() {
        return "Wilk" + getKoncowka();
    }
}
