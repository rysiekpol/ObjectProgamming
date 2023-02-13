package projekt.zwierzeta;

import projekt.Organizm;
import projekt.Wypisanie;
import projekt.obsluga.Para;
import projekt.swiat.Swiat;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Lis extends Zwierze implements Wypisanie {
    public Lis(Swiat swiat, int x, int y, boolean czyZyje){
        polozenieX = x;
        polozenieY = y;
        sila = 3;
        inicjatywa = 7;
        kolor = new Color(150, 58, 0);
        oznaczenie = 'L';
        this.swiat = swiat;
        this.czyZyje = czyZyje;
        this.czyMozeRozmnazac = 0;
        wiek = 0;
    }

    public void akcja(){
        przygotujOrganizm();
        String status = "";
        Vector<String> kom = swiat.getKomentarz();
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        Vector<Para> pola = szukajPola(polozenieX, polozenieY);
        if (pola.size() > 0) {
            Random random = new Random();
            int losuj = random.nextInt(pola.size());
            if (swiatOrganizmow[pola.get(losuj).getL()][pola.get(losuj).getR()] == null) {
                swiatOrganizmow[polozenieX][polozenieY] = null;
                status = Wypisz() + " PORUSZYL SIE NA POZYCJE ";
                polozenieX = pola.get(losuj).getL();
                polozenieY = pola.get(losuj).getR();
                status +=  String.valueOf(polozenieX)+ ":" + String.valueOf(polozenieY);
                swiatOrganizmow[polozenieX][polozenieY] = this;
            }
            else if(swiatOrganizmow[pola.get(losuj).getL()][pola.get(losuj).getR()] != null){
                kolizja(swiatOrganizmow[pola.get(losuj).getL()][pola.get(losuj).getR()]);
            }
        }
        else {
            status = Wypisz() + " NIE PORUSZYL SIE Z POWODU BRAKU MIEJSCA";
        }
        kom.add(status);
    }

    @Override
    public String Wypisz() {
        return "Lis" + getKoncowka();
    }

    Vector<Para> szukajPola(int x, int y) {
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        Vector<Para> vect =  new Vector<>();
        if (x - 1 > 0 && y - 1 > 0 && (swiatOrganizmow[x - 1][y - 1] == null || swiatOrganizmow[x - 1][y - 1].getSila()<sila)) {
            vect.add(new Para(x - 1, y - 1));
        }
        if (x - 1 > 0 && (swiatOrganizmow[x - 1][y] == null || swiatOrganizmow[x - 1][y].getSila() < sila)) {
            vect.add(new Para(x - 1, y));
        }
        if (x - 1 > 0 && y + 1 < swiat.getSizeY() && (swiatOrganizmow[x - 1][y + 1] == null || swiatOrganizmow[x - 1][y + 1].getSila() < sila)) {
            vect.add(new Para(x - 1, y + 1));
        }
        if (y - 1 > 0 && (swiatOrganizmow[x][y - 1] == null || swiatOrganizmow[x][y - 1].getSila() < sila)) {
            vect.add(new Para(x, y - 1));
        }
        if (y + 1 < swiat.getSizeY() && (swiatOrganizmow[x][y + 1] == null || swiatOrganizmow[x][y + 1].getSila() < sila)) {
            vect.add(new Para(x, y + 1));
        }
        if (x + 1 < swiat.getSizeX() && y - 1 > 0 && (swiatOrganizmow[x + 1][y - 1] == null || swiatOrganizmow[x + 1][y - 1].getSila() < sila)) {
            vect.add(new Para(x + 1, y - 1));
        }
        if (x + 1 < swiat.getSizeX() && (swiatOrganizmow[x + 1][y] == null || swiatOrganizmow[x + 1][y].getSila() < sila)) {
            vect.add(new Para(x + 1, y));
        }
        if (x + 1 < swiat.getSizeX() && y + 1 < swiat.getSizeY() && (swiatOrganizmow[x + 1][y + 1] == null || swiatOrganizmow[x + 1][y + 1].getSila() < sila)) {
            vect.add(new Para(x + 1, y + 1));
        }
        return vect;
    }
}
