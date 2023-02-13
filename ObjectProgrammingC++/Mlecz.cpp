#include "Mlecz.h"
#include "Swiat.h"
#include <conio.h>
Mlecz::Mlecz(Swiat* swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 0;
	inicjatywa = 0;
	oznaczenie = 'm';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

void Mlecz::akcja() {
	przygotujOrganizm();
	for (int i = 0; i < 3; i++) {
		int losuj = rand() % 100;
		if (losuj > 96) {
			Roslina::akcja();
		}
	}
}

Organizm* Mlecz::klonuj() {
	return new Mlecz(swiat, -1, -1, true);
}

string Mlecz::Wypisz() const {
	string komentarz = "Mlecz na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Mlecz na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}