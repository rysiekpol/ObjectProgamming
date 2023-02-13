#ifndef Roslina_h
#define Roslina_h

#include "Organizm.h"
#include <string>

class Roslina : public Organizm {
public:
	void akcja() override;
	void kolizja(Organizm* atakujacy) override;
	virtual ~Roslina();
};

#endif