import random
from Obsluga.generatorOrganizmow import generatorOrganizmow
from Obsluga.para import Para
from organizm import Organizm


class Roslina(Organizm):
    def Wypisz(self):
        pass

    def akcja(self):
        if self.czyMozeRozmnazac > 10:
            self.czyMozeRozmnazac = 0
            status = ""
            kom = self.swiat.getKomentarz()
            puste = self.swiat.znajdzPuste(self.polozenieX, self.polozenieY)
            swiatOrganizmow = self.swiat.getSwiatOrganizmow()
            o = generatorOrganizmow().klonowanie(self, self.swiat)
            if len(puste):
                losuj = random.randint(0,len(puste)-1)
                o.setPolozenieX(puste[losuj].getX())
                o.setPolozenieY(puste[losuj].getY())
                swiatOrganizmow[puste[losuj].getX()][puste[losuj].getY()] = o
                self.swiat.dodajOrganizm(o, True)
                status = self.Wypisz() + " ROZPYLIL SIE NA POZYCJE " + str(puste[losuj].getX()) + ":" + str(puste[losuj].getY())
            else:
                status = self.Wypisz() + " NIE ROZPYLIL SIE Z POWODU BRAKU MIEJSCA"
            kom.append(status)

    def kolizja(self, atakujacy):
        status = ""
        kom = self.swiat.getKomentarz()
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        status = self.Wypisz() + " ZOSTAL ZJEDZONY PRZEZ " + atakujacy.Wypisz()
        swiatOrganizmow[self.polozenieX][self.polozenieY] = atakujacy
        swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = None
        atakujacy.setPolozenieX(self.polozenieX)
        atakujacy.setPolozenieY(self.polozenieY)
        self.czyZyje = False
        kom.append(status)

