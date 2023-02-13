import sys
from Obsluga.para import Para
from Rosliny.barszcz import Barszcz
from Rosliny.guarana import Guarana
from Rosliny.jagody import Jagody
from Rosliny.mlecz import Mlecz
from Rosliny.trawa import Trawa
from Zwierzeta.antylopa import Antylopa
from Zwierzeta.cyberOwca import CyberOwca
from Zwierzeta.czlowiek import Czlowiek
from Zwierzeta.lis import Lis
from Zwierzeta.owca import Owca
from Zwierzeta.wilk import Wilk
import random

from Zwierzeta.zolw import Zolw


class Swiat:
    MAX_ORAGNIZM = 10

    def __init__(self, x=20, y=20, ktoraTura=0, ktoraTuraUmiejetnosci=0, czyUmiejetnosc=False):
        self.__x = x
        self.__y = y
        self.__ktoraTura = ktoraTura
        self.__ktoraTuraUmiejetnosci = ktoraTuraUmiejetnosci
        self.__czyUmiejetnosc = czyUmiejetnosc
        self.__ostatniKlawisz = -1
        self.__organizmy = []
        self.__komentarz = []
        self.__swiatOrganizmow = [[None]*x for i in range(y)]

    def getSizeX(self):
        return self.__x

    def getSizeY(self):
        return self.__y

    def setSizeX(self, x):
        self.__x = x

    def setSizeY(self, y):
        self.__y = y

    def getOrganizmy(self):
        return self.__organizmy

    def getSwiatOrganizmow(self):
        return self.__swiatOrganizmow

    def getOstatniKlawisz(self):
        return self.__ostatniKlawisz

    def setOstatniKlawisz(self, klawisz):
        self.__ostatniKlawisz = klawisz

    def getCzyUmiejetnosc(self):
        return self.__czyUmiejetnosc

    def setKtoraTuraUmiejetnosci(self, ktora):
        self.__ktoraTuraUmiejetnosci = ktora

    def getKtoraTura(self):
        return self.__ktoraTura

    def setCzyUmiejetnosc(self, czy):
        self.__czyUmiejetnosc = czy

    def getKtoraTuraUmiejetnosci(self):
        return self.__ktoraTuraUmiejetnosci

    def dodajOrganizm(self, organizm, czyKlonowanie):
        self.__organizmy.append(organizm)
        if czyKlonowanie == False:
            self.sortowanieList()

    def sortowanieList(self):
        self.__organizmy = sorted(self.__organizmy, key=lambda organizm: (organizm.getInicjatywa(), organizm.getWiek()), reverse=True)

    def ustawPozycje(self, organizm):
        status = ""
        x = random.randint(0, self.__x-1)
        y = random.randint(0, self.__y-1)
        if self.__swiatOrganizmow[x][y] is None:
            organizm.setPolozenieX(x)
            organizm.setPolozenieY(y)
            self.__swiatOrganizmow[x][y] = organizm
            self.dodajOrganizm(organizm, False)

    def getKomentarz(self):
        return self.__komentarz

    def wykonajTure(self):
        self.__ktoraTura+=1
        self.__komentarz.clear()
        self.wykonajAkcje()
        self.usunMartweOrganizmy()

    def wykonajAkcje(self):
        for organizm in self.__organizmy:
            if organizm.getCzyZyje():
                organizm.akcja()

    def usunMartweOrganizmy(self):
        doUsuniecia = []
        for organizm in self.__organizmy:
            if organizm.getCzyZyje()==False:
                self.__organizmy.remove(organizm)
        self.sortowanieList()

    def znajdzPuste(self, x, y):
        listaPustych = []
        if x - 1 >= 0 and y - 1 >= 0 and self.__swiatOrganizmow[x - 1][y - 1] is None:
            listaPustych.append(Para(x-1, y-1))
        if x - 1 >= 0 and self.__swiatOrganizmow[x - 1][y] is None:
            listaPustych.append(Para(x-1, y))
        if x - 1 >= 0 and y + 1 < self.__y and self.__swiatOrganizmow[x - 1][y + 1] is None:
            listaPustych.append(Para(x-1, y +1))
        if y - 1 >= 0 and self.__swiatOrganizmow[x][y - 1] is None:
            listaPustych.append(Para(x, y - 1))
        if y + 1 < self.__y and self.__swiatOrganizmow[x][y + 1] is None:
            listaPustych.append(Para(x, y +1))
        if x + 1 < self.__x and y - 1 >= 0 and self.__swiatOrganizmow[x + 1][y - 1] is None:
            listaPustych.append(Para(x+1, y - 1))
        if x + 1 < self.__x and self.__swiatOrganizmow[x + 1][y] is None:
            listaPustych.append(Para(x+1, y))
        if x + 1 < self.__x and y + 1 < self.__y and self.__swiatOrganizmow[x + 1][y + 1] is None:
            listaPustych.append(Para(x+1, y + 1))
        return listaPustych

    def nowySwiat(self, ile):
        for i in range(self.__x):
            for j in range(self.__y):
                self.__swiatOrganizmow[i][j] = None

        if ile > self.__x * self.__y:
            print("Za duzo organizmow")
            sys.exit()

        self.ustawPozycje(Czlowiek(self, -100, -100, True))
        for i in range(ile-1):
            ktory = random.randint(0,self.__class__.MAX_ORAGNIZM)
            if ktory == 0:
                self.ustawPozycje(Wilk(self, -100, -100, True))
            elif ktory == 1:
                self.ustawPozycje(Owca(self, -100, -100, True))
            elif ktory == 2:
                self.ustawPozycje(Antylopa(self, -100, -100, True))
            elif ktory == 3:
                self.ustawPozycje(Lis(self, -100, -100, True))
            elif ktory == 4:
                self.ustawPozycje(Zolw(self, -100, -100, True))
            elif ktory == 5:
                self.ustawPozycje(Trawa(self, -100, -100, True))
            elif ktory == 6:
                self.ustawPozycje(Mlecz(self, -100, -100, True))
            elif ktory == 7:
                self.ustawPozycje(Barszcz(self, -100, -100, True))
            elif ktory == 8:
                self.ustawPozycje(Guarana(self, -100, -100, True))
            elif ktory == 9:
                self.ustawPozycje(Jagody(self, -100, -100, True))
            elif ktory == 10:
                self.ustawPozycje(CyberOwca(self, -100, -100, True))

    def zapiszSwiat(self, nazwa):
        nazwa += ".txt"
        file = open(nazwa, "w")
        boolToInt = int(self.__czyUmiejetnosc == True)
        file.write(str(self.__x) + " " + str(self.__y) + " " + str(self.__ktoraTura) + " " + str(self.__ktoraTuraUmiejetnosci) + " " + str(boolToInt) + "\n")
        file.write(str(len(self.__organizmy)) + "\n")
        for organizm in self.__organizmy:
            file.write(str(organizm.getOznaczenie()) + " " + str(organizm.getPolozenieX()) + " " + str(organizm.getPolozenieY())
                          + " " + str(organizm.getSila()) + " " + str(organizm.getInicjatywa()) + " " + str(organizm.getCzyMozeRozmnazac())
                          + " " + str(organizm.getWiek()) + "\n")
        file.close()

    def wczytajSwiat(self, wczytanyPlik):
        ile = int(wczytanyPlik[1])
        for i in range(ile):
            wartosci = wczytanyPlik[i+2].split()
            ktory = wartosci[0]
            x = int(wartosci[1])
            y = int(wartosci[2])
            sila = int(wartosci[3])
            inicjatywa = int(wartosci[4])
            czyMozeRozmnazac = int(wartosci[5])
            wiek = int(wartosci[6])
            if ktory == "C":
                self.ustawOrganizm(Czlowiek(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "W":
                self.ustawOrganizm(Wilk(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "O":
                self.ustawOrganizm(Owca(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "A":
                self.ustawOrganizm(Antylopa(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "L":
                self.ustawOrganizm(Lis(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "Z":
                self.ustawOrganizm(Zolw(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "t":
                self.ustawOrganizm(Trawa(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "m":
                self.ustawOrganizm(Mlecz(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "b":
                self.ustawOrganizm(Barszcz(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "g":
                self.ustawOrganizm(Guarana(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "j":
                self.ustawOrganizm(Jagody(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            elif ktory == "Y":
                self.ustawOrganizm(CyberOwca(self, -100, -100, True), x, y, sila, inicjatywa, czyMozeRozmnazac, wiek)
            else:
                print("Bledny format pliku, wychodze z programu")
                sys.exit()

    def ustawOrganizm(self, organizm, x, y, sila, inicjatywa, czyMozeRozmnazac, wiek):
        organizm.setPolozenieX(x)
        organizm.setPolozenieY(y)
        organizm.setSila(sila)
        organizm.setInicjatywa(inicjatywa)
        organizm.setCzyMozeRozmnazac(czyMozeRozmnazac)
        organizm.setWiek(wiek)
        self.__swiatOrganizmow[x][y] = organizm
        self.dodajOrganizm(organizm, False)






