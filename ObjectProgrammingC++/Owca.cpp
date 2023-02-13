#include "Owca.h"
#include "Swiat.h"
#include <conio.h>

Owca::Owca(Swiat* swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 4;
	inicjatywa = 4;
	oznaczenie = 'O';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

Organizm* Owca::klonuj() {
	return new Owca(swiat, -1, -1, true);
}

string Owca::Wypisz() const {
	string komentarz = "Owca na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Owca na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}
