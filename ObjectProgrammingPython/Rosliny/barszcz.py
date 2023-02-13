import random

from Obsluga.para import Para
from Rosliny.roslina import Roslina
from Zwierzeta.cyberOwca import CyberOwca
from Zwierzeta.zwierze import Zwierze


class Barszcz(Roslina):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Barszcz, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 10
        self.inicjatywa = 0
        self.kolor = "#8C008C"
        self.oznaczenie = "b"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def akcja(self):
        self.przygotujOrganizm()
        losuj = random.randint(0,100)
        if losuj > 98:
            super().akcja()
        status = ""
        kom = self.swiat.getKomentarz()
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        zwierzeta = self.szukajZwierzecia(self.polozenieX, self.polozenieY)
        if len(zwierzeta):
            for wsp in zwierzeta:
                status = self.Wypisz() + " ZABIL " + swiatOrganizmow[wsp.getX()][wsp.getY()].Wypisz()
                swiatOrganizmow[wsp.getX()][wsp.getY()].setCzyZyje(False)
                swiatOrganizmow[wsp.getX()][wsp.getY()] = None
                kom.append(status)


    def kolizja(self, atakujacy):
        if type(atakujacy) is not CyberOwca:
            status = ""
            kom = self.swiat.getKomentarz()
            swiatOrganizmow = self.swiat.getSwiatOrganizmow()
            status = atakujacy.Wypisz() + " ZJADL " + self.Wypisz() + " I ZGINAL"
            swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = None
            atakujacy.setCzyZyje(False)
            kom.append(status)
        else:
            status = ""
            kom = self.swiat.getKomentarz()
            swiatOrganizmow = self.swiat.getSwiatOrganizmow()
            status = atakujacy.Wypisz() + " ZJADL " + self.Wypisz() + " I BYL ODPORNY"
            swiatOrganizmow[self.polozenieX][self.polozenieY] = swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()]
            swiatOrganizmow[atakujacy.getPolozenieX()][atakujacy.getPolozenieY()] = None
            atakujacy.setPolozenieX(self.polozenieX)
            atakujacy.setPolozenieY(self.polozenieY)
            self.czyZyje = False
            kom.append(status)

    def szukajZwierzecia(self, x, y):
        swiatOrganizmow = self.swiat.getSwiatOrganizmow()
        sizeX = self.swiat.getSizeX()
        sizeY = self.swiat.getSizeY()
        listaPustych = []
        if x - 1 >= 0 and y - 1 >= 0 and type(swiatOrganizmow[x - 1][y - 1]) is Zwierze and type(swiatOrganizmow[x - 1][y - 1]) is not CyberOwca:
            listaPustych.append(Para(x - 1, y - 1))
        if x - 1 >= 0 and type(swiatOrganizmow[x - 1][y]) is Zwierze and type(swiatOrganizmow[x - 1][y - 1]) is not CyberOwca:
            listaPustych.append(Para(x - 1, y))
        if x - 1 >= 0 and y + 1 < sizeY and type(swiatOrganizmow[x - 1][y + 1]) is Zwierze and type(swiatOrganizmow[x - 1][y - 1]) is not CyberOwca:
            listaPustych.append(Para(x - 1, y + 1))
        if y - 1 >= 0 and type(swiatOrganizmow[x][y - 1]) is Zwierze and type(swiatOrganizmow[x - 1][y - 1]) is not CyberOwca:
            listaPustych.append(Para(x, y - 1))
        if y + 1 < sizeY and type(swiatOrganizmow[x][y + 1]) is Zwierze and type(swiatOrganizmow[x - 1][y - 1]) is not CyberOwca:
            listaPustych.append(Para(x, y + 1))
        if x + 1 < sizeX and y - 1 >= 0 and type(swiatOrganizmow[x + 1][y - 1]) is Zwierze and type(swiatOrganizmow[x - 1][y - 1]) is not CyberOwca:
            listaPustych.append(Para(x + 1, y - 1))
        if x + 1 < sizeX and type(swiatOrganizmow[x + 1][y]) is Zwierze and type(swiatOrganizmow[x - 1][y - 1]) is not CyberOwca:
            listaPustych.append(Para(x + 1, y))
        if x + 1 < sizeX and y + 1 < sizeY and type(swiatOrganizmow[x + 1][y + 1]) is Zwierze and type(swiatOrganizmow[x - 1][y - 1]) is not CyberOwca:
            listaPustych.append(Para(x + 1, y + 1))
        return listaPustych

    def Wypisz(self):
        return "Guarana" + self.koncowka()



