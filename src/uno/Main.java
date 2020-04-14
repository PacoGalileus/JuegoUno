package uno;

import uno.players.IAPlayer;
import uno.players.Jugador;
import uno.players.Persona;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Baraja NewBaraja = new Baraja();
       // System.out.print(NewBaraja.toString());

        Juego NewJuego = new Juego(PideJugadores());

    }
    private static ArrayList PideJugadores(){
        Integer numJugadores = 0;

        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

        System.out.println("Indique el número de jugadores humanos");
        Scanner sc = new Scanner(System.in);
        String entrada = sc.next();

        try {
            numJugadores = Integer.parseInt(entrada); //si no es un integer
        } catch (NumberFormatException e) {
            System.out.println("No ha introducido un número");
            return null;
        }
        if ((numJugadores < 0) || (numJugadores > 10)){//|| (numJugadores < 1)) { //si el numero de jugadores elegido está fuera de rango
            System.out.println("El número de jugadores está fuera de rango");
            return null;
        }

        for (int i = 0; i < numJugadores ; i++) {
            System.out.println("Indique el nombre del jugador "+i);
            entrada = sc.next();
            jugadores.add(new Persona(entrada));
        }

        System.out.println("Indique el número de jugadores IA");
        entrada = sc.next();

        try {
            numJugadores = Integer.parseInt(entrada); //si no es un integer
        } catch (NumberFormatException e) {
            System.out.println("No ha introducido un número");
            return null;
        }

        for (int i = 0; i < numJugadores ; i++) {
            jugadores.add(new IAPlayer("IA Jugador "+i));
        }

        return jugadores;
    }
}
