package gomes.dd;

public class PlayerSheet {
    public String nome;
    public String classe;
    public String raca;
    public String alinhamento;
    public int xp;

    public int forca;
    public int destreza;
    public int constituicao;
    public int inteligencia;
    public int sabedoria;
    public int carisma;

    public PlayerSheet(String nome) {
        this.nome = nome;
        this.classe = "Desconhecida";
        this.raca = "Desconhecida";
        this.alinhamento = "Neutro";
        this.xp = 0;

        this.forca = 10;
        this.destreza = 10;
        this.constituicao = 10;
        this.inteligencia = 10;
        this.sabedoria = 10;
        this.carisma = 10;
    }
}
