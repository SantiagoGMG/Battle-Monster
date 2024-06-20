
package battle;

public enum Elementos {
    piedra(0),
    papel(1),
    tijera(2);
    
    private int valor;

    private Elementos(int valor) {
        this.valor = valor;
    }

    public static Elementos getPiedra() {
        return piedra;
    }

    public static Elementos getPapel() {
        return papel;
    }

    public static Elementos getTijera() {
        return tijera;
    }

    public int getValor() {
        return valor;
    }
    
}
