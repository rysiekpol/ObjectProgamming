class Organizm:
    MIN_ZYCIA_ABY_URODZIC = 5

    def __init__(self, Swiat, x, y, czyZyje, czyNull=True):
        self.swiat = Swiat
        self.polozenieX = x
        self.polozenieY = y
        self.czyZyje = czyZyje
        self.sila = None
        self.wiek = None
        self.inicjatywa = None
        self.oznaczenie = None
        self.czyMozeRozmnazac = None
        self.czyNull = czyNull
        self.kolor = None

    def getCzyNull(self):
        return self.czyNull

    def setCzyNull(self, czy):
        self.czyNull = czy

    def setPolozenieX(self, x):
        self.polozenieX = x

    def setPolozenieY(self, y):
        self.polozenieY = y

    def getPolozenieX(self):
        return self.polozenieX

    def getPolozenieY(self):
        return self.polozenieY

    def getInicjatywa(self):
        return self.inicjatywa

    def setInicjatywa(self, ile):
        self.inicjatywa = ile

    def getWiek(self):
        return self.wiek

    def setWiek(self, ile):
        self.wiek = ile

    def getSila(self):
        return self.sila

    def setSila(self, ile):
        self.sila = ile

    def getCzyZyje(self):
        return self.czyZyje

    def setCzyZyje(self, czy):
        self.czyZyje = czy

    def getKolor(self):
        return self.kolor

    def getOznaczenie(self):
        return self.oznaczenie

    def akcja(self):
        pass

    def kolizja(self, atakujacy):
        pass

    def przygotujOrganizm(self):
        self.wiek += 1
        self.czyMozeRozmnazac += 1

    def getCzyMozeRozmnazac(self):
        return self.czyMozeRozmnazac

    def setCzyMozeRozmnazac(self, ile):
        self.czyMozeRozmnazac = ile

    def koncowka(self):
        return " na pozycji " + str(self.polozenieX) + ":" + str(self.polozenieY) \
               + " o sile: " + str(self.sila) + " inicjatywie: " \
               + str(self.inicjatywa) + " wiek:" + str(self.wiek)
