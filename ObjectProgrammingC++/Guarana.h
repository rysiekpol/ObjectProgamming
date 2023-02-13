#ifndef Guarana_h
#define Guarana_h
#include "Roslina.h"
#include <vector>
#include <algorithm>
class Guarana : public Roslina {
protected:
	string Wypisz() const override;
public:
	Guarana(Swiat* swiat, int x, int y, bool czyZyje);
	void akcja() override;
	virtual bool czyZwiekszylAtak(Organizm* atakujacy) override;
	Organizm* klonuj() override;
};
#endif