#ifndef Wilcze_jagody_h
#define Wilcze_jagody_h
#include "Roslina.h"
#include <vector>
#include <algorithm>
class Jagody : public Roslina {
protected:
	string Wypisz() const override;
public:
	Jagody(Swiat* swiat, int x, int y, bool czyZyje);
	void akcja() override;
	virtual bool czySmiertelnaRoslina(Organizm* atakujacy);
	Organizm* klonuj() override;
};
#endif