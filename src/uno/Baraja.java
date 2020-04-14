package uno;

import java.util.ArrayList;
import java.util.Iterator;

public class Baraja {

    private ArrayList<Carta> cartas = new ArrayList<Carta>();

    public Baraja() {

        rellenaBaraja();
    }

    public Carta getOne() {

        return cartas.remove((int) (Math.random() * cartas.size()));
    }

    public void addOne(Carta carta) {
        cartas.add(carta);
    }

    public int getSize() {

        return cartas.size();
    }

    private void rellenaBaraja(){
        Carta nuevaCarta = null;
        Carta.Color[] posiblesColores = Carta.Color.values();

        for (int i = 0; i < posiblesColores.length; i++) { //recorre colores
            for (int j = 0; j < 10; j++) { //recorre numeros
                cartas.add(new Carta(posiblesColores[i], Carta.Tipo.NORMAL, j));
                if (j > 0) {
                    cartas.add(new Carta(posiblesColores[i], Carta.Tipo.NORMAL, j));
                }
            }

            for (int z = 0; z < 2; z++) { //cada color tiene dos cartas especiales
            cartas.add(new Carta(posiblesColores[i], Carta.Tipo.CHANGE_DIRECTION, null));
            cartas.add(new Carta(posiblesColores[i], Carta.Tipo.GET_2, null));
            cartas.add(new Carta(posiblesColores[i], Carta.Tipo.FORBIDDEN, null));
            }
        }

        for (int z = 0; z < 3; z++) { //hay cuatro cartas especiales
            cartas.add(new Carta(null, Carta.Tipo.GET_4, null));
            cartas.add(new Carta(null, Carta.Tipo.CHANGE_COLOR, null));
        }
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        Iterator it = cartas.iterator();
        while (it.hasNext()) {
            sB.append(it.next()+"\n");
        }
        return sB.toString();
    }
}



