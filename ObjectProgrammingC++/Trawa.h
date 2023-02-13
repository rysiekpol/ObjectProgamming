#ifndef Trawa_h
#define Trawa_h
#include "Roslina.h"
#include <vector>
#include <algorithm>
class Trawa : public Roslina {
protected:
	string Wypisz() const override;
public:
	Trawa(Swiat* swiat, int x, int y, bool czyZyje);
	void akcja() override;
	Organizm* klonuj() override;
};
#endif