#ifndef Wilk_h
#define Wilk_h
#include "Zwierze.h"
class Wilk : public Zwierze {
protected:
	string Wypisz() const override;
public:
	Wilk(Swiat* swiat, int x, int y, bool czyZyje);
	Organizm* klonuj() override;
};

#endif