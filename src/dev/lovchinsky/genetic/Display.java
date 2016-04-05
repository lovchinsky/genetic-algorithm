package dev.lovchinsky.genetic;

import dev.lovchinsky.genetic.conception.City;

public interface Display {
    void clear();
    void drawCity(City city);
    void drawRoute(City firstCity, City secondCity);
}
