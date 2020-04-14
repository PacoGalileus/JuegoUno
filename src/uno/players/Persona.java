package uno.players;

import uno.Carta;
import uno.ConsolePrint;
import uno.Juego;
import uno.players.Jugador;

import java.util.ArrayList;
import java.util.Scanner;

public class Persona implements Jugador {
    private String nombre;
    public ArrayList<Carta> mano = new ArrayList<Carta>();
    private Juego juego;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Carta chooseCarta(Carta carta, Carta.Color currentColor) {
        int indexCartaElegida;
        System.out.println("_____________________________________________________________________________________________________________");
        System.out.println("Última carta => Tipo: " + carta.getTipo() + " Color:" + carta.getColor() + " Número:" + carta.getNumero());


        ConsolePrint.pintaCarta(carta);

        System.out.println("currentColor => " + currentColor);

        System.out.println("Turno del jugador: " + nombre + "\nElija una carta:");

        ConsolePrint.pintaCartas(mano);

        Scanner sc = new Scanner(System.in);
        String cartaElegida = sc.next();

        if (("r".equals(cartaElegida)) || ("R".equals(cartaElegida))) { //si el jugador no tiene carta para tirar tecleará r para robar
            return null;
        } else {
            try {
                indexCartaElegida = Integer.parseInt(cartaElegida); //si la carta no es un integer
            } catch (NumberFormatException e) {
                return chooseCarta(carta, currentColor);
            }
            if ((indexCartaElegida >= mano.size()) || (indexCartaElegida < 0)) { //si la carta elegida está fuera de rango
                return chooseCarta(carta, currentColor);
            }
        }

        return mano.get(indexCartaElegida);
    }

    @Override
    public Carta.Color chooseColor() {
        int indexColorElegido;
        String ANSI_RESET = "\u001B[0m";
        String ANSI_SUBROJO = "\u001B[41;30m";
        String ANSI_SUBVERDE = "\u001B[42;30m";
        String ANSI_SUBAMARILLO = "\u001B[43;30m";
        String ANSI_SUBAZUL = "\u001B[44;30m";

        System.out.println("Elije color: "+ ANSI_SUBAZUL +" 0 BLUE "+ ANSI_SUBROJO+" 1 RED "+ ANSI_SUBAMARILLO+" 2 YELLOW "+ ANSI_SUBVERDE+" 3 GREEN "+ANSI_RESET);
        Scanner sc = new Scanner(System.in);
        String colorElegido = sc.next();

        try {
            indexColorElegido = Integer.parseInt(colorElegido); //si la carta no es un integer
        } catch (NumberFormatException e) {
            return chooseColor();
        }
        if ((indexColorElegido < 0) || (indexColorElegido > 3)) { //si el color elegido está fuera de rango
            return chooseColor();
        }

        return Carta.Color.values()[indexColorElegido];
    }

    @Override
    public boolean removeCarta(Carta delCart) {
        return mano.remove(delCart);
    }

    public void addCarta(Carta newCart) {
        mano.add(newCart);
    }

    @Override
    public String toString() {
        return "uno.players.Persona{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean isEmpty() {
        return mano.isEmpty();
    }

    @Override
    public void setJuego(Juego juego) {
        this.juego = juego;
    }
}
