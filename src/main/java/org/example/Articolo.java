package org.example;

public class Articolo {
    private Integer articoloID;

    public Integer getArticoloID() {
        return articoloID;
    }

    public void setArticoloID(Integer a) {
        this.articoloID = a;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String descrizione;
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    private Float prezzoUnitarioVendita;
    public Float getPrezzoUnitarioVendita() {
        return prezzoUnitarioVendita;
    }

    public void setPrezzoUnitarioVendita(Float prezzoUnitarioVendita) {
        this.prezzoUnitarioVendita = prezzoUnitarioVendita;
    }

    private Integer giacenza;
    public Integer getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(Integer giacenza) {
        this.giacenza = giacenza;
    }

    private Integer aliquotaIVA;
    public Integer getAliquotaIVA() {
        return aliquotaIVA;
    }

    public void setAliquotaIVA(Integer aliquotaIVA) {
        this.aliquotaIVA = aliquotaIVA;
    }

    // costruttore della classe
    public Articolo() {
    }

    public Articolo(Integer id, String nome, String descrizione, Float prezzo, Integer quantita, Integer iva) {
        // compilare istanza
        this.articoloID = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzoUnitarioVendita = prezzo;
        this.giacenza = quantita;
        this.aliquotaIVA = iva;
    }
}
