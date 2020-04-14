package uno;

import java.util.Objects;

public class Carta {

    public enum Color{
        BLUE,RED,YELLOW,GREEN
    }

    public enum Tipo{
        NORMAL,GET_2,CHANGE_DIRECTION,FORBIDDEN,GET_4,CHANGE_COLOR
    }

    private Color color;
    private Tipo tipo;
    private Integer numero;

    public Carta(Color color,Tipo tipo, Integer numero) {
      this.color = color;
      this.tipo = tipo;
      this.setNumero(numero);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        if (numero == null || (numero>=0 && numero<=9)) {
            this.numero = numero;
        }
    }

    @Override
    public String toString() {
        return "uno.Carta{" +
                "tipo=" + tipo +
                ", color=" + color +
                ", numero=" + numero +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return color == carta.color &&
                tipo == carta.tipo &&
                Objects.equals(numero, carta.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, tipo, numero);
    }
}
