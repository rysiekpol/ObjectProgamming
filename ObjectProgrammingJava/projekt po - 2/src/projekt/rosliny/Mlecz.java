package projekt.rosliny;

import projekt.Wypisanie;
import projekt.swiat.Swiat;

import java.awt.*;
import java.util.Random;

public class Mlecz extends Roslina implements Wypisanie {
    public Mlecz(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 0;
        inicjatywa = 0;
        kolor = new Color(170, 227, 79);
        oznaczenie = 'm';
        this.swiat = swiat;
        this.czyZyje = czyZyje;
        this.czyMozeRozmnazac = 0;
        wiek = 0;
    }

    @Override
    public void akcja() {
        przygotujOrganizm();
        Random random = new Random();
        for(int i = 0; i<3; i++) {
            int losuj = random.nextInt(100);
            if (losuj > 96) {
                super.akcja();
            }
        }
    }

    @Override
    public String Wypisz() {
        return "Mlecz" + getKoncowka();
    }
}
