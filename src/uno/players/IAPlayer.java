package uno.players;

import uno.Carta;
import uno.ConsolePrint;
import uno.Juego;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class IAPlayer implements Jugador {
    private String nombre;
    private Juego juego;
    public ArrayList<Carta> mano = new ArrayList<Carta>();

    public IAPlayer(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Carta chooseCarta(Carta carta, Carta.Color currentColor) {
        int indexCartaElegida;
        int totalCartasRobar;
        System.out.println("_____________________________________________________________________________________________________________");
        System.out.println("Última carta tirada => Tipo: " + carta.getTipo() + " Color:" + carta.getColor() + " Número:" + carta.getNumero());

        ConsolePrint.pintaCarta(carta);

        System.out.println("Color Actual => " + currentColor);

        System.out.println("Turno del jugador: " + nombre );

        ConsolePrint.pintaCartas(mano);

        for (int i = 0; i < mano.size() ; i++) {
            totalCartasRobar = juego.totalCartasRobar;
            if (juego.validaCartaTirada(mano.get(i))) {
               System.out.println("El jugador: " + nombre +" ha elegido la carta "+ i );
                juego.totalCartasRobar = totalCartasRobar; //me aseguro de que no se sume totalCartasRobar volviendo a poner el valor que ya tenía
               return mano.get(i);
            }
        }
        if (juego.totalCartasRobar>0){
            System.out.println("El jugador: " + nombre +" roba "+juego.totalCartasRobar+" cartas" );
        } else {
            System.out.println("El jugador: " + nombre +" roba una cartas" );
        }

        return null;
    }

    @Override
    public Carta.Color chooseColor() {
        Integer colors[] = new Integer[4];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = 0;
        }

        for (int i = 0; i < mano.size(); i++) {
            if (mano.get(i).getColor() != null) {
                switch (mano.get(i).getColor()) {
                    case BLUE:
                        colors[0]++;
                        break;
                    case RED:
                        colors[1]++;
                        break;
                    case YELLOW:
                        colors[2]++;
                        break;
                    case GREEN:
                        colors[3]++;
                        break;
                }
            }
        }
        return Carta.Color.values()[Arrays.asList(colors).indexOf(Collections.max(Arrays.asList(colors)))];
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
