#include "Wilcze_jagody.h"
#include "Swiat.h"
#include <conio.h>
Jagody::Jagody(Swiat* swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 99;
	inicjatywa = 0;
	oznaczenie = 'j';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

void Jagody::akcja() {
	przygotujOrganizm();
	int losuj = rand() % 100;
	if (losuj > 97) {
		Roslina::akcja();
	}
}

bool Jagody::czySmiertelnaRoslina(Organizm* atakujacy) {
	return true;
}

Organizm* Jagody::klonuj() {
	return new Jagody(swiat, -1, -1, true);
}

string Jagody::Wypisz() const {
	string komentarz = "Wilcze jagody na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Wilcze jagody na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}