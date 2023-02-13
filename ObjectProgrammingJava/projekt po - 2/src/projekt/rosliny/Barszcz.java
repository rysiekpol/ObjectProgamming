package projekt.rosliny;

import projekt.Organizm;
import projekt.Wypisanie;
import projekt.obsluga.Para;
import projekt.swiat.Swiat;
import projekt.zwierzeta.Zwierze;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Barszcz extends Roslina implements Wypisanie {
    public Barszcz(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 10;
        inicjatywa = 0;
        kolor = new Color(65, 54, 225);
        oznaczenie = 'b';
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
        if(losuj>98){
            super.akcja();
        }
        String status;
        Vector<String> kom = swiat.getKomentarz();
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        Vector<Para> zwierzeta = szukajZwierzecia(polozenieX, polozenieY);
        if (zwierzeta.size() > 0) {
            for (Para wsp : zwierzeta) {
                status = Wypisz() + " ZABIL " + swiatOrganizmow[wsp.getL()][wsp.getR()].Wypisz();
                swiatOrganizmow[wsp.getL()][wsp.getR()].setCzyZyje(false);
                swiatOrganizmow[wsp.getL()][wsp.getR()] = null;
                kom.add(status);
            }
        }
    }

    private Vector<Para> szukajZwierzecia(int x, int y) {
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        int sizeX = swiat.getSizeX();
        int sizeY = swiat.getSizeY();
        Vector<Para> vect = new Vector<>();
        if (x - 1 >= 0 && y - 1 > 0 && swiatOrganizmow[x - 1][y - 1] instanceof Zwierze) {
            vect.add(new Para(x-1, y-1));
        }
        if (x - 1 >= 0 && swiatOrganizmow[x - 1][y] instanceof Zwierze) {
            vect.add(new Para(x-1,y));
        }
        if (x - 1 >= 0 && y + 1 < sizeY && swiatOrganizmow[x - 1][y + 1] instanceof Zwierze) {
            vect.add(new Para(x-1,y +1));
        }
        if (y - 1 >= 0 && swiatOrganizmow[x][y - 1] instanceof Zwierze) {
            vect.add(new Para(x,y - 1));
        }
        if (y + 1 < sizeY && swiatOrganizmow[x][y + 1] instanceof Zwierze) {
            vect.add(new Para(x,y +1));
        }
        if (x + 1 < sizeX && y - 1 >= 0 && swiatOrganizmow[x + 1][y - 1] instanceof Zwierze) {
            vect.add(new Para(x+1,y - 1));
        }
        if (x + 1 < sizeX && swiatOrganizmow[x + 1][y] instanceof Zwierze) {
            vect.add(new Para(x+1,y));
        }
        if (x + 1 < sizeX && y + 1 < sizeY && swiatOrganizmow[x + 1][y + 1] instanceof Zwierze) {
            vect.add(new Para(x+1,y + 1));
        }
        return vect;
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        String status;
        Vector<String> kom = swiat.getKomentarz();
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        status = atakujacy.Wypisz() + " ZJADL " + Wypisz() + " I ZGINAL";
        swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = null;
        atakujacy.setCzyZyje(false);
        kom.add(status);
    }

    @Override
    public String Wypisz() {
        return "Barszcz sosnowskiego" + getKoncowka();
    }
}
