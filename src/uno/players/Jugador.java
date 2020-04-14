package uno.players;

import uno.Carta;
import uno.Juego;

public interface Jugador {

    Carta chooseCarta(Carta carta, Carta.Color color);
    Carta.Color chooseColor();
    boolean removeCarta(Carta carta);

    void addCarta(Carta newCart);

    boolean isEmpty();

    void setJuego(Juego juego);

}
