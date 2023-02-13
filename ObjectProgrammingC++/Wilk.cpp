#include "Wilk.h"
#include "Swiat.h"
#include <conio.h>

Wilk::Wilk(Swiat* swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 9;
	inicjatywa = 5;
	oznaczenie = 'W';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

Organizm* Wilk::klonuj() {
	return new Wilk(swiat, -1, -1, true);
}

string Wilk::Wypisz() const {
	string komentarz = "Wilk na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Wilk na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}
