package projekt.zwierzeta;

import projekt.Wypisanie;
import projekt.swiat.Swiat;

import java.awt.*;

public class Owca extends Zwierze implements Wypisanie {

    public Owca(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 4;
        inicjatywa = 4;
        kolor = Color.pink;
        oznaczenie = 'O';
        this.swiat = swiat;
        this.czyZyje = czyZyje;
        this.czyMozeRozmnazac = 0;
        wiek = 0;
    }

    @Override
    public String Wypisz() {
        return "Owca" + getKoncowka();
    }
}
