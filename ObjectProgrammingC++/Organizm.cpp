#include "Organizm.h"

/*Organizm::Organizm(const Organizm& other)
	: sila(other.sila), inicjatywa(other.inicjatywa), polozenieX(other.polozenieX), polozenieY(other.polozenieY), oznaczenie(other.oznaczenie), swiat(other.swiat){}*/

Organizm::~Organizm() {}

void Organizm::rysowanie() {

}

//std::ostream& operator<<(std::ostream& os, const Organizm& organizm) {
//	organizm.Wypisz(os);
//	return os;
//}

int Organizm::getPolozenieX() {
	return polozenieX;
}

void Organizm::setPolozenieX(int polX) {
	polozenieX = polX;
}

int Organizm::getPolozenieY() {
	return polozenieY;
}

void Organizm::setPolozenieY(int polY) {
	polozenieY = polY;
}

int Organizm::getInicjatywa(){
	return inicjatywa;
}

void Organizm::setInicjatywa(int ile) {
	inicjatywa = ile;
}

char Organizm::getOznaczenie() {
	return oznaczenie;
}

bool Organizm::getCzyZyje() {
	return czyZyje;
}

int Organizm::getCzyMozeRozmnazac() {
	return czyMozeRozmnazac;
}

void Organizm::setCzyMozeRozmnazac(int ile) {
	czyMozeRozmnazac = ile;
}

void Organizm::setCzyZyje(bool zyje) {
	czyZyje = zyje;
}

int Organizm::getSila() {
	return sila;
}

void Organizm::setSila(int sila) {
	this->sila=sila;
}

int Organizm::getWiek() {
	return wiek;
}

void Organizm::setWiek(int wiek) {
	this->wiek = wiek;
}

bool Organizm::czyOdbilAtak(Organizm* atakujacy) {
	return false;
}

bool Organizm::czyZwiekszylAtak(Organizm* atakujacy) {
	return false;
}

bool Organizm::czySmiertelnaRoslina(Organizm* atakujacy) {
	return false;
}

bool Organizm::czyUciekl() {
	return false;
}

void Organizm::przygotujOrganizm() {
	wiek++;
	czyMozeRozmnazac++;
}