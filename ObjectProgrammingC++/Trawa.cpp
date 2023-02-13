#include "Trawa.h"
#include "Swiat.h"
#include <conio.h>
Trawa::Trawa(Swiat* swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 0;
	inicjatywa = 0;
	oznaczenie = 't';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

void Trawa::akcja() {
	przygotujOrganizm();
	int losuj = rand() % 100;
	if (losuj > 95) {
		Roslina::akcja();
	}
}

Organizm* Trawa::klonuj() {
	return new Trawa(swiat, -1, -1, true);
}

string Trawa::Wypisz() const {
	string komentarz = "Trawa na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Trawa na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}