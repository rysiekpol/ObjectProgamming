package projekt.rosliny;

import projekt.Organizm;
import projekt.Wypisanie;
import projekt.obsluga.GeneratorOrganizmow;
import projekt.obsluga.Para;

import java.util.Random;
import java.util.Vector;

public class Roslina extends Organizm implements Wypisanie {

    @Override
    public String Wypisz() {
        return null;
    }

    @Override
    public void akcja() {
        if (czyMozeRozmnazac > 10) {
            czyMozeRozmnazac = 0;
            String status;
            Vector<String> kom = swiat.getKomentarz();
            Vector<Para> puste = swiat.znajdzPuste(polozenieX, polozenieY);
            Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
            Organizm o = new GeneratorOrganizmow().klonowanie(this, swiat);
            if (puste.size() > 0) {
                Random random = new Random();
                int losuj = random.nextInt(puste.size());
                o.setPolozenieX(puste.get(losuj).getL());
                o.setPolozenieY(puste.get(losuj).getR());
                swiatOrganizmow[puste.get(losuj).getL()][puste.get(losuj).getR()] = o;
                swiat.dodajOrganizm(o, true);
                status = Wypisz() + " ROZPYLIL SIE NA POZYCJE " + String.valueOf(puste.get(losuj).getL()) + ":" + String.valueOf(puste.get(losuj).getR());
            } else {
                status = Wypisz() + " NIE ROZPYLIL SIE Z POWODU BRAKU MIEJSCA";
            }
            kom.add(status);
        }
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        String status;
        Vector<String> kom = swiat.getKomentarz();
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        status = Wypisz() + " ZOSTAL ZJEDZONY PRZEZ " + atakujacy.Wypisz();
        swiatOrganizmow[polozenieX][polozenieY] = atakujacy;
        swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = null;
        atakujacy.setPolozenieX(polozenieX);
        atakujacy.setPolozenieY(polozenieY);
        czyZyje = false;
        kom.add(status);
    }
}
