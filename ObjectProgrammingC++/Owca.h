#ifndef Owca_h
#define Owca_h
#include "Zwierze.h"
class Owca : public Zwierze {
protected:
	string Wypisz() const override;
public:
	Owca(Swiat* swiat, int x, int y, bool czyZyje);
	Organizm* klonuj() override;
};

#endif