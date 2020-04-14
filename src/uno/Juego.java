package uno;

import uno.players.Jugador;

import java.util.ArrayList;
import java.util.LinkedList;

public class Juego {

    private ArrayList<Jugador> listaJugadores;
    private Baraja baraja = new Baraja();
    private LinkedList<Carta> cartasTiradas = new LinkedList<>();
    private Integer indexListaJugadores = 0;
    private boolean isRight = true;
    private Carta.Color currentColor;
    public Integer totalCartasRobar = 0;

    public Juego(ArrayList<Jugador> listaJugadores) {
        if (listaJugadores != null || (listaJugadores.size() <= 10 && listaJugadores.size() > 1)) {
            this.listaJugadores = listaJugadores;
            runGame();
        }
    }

    private void inicializaJuego() {
        for (int i = 0; i < (listaJugadores.size()); i++) {
            listaJugadores.get(i).setJuego(this);                //añade control inverso a Jugador
            for (int j = 0; j < 7; j++) {
                listaJugadores.get(i).addCarta(baraja.getOne()); //reparte cartas
            }
        }
    }

    private void runGame() {

        inicializaJuego();
        cartasTiradas.push(baraja.getOne()); //Sacamos la primera carta
        while (cartasTiradas.peek().getTipo()!= Carta.Tipo.NORMAL){
            cartasTiradas.push(baraja.getOne()); //Sacamos otra carta porque la primera tiene que ser normal
        }

        cartasTiradas.push(baraja.getOne()); //Sacamos la primera carta
        currentColor = cartasTiradas.peek().getColor();
        juegaTurno(listaJugadores.get(0)); //empieza el jugador 0

        while (true) {
            if (cartasTiradas.peek().getTipo() == Carta.Tipo.CHANGE_DIRECTION) {
                isRight = !isRight;
            } else if (cartasTiradas.peek().getTipo() == Carta.Tipo.FORBIDDEN) {
                nextPlayer();
            }
            if (juegaTurno(nextPlayer())) {
                break;
            }
        }
        System.out.println("******* GAME OVER *******");
        System.out.println("HA GANADO EL JUGADOR: "+listaJugadores.get(indexListaJugadores));
    }

    private Jugador nextPlayer() {
        if (isRight) {
            indexListaJugadores = listaJugadores.size() == indexListaJugadores + 1 ? 0 : indexListaJugadores + 1;
        } else {
            indexListaJugadores = indexListaJugadores == 0 ? listaJugadores.size() - 1 : indexListaJugadores - 1;
        }

        return listaJugadores.get(indexListaJugadores);
    }

    private boolean juegaTurno(Jugador turnoJugador) {

        while (true) {
            Carta carta = turnoJugador.chooseCarta(cartasTiradas.peek(), currentColor);
            if (carta == null) {

                if (totalCartasRobar > 0) { //si hay cartas por robar significa y el jugador saca un r significa que no se puede defender
                    for (int i = 0; i < totalCartasRobar; i++) {
                        if (baraja.getSize() == 0) { //nos quedamos sin cartas en la baraja asi que cogemos de las tiradas
                            rebarajar();
                        }
                        turnoJugador.addCarta(baraja.getOne());//El jugador roba carta
                    }
                    totalCartasRobar = 0;
                } else {
                    if (baraja.getSize() == 0) { //nos quedamos sin cartas en la baraja asi que cogemos de las tiradas
                        rebarajar();
                    }
                    turnoJugador.addCarta(baraja.getOne());//Le damos carta al jugador que ha tecleado r para robar
                    carta = turnoJugador.chooseCarta(cartasTiradas.peek(), currentColor);
                }
            }

            if (carta == null) { //Si vuelve otro null es que no ha podido tirar la carta robada
                break;
            }

            if (validaCartaTirada(carta)) {
                turnoJugador.removeCarta(carta);
                cartasTiradas.push(carta);

                if (cartasTiradas.peek().getTipo() == Carta.Tipo.CHANGE_COLOR || cartasTiradas.peek().getTipo() == Carta.Tipo.GET_4) {
                    currentColor = turnoJugador.chooseColor();
                } else {
                    currentColor = cartasTiradas.peek().getColor();
                }
                break;
            }
        }

        return turnoJugador.isEmpty();
    }

    public void rebarajar(){
        int totalCartasTiradas = cartasTiradas.size();
        for (int i = 0; i < totalCartasTiradas - 1; i++) {
            baraja.addOne(cartasTiradas.pollLast());
        }
    }

    public boolean validaCartaTirada(Carta carta) {

        if (totalCartasRobar > 0) {
            switch (carta.getTipo()) {
                case GET_2:
                    if (cartasTiradas.peek().getTipo().equals(Carta.Tipo.GET_2))  {
                        totalCartasRobar =+ 2;
                        return true;
                    } else {
                        return false;
                    }
                case GET_4:
                    if (cartasTiradas.peek().getTipo().equals(Carta.Tipo.GET_4)) {
                        totalCartasRobar =+ 4;
                        return true;
                    }
                default:
                    return false;
            }
        } else {

            switch (carta.getTipo()) {
                case NORMAL:
                    return (carta.getColor() == currentColor || carta.getNumero().equals(cartasTiradas.peek().getNumero()));
                case GET_2:
                    if (carta.getColor() == currentColor) {
                        totalCartasRobar = totalCartasRobar + 2;
                        return true;
                    }
                    break; //¿es necesario?
                case GET_4:
                    totalCartasRobar = totalCartasRobar + 4;
                    return true;
                case CHANGE_COLOR:
                    return true;
                case CHANGE_DIRECTION:
                case FORBIDDEN:
                    return (carta.getColor() == currentColor || cartasTiradas.peek().getTipo().equals(Carta.Tipo.FORBIDDEN));
            }
        }

        return false;
    }
}
