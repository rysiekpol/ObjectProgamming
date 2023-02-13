#ifndef Organizm_h
#define Organizm_h
#include <iostream>
#include <string>
using namespace std;
class Swiat;
class Organizm {
protected:
	int sila;
	int inicjatywa;
	int polozenieX;
	int polozenieY;
	char oznaczenie;
	bool czyZyje;
	int czyMozeRozmnazac;
	Swiat* swiat;
	int wiek;	
	//friend std::ostream& operator<<(std::ostream& os, const Organizm& organizm);
public:
	virtual string Wypisz() const = 0;
	virtual Organizm* klonuj() = 0;
	virtual void akcja() = 0;
	virtual void kolizja(Organizm* broniacy) = 0;
	virtual void rysowanie();
	virtual ~Organizm();
	int getPolozenieX();
	void setPolozenieX(int polX);
	int getPolozenieY();
	void setPolozenieY(int polY);
	int getInicjatywa();
	void setInicjatywa(int ile);
	int getSila();
	void setSila(int sila);
	bool getCzyZyje();
	int getCzyMozeRozmnazac();
	void setCzyMozeRozmnazac(int ile);
	void setCzyZyje(bool zyje);
	char getOznaczenie();
	int getWiek();
	void setWiek(int wiek);
	virtual bool czyOdbilAtak(Organizm* atakujacy);
	virtual bool czyZwiekszylAtak(Organizm* atakujacy);
	virtual bool czySmiertelnaRoslina(Organizm* atakujacy);
	virtual bool czyUciekl();
	void przygotujOrganizm();
};

#endif
