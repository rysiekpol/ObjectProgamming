#include "Guarana.h"
#include "Swiat.h"
#include <conio.h>
Guarana::Guarana(Swiat* swiat, int x, int y, bool czyZyje) {
	this->polozenieX = x;
	this->polozenieY = y;
	sila = 0;
	inicjatywa = 0;
	oznaczenie = 'g';
	this->swiat = swiat;
	this->czyZyje = czyZyje;
	this->czyMozeRozmnazac = 0;
	wiek = 0;
}

void Guarana::akcja() {
	przygotujOrganizm();
	int losuj = rand() % 100;
	if (losuj > 97) {
		Roslina::akcja();
	}
}

bool Guarana::czyZwiekszylAtak(Organizm* atakujacy) {
	string status;
	vector<string> *temp = &swiat->getKomentarz();
	status = Wypisz() + " ZWIEKSZYLA ATAK " + atakujacy->Wypisz();
	(*temp).push_back(status);
	int sila = atakujacy->getSila() + 3;
	atakujacy->setSila(sila);
	return true;
}


Organizm* Guarana::klonuj() {
	return new Guarana(swiat, -1, -1, true);
}

string Guarana::Wypisz() const {
	string komentarz = "Guarana na pozycji " + to_string(polozenieX) + ":" + to_string(polozenieY) + " o sile: " + to_string(sila) + " inicjatywie: " + to_string(inicjatywa) + " wiek:" + to_string(wiek);
	return komentarz;
	//cout << "Guarana na pozycji " << polozenieX << ":" << polozenieY << " o sile: " << sila << " inicjatywie: " << inicjatywa << " wiek:" << wiek;
}