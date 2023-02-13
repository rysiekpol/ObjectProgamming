from Zwierzeta.zwierze import Zwierze


class Owca(Zwierze):
    def __init__(self, Swiat, x, y, czyZyje):
        super(Owca, self).__init__(Swiat, x, y, czyZyje)
        self.sila = 4
        self.inicjatywa = 4
        self.kolor = "pink"
        self.oznaczenie = "O"
        self.czyMozeRozmnazac = 0
        self.wiek = 0

    def Wypisz(self):
        return "Owca" + self.koncowka()



