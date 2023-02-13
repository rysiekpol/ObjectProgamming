#include "Zolw.h"
#include "Swiat.h"
#include <conio.h>

Zolw::Zolw(Swiat* swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 2;
	inicjatywa = 1;
	oznaczenie = 'Z';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

void Zolw::akcja() {
	int wiek = getWiek();
	this->setWiek(++wiek);
	int percentage = rand() % 100;
	if (percentage > 75) {
		Zwierze::akcja();
	}
}

bool Zolw::czyOdbilAtak(Organizm* atakujacy) {
	return atakujacy->getSila() < 5;
}

Organizm* Zolw::klonuj() {
	return new Zolw(swiat, -1, -1, true);
}

string Zolw::Wypisz() const {
	string komentarz = "Zolw na pozycji " + to_string(polozenieX) + ":"  + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Zolw na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}
