package battle;

public class Monster {

    int vida;
    int atq;
    int evasion;
    String imagen;

    public Monster(int vida, int atq, int evasion, String imagen) {
        this.vida = vida;
        this.atq = atq;
        this.evasion = evasion;
        this.imagen = imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public int getVida() {
        return vida;
    }

    public int getAtq() {
        return atq;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setAtq(int atq) {
        this.atq = atq;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

}
