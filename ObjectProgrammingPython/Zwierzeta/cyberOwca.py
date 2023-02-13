import math

from Zwierzeta.zwierze import Zwierze
import Rosliny

class CyberOwca(Zwierze):
    GORA = 0
    LEWO = 1
    DOL = 2
    PRAWO = 3

    def __init__(self, Swiat, x, y, czyZyje):
        super(CyberOwca, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 11
        self.inicjatywa = 4
        self.kolor = "gray"
        self.oznaczenie = "Y"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def akcja(self):
        kierunek = self.znajdzNajblizszyBarszcz()
        if kierunek == -1:
            super(CyberOwca, self).akcja()
        else:
            status = ""
            kom = self.swiat.getKomentarz()
            swiatOrganizmow = self.swiat.getSwiatOrganizmow()
            if kierunek == self.__class__.DOL:
                if self.polozenieX + 1 < self.swiat.getSizeX():
                    if swiatOrganizmow[self.polozenieX + 1][self.polozenieY] is not None:
                        swiatOrganizmow[self.polozenieX + 1][self.polozenieY].kolizja(self)
                    else:
                        swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                        status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX + 1) + ' ' + str(
                            self.polozenieY)
                        self.polozenieX += 1
                        swiatOrganizmow[self.polozenieX][self.polozenieY] = self
            elif kierunek == self.__class__.GORA:
                if self.polozenieX > 0:
                    if swiatOrganizmow[self.polozenieX - 1][self.polozenieY] is not None:
                        swiatOrganizmow[self.polozenieX - 1][self.polozenieY].kolizja(self)
                    else:
                        swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                        status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX - 1) + ' ' + str(
                            self.polozenieY)
                        self.polozenieX -= 1
                        swiatOrganizmow[self.polozenieX][self.polozenieY] = self
            elif kierunek == self.__class__.PRAWO:
                if self.polozenieY + 1 < self.swiat.getSizeY():
                    if swiatOrganizmow[self.polozenieX][self.polozenieY + 1] is not None:
                        swiatOrganizmow[self.polozenieX][self.polozenieY + 1].kolizja(self)
                    else:
                        swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                        status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX) + ' ' + str(
                            self.polozenieY + 1)
                        self.polozenieY += 1
                        swiatOrganizmow[self.polozenieX][self.polozenieY] = self
            elif kierunek == self.__class__.LEWO:
                if self.polozenieY > 0:
                    if swiatOrganizmow[self.polozenieX][self.polozenieY - 1] is not None:
                        swiatOrganizmow[self.polozenieX][self.polozenieY - 1].kolizja(self)
                    else:
                        swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                        status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX) + ' ' + str(
                            self.polozenieY - 1)
                        self.polozenieY -= 1
                        swiatOrganizmow[self.polozenieX][self.polozenieY] = self
            if status != "":
                kom.append(status)

    def znajdzNajblizszyBarszcz(self):
        organizmy = self.swiat.getOrganizmy()
        odleglosc = abs(math.sqrt((self.swiat.getSizeX() ** 2 + self.swiat.getSizeY() ** 2)))
        czyZnalezione = False
        polBarszczX = 0
        polBarszczY = 0
        for org in organizmy:
            if type(org) == Rosliny.barszcz.Barszcz and org.getCzyZyje():
                wartosc = abs(math.sqrt(
                    ((org.getPolozenieX() - self.polozenieX) ** 2 + (org.getPolozenieY() - self.polozenieY) ** 2)))
                if wartosc < odleglosc:
                    odleglosc = wartosc
                    polBarszczX = org.getPolozenieX()
                    polBarszczY = org.getPolozenieY()
                    czyZnalezione = True
        if czyZnalezione:
            if abs(self.polozenieX - polBarszczX) > abs(self.polozenieY - polBarszczY):
                if self.polozenieX > polBarszczX:
                    return self.__class__.GORA
                else:
                    return self.__class__.DOL
            else:
                if self.polozenieY > polBarszczY:
                    return self.__class__.LEWO
                else:
                    return self.__class__.PRAWO
        return -1

    def Wypisz(self):
        return "Cyber owca" + self.koncowka()
