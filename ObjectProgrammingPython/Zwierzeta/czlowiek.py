from Obsluga.para import Para
from Zwierzeta.zwierze import Zwierze
from organizm import Organizm


class Czlowiek(Zwierze):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Czlowiek, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 5
        self.inicjatywa = 4
        self.kolor = "yellow"
        self.oznaczenie = "C"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def akcja(self):
        self.przygotujOrganizm()
        self.ustawRuch()
        status = ""
        kom = self.swiat.getKomentarz()
        if self.swiat.getCzyUmiejetnosc():
            self.swiat.setKtoraTuraUmiejetnosci(self.swiat.getKtoraTuraUmiejetnosci() + 1)
            if self.swiat.getKtoraTuraUmiejetnosci() < 5:
                self.wywolajUmiejetnosc()
                status = self.Wypisz() + " WYWOLAL UMIEJETNOSC CALOPALENIE, JEST TO " + \
                         str(self.swiat.getKtoraTuraUmiejetnosci()) + " TURA UMIEJETNOSCI"
        else:
            status = self.Wypisz() + " UMIEJETNOSC SKONCZYLA SIE"
            self.swiat.setCzyUmiejetnosc(False)
            self.swiat.setKtoraTuraUmiejetnosci(0)
        if status != "":
            kom.append(status)

    def ustawRuch(self):
        self.przygotujOrganizm()
        ruch = self.swiat.getOstatniKlawisz()
        status = ""
        kom = self.swiat.getKomentarz()
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        if ruch == 1:
            if self.polozenieX + 1 < self.swiat.getSizeX():
                if swiatOrganizmow[self.polozenieX + 1][self.polozenieY] is not None:
                    swiatOrganizmow[self.polozenieX + 1][self.polozenieY].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX + 1) + ' ' + str(
                        self.polozenieY)
                    self.polozenieX += 1
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 0:
            if self.polozenieX > 0:
                if swiatOrganizmow[self.polozenieX - 1][self.polozenieY] is not None:
                    swiatOrganizmow[self.polozenieX - 1][self.polozenieY].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX - 1) + ' ' + str(
                        self.polozenieY)
                    self.polozenieX -= 1
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 3:
            if self.polozenieY + 1 < self.swiat.getSizeY():
                if swiatOrganizmow[self.polozenieX][self.polozenieY + 1] is not None:
                    swiatOrganizmow[self.polozenieX][self.polozenieY + 1].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX) + ' ' + str(
                        self.polozenieY + 1)
                    self.polozenieY += 1
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 2:
            if self.polozenieY > 0:
                if swiatOrganizmow[self.polozenieX][self.polozenieY - 1] is not None:
                    swiatOrganizmow[self.polozenieX][self.polozenieY - 1].kolizja(self)
                else:
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                    status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE " + str(self.polozenieX) + ' ' + str(
                        self.polozenieY - 1)
                    self.polozenieY -= 1
                    swiatOrganizmow[self.polozenieX][self.polozenieY] = self
        elif ruch == 4:
            self.swiat.setCzyUmiejetnosc(True)
        if status != "":
            kom.append(status)

    def wywolajUmiejetnosc(self):
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        pelnePola = self.znajdzPelne(self.polozenieX, self.polozenieY)
        status = ""
        kom = self.swiat.getKomentarz()
        if len(pelnePola) > 0:
            for wsp in pelnePola:
                status = self.Wypisz() + " ZABIL ZA POMOCA UMIEJETNOSCI " + swiatOrganizmow[wsp.getX()][
                    wsp.getY()].Wypisz()
                swiatOrganizmow[wsp.getX()][wsp.getY()].setCzyZyje(False)
                swiatOrganizmow[wsp.getX()][wsp.getY()] = None
                kom.append(status)

    def znajdzPelne(self, x, y):
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        sizeX = self.swiat.getSizeX()
        sizeY = self.swiat.getSizeY()
        listaPustych = []
        if x - 1 >= 0 and y - 1 >= 0 and swiatOrganizmow[x - 1][y - 1] is not None:
            listaPustych.append(Para(x - 1, y - 1))
        if x - 1 >= 0 and swiatOrganizmow[x - 1][y] is not None:
            listaPustych.append(Para(x - 1, y))
        if x - 1 >= 0 and y + 1 < sizeY and swiatOrganizmow[x - 1][y + 1] is not None:
            listaPustych.append(Para(x - 1, y + 1))
        if y - 1 >= 0 and swiatOrganizmow[x][y - 1] is not None:
            listaPustych.append(Para(x, y - 1))
        if y + 1 < sizeY and swiatOrganizmow[x][y + 1] is not None:
            listaPustych.append(Para(x, y + 1))
        if x + 1 < sizeX and y - 1 >= 0 and swiatOrganizmow[x + 1][y - 1] is not None:
            listaPustych.append(Para(x + 1, y - 1))
        if x + 1 < sizeX and swiatOrganizmow[x + 1][y] is not None:
            listaPustych.append(Para(x + 1, y))
        if x + 1 < sizeX and y + 1 < sizeY and swiatOrganizmow[x + 1][y + 1] is not None:
            listaPustych.append(Para(x + 1, y + 1))
        return listaPustych

    def Wypisz(self):
        return "Czlowiek" + self.koncowka()
