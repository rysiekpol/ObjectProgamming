from Zwierzeta.zwierze import Zwierze
from organizm import Organizm

class Wilk(Zwierze):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Wilk, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 9
        self.inicjatywa = 5
        self.kolor = "black"
        self.oznaczenie = "W"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def Wypisz(self):
        return "Wilk" + self.koncowka()



        