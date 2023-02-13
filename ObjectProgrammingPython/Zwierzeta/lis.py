import random

from Obsluga.para import Para
from Zwierzeta.zwierze import Zwierze


class Lis(Zwierze):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Lis, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 3
        self.inicjatywa = 7
        self.kolor = "orange"
        self.oznaczenie = "L"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def akcja(self):
        self.przygotujOrganizm()
        status = ""
        kom = self.swiat.getKomentarz()
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        pola = self.szukajPola(self.polozenieX, self.polozenieY)
        if len(pola):
            losuj = random.randint(0,len(pola)-1)
            if swiatOrganizmow[pola[losuj].getX()][pola[losuj].getY()] is None:
                swiatOrganizmow[self.polozenieX][self.polozenieY] = None
                status = self.Wypisz() + " PORUSZYL SIE NA POZYCJE "
                self.polozenieX = pola[losuj].getX()
                self.polozenieY = pola[losuj].getY()
                status += str(self.polozenieX) + ":" + str(self.polozenieY)
                swiatOrganizmow[self.polozenieX][self.polozenieY] = self
            else:
                self.kolizja(swiatOrganizmow[pola[losuj].getX()][pola[losuj].getY()])
        else:
            status = self.Wypisz() + " NIE PORUSZYL SIE Z POWODU BRAKU MIEJSCA"
        kom.append(status)

    def szukajPola(self, x, y):
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        sizeX = self.swiat.getSizeX()
        sizeY = self.swiat.getSizeY()
        listaPustych = []
        if x - 1 >= 0 and y - 1 >= 0 and (swiatOrganizmow[x - 1][y - 1] is None or swiatOrganizmow[x - 1][y - 1].getSila()<self.sila):
            listaPustych.append(Para(x - 1, y - 1))
        if x - 1 >= 0 and (swiatOrganizmow[x - 1][y] is None or swiatOrganizmow[x - 1][y].getSila()<self.sila):
            listaPustych.append(Para(x - 1, y))
        if x - 1 >= 0 and y + 1 < sizeY and (swiatOrganizmow[x - 1][y + 1] is None or swiatOrganizmow[x - 1][y + 1].getSila()<self.sila):
            listaPustych.append(Para(x - 1, y + 1))
        if y - 1 >= 0 and (swiatOrganizmow[x][y - 1] is None or swiatOrganizmow[x][y - 1].getSila()<self.sila):
            listaPustych.append(Para(x, y - 1))
        if y + 1 < sizeY and (swiatOrganizmow[x][y + 1] is None or swiatOrganizmow[x][y + 1].getSila()<self.sila):
            listaPustych.append(Para(x, y + 1))
        if x + 1 < sizeX and y - 1 >= 0 and (swiatOrganizmow[x + 1][y - 1] is None or swiatOrganizmow[x + 1][y - 1].getSila()<self.sila):
            listaPustych.append(Para(x + 1, y - 1))
        if x + 1 < sizeX and (swiatOrganizmow[x + 1][y] is None or swiatOrganizmow[x + 1][y].getSila()<self.sila):
            listaPustych.append(Para(x + 1, y))
        if x + 1 < sizeX and y + 1 < sizeY and (swiatOrganizmow[x + 1][y + 1] is None or swiatOrganizmow[x + 1][y + 1].getSila()<self.sila):
            listaPustych.append(Para(x + 1, y + 1))
        return listaPustych

    def Wypisz(self):
        return "Lis" + self.koncowka()



