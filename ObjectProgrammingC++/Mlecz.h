#ifndef Mlecz_h
#define Mlecz_h
#include "Roslina.h"
class Mlecz : public Roslina {
protected:
	string Wypisz() const override;
public:
	Mlecz(Swiat* swiat, int x, int y, bool czyZyje);
	void akcja() override;
	Organizm* klonuj() override;
};
#endif