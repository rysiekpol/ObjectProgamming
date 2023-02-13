from Obsluga.para import Para
from Zwierzeta.zwierze import Zwierze
import random


class Antylopa(Zwierze):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Antylopa, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 4
        self.inicjatywa = 4
        self.kolor = "red"
        self.oznaczenie = "A"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def akcja(self):
        self.przygotujOrganizm()
        ruch = random.randint(0, 3)
        status = ""
        kom = self.swiat.getKomentarz()
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        if ruch == 0:
            if self.polozenieX + 2 < self.swiat.getSizeX():
                if swiatOrganizmow[self.polozenieX + 2][self.polozenieY] is not None:
                    swiatOrganizmow[self.polozenieX + 2][self.polozenieY].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYLA SIE NA POZYCJE " + str(
                        self.polozenieX + 2) + ' ' + str(
                        self.polozenieY)
                    self.polozenieX += 2
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 1:
            if self.polozenieX > 0:
                if swiatOrganizmow[self.polozenieX - 2][self.polozenieY] is not None:
                    swiatOrganizmow[self.polozenieX - 2][self.polozenieY].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYLA SIE NA POZYCJE " + str(
                        self.polozenieX - 2) + ' ' + str(
                        self.polozenieY)
                    self.polozenieX -= 2
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 2:
            if self.polozenieY + 2 < self.swiat.getSizeY():
                if swiatOrganizmow[self.polozenieX][self.polozenieY + 2] is not None:
                    swiatOrganizmow[self.polozenieX][self.polozenieY + 2].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYLA SIE NA POZYCJE " + str(
                        self.polozenieX) + ' ' + str(
                        self.polozenieY + 2)
                    self.polozenieY += 2
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 3:
            if self.polozenieY > 0:
                if swiatOrganizmow[self.polozenieX][self.polozenieY - 2] is not None:
                    swiatOrganizmow[self.polozenieX][self.polozenieY - 2].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYLA SIE NA POZYCJE " + str(
                        self.polozenieX) + ' ' + str(
                        self.polozenieY - 2)
                    self.polozenieY -= 2
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        if status != "":
            kom.append(status)

    def kolizja(self, atakujacy):
        status = ""
        kom = self.swiat.getKomentarz()
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        if self.kolor != atakujacy.getKolor():
            szansa = random.randint(0, 100)
            if szansa > 50:
                para = self.swiat.znajdzPuste(self.polozenieX, self.polozenieY)
                if len(para) > 0:
                    ktore = random.randint(0, len(para)-1)
                    swiatOrganizmow[para[ktore].getX()][para[ktore].getY()] = self
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    self.polozenieX = para[ktore].getX()
                    self.polozenieY = para[ktore].getY()
                else:
                    status = self.Wypisz() + " NIE UCIEKLA Z POWODU BRAKU MIEJSCA"
                    super().kolizja(atakujacy)
            else:
                status = self.Wypisz() + " NIE UCIEKLA Z POWODU MALEJ SZANSY"
                kom.append(status)
                super().kolizja(atakujacy)
        else:
            super().kolizja(atakujacy)

    def Wypisz(self):
        return "Antylopa" + self.koncowka()
