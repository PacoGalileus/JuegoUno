package uno;

import java.util.ArrayList;

public class ConsolePrint {

    public static void pintaCartas(ArrayList<Carta> mano) {
        String[] linea = {"", "", "", ""};

        linea[0] = "";
        linea[1] = "";
        linea[2] = "";
        linea[3] = "       ";
        for (int i = 0; i < mano.size(); i++) {
            rellenaCarta(mano.get(i),linea);
            linea[3] = linea[3] + i + "            ";
        }
        pintar(linea);
    }

    public static void pintaCarta(Carta carta) {
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add(carta);
        pintaCartas(cartas);
    }

    private static void rellenaCarta(Carta carta,String[] linea) {

        String ANSI_RESET = "\u001B[0m";
        String ANSI_SUBROJO = "\u001B[41;30m";
        String ANSI_SUBVERDE = "\u001B[42;30m";
        String ANSI_SUBAMARILLO = "\u001B[43;30m";
        String ANSI_SUBAZUL = "\u001B[44;30m";
        String color;
        String texto = "";

        switch (carta.getTipo()) {
            case NORMAL:
                texto = " " + carta.getNumero().toString() + " ";
                break;
            case GET_2:
                texto = "+ 2";
                break;
            case FORBIDDEN:
                texto = " X ";
                break;
            case CHANGE_DIRECTION:
                texto = "« »";
                break;
            case GET_4:
                texto = "+ 4";
                break;
            case CHANGE_COLOR:
                texto = " C ";
                break;
        }

        switch (carta.getTipo()) {
            case NORMAL:
            case GET_2:
            case FORBIDDEN:
            case CHANGE_DIRECTION:
                if (carta.getColor() == Carta.Color.RED) {
                    color = ANSI_SUBROJO;
                } else if (carta.getColor() == Carta.Color.GREEN) {
                    color = ANSI_SUBVERDE;
                } else if (carta.getColor() == Carta.Color.YELLOW) {
                    color = ANSI_SUBAMARILLO;
                } else {
                    color = ANSI_SUBAZUL;
                }
                linea[0] = linea[0] + "  ¦" + color + "   " + color + "   " + color + "   " + ANSI_RESET + "¦";
                linea[1] = linea[1] + "  ¦" + color + "   " + texto + color + "   " + ANSI_RESET + "¦";
                linea[2] = linea[2] + "  ¦" + color + "   " + color + "   " + color + "   " + ANSI_RESET + "¦";
                break;
            case CHANGE_COLOR:
            case GET_4:
                linea[0] = linea[0] + "  ¦" + ANSI_SUBROJO + "    " + ANSI_SUBVERDE + "    " + ANSI_RESET + "¦";
                linea[1] = linea[1] + "  ¦  " + texto + "   ¦";
                linea[2] = linea[2] + "  ¦" + ANSI_SUBAMARILLO + "    " + ANSI_SUBAZUL + "    " + ANSI_RESET + "¦";
                break;
        }
    }

    private static void pintar(String[] linea) {
        for (int i = 0; i < linea.length; i++) {
            if (!linea[i].isEmpty()) {
                System.out.println(linea[i]);
            }
        }
    }
}
