import random
from Obsluga.generatorOrganizmow import generatorOrganizmow
from Obsluga.para import Para
from organizm import Organizm


class Zwierze(Organizm):
    def Wypisz(self):
        pass

    def akcja(self):
        self.przygotujOrganizm()
        ruch = random.randint(0, 3)
        status = ""
        kom = self.swiat.getKomentarz()
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        if ruch == 0:
            if self.polozenieX + 1 < self.swiat.getSizeX():
                if swiatOrganizmow[self.polozenieX + 1][self.polozenieY] is not None:
                    swiatOrganizmow[self.polozenieX + 1][self.polozenieY].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX + 1) + ' ' + str(self.polozenieY)
                    self.polozenieX += 1
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 1:
            if self.polozenieX > 0:
                if swiatOrganizmow[self.polozenieX - 1][self.polozenieY] is not None:
                    swiatOrganizmow[self.polozenieX - 1][self.polozenieY].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX - 1) + ' ' + str(self.polozenieY)
                    self.polozenieX -= 1
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 2:
            if self.polozenieY + 1 < self.swiat.getSizeY():
                if swiatOrganizmow[self.polozenieX][self.polozenieY + 1] is not None:
                    swiatOrganizmow[self.polozenieX][self.polozenieY + 1].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX) + ' ' + str(self.polozenieY + 1)
                    self.polozenieY += 1
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 3:
            if self.polozenieY > 0:
                if swiatOrganizmow[self.polozenieX][self.polozenieY - 1] is not None:
                    swiatOrganizmow[self.polozenieX][self.polozenieY - 1].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX) + ' ' + str(self.polozenieY - 1)
                    self.polozenieY -= 1
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        if status != "":
            kom.append(status)

    def kolizja(self, atakujacy):
        status = ""
        kom = self.swiat.getKomentarz()
        if self.kolor == atakujacy.getKolor():
            if atakujacy.getCzyMozeRozmnazac() > self.__class__.MIN_ZYCIA_ABY_URODZIC and self.getCzyMozeRozmnazac() > self.__class__.MIN_ZYCIA_ABY_URODZIC:
                self.czyMozeRozmnazac = 0
                atakujacy.setCzyMozeRozmnazac(0)
                o = generatorOrganizmow().klonowanie(self, self.swiat)
                pustePola = self.swiat.znajdzPuste(self.polozenieX, self.polozenieY)
                if len(pustePola):
                    ruch = random.randint(0, len(pustePola) - 1)
                    o.setPolozenieX(pustePola[ruch].getX())
                    o.setPolozenieY(pustePola[ruch].getY())
                    self.swiat.dodajOrganizm(o, True)
                    status += o.Wypisz()
                    self.swiat.getSwiatOrganizmow()[pustePola[ruch].getX()][pustePola[ruch].getY()] = o
                else:
                    status = "BRAK MIEJSCA NA ROZMNOZENIE SIE " + self.Wypisz() + " i " + atakujacy.Wypisz()
        else:
            swiatOrganizmow = self.swiat.getSwiatOrganizmow()
            if atakujacy.getSila() >= self.sila:
                status = self.Wypisz() + " ZOSTAL ZABITY PRZEZ " + atakujacy.Wypisz()
                swiatOrganizmow[self.polozenieX][self.polozenieY] = atakujacy
                swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = None
                atakujacy.setPolozenieX(self.polozenieX)
                atakujacy.setPolozenieY(self.polozenieY)
                self.czyZyje = False
            else:
                status = atakujacy.Wypisz() + " ZOSTAL ZABITY PRZEZ " + self.Wypisz()
                swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = None
                atakujacy.setCzyZyje(False)
        kom.append(status)