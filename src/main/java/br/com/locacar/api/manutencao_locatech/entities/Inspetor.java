package br.com.locacar.api.manutencao_locatech.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_inspetor")
public class Inspetor {
    @Id
    private Long numero;
    private String nome;

    public Inspetor() {}

    public Inspetor(Long numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inspetor inspetor = (Inspetor) o;
        return Objects.equals(numero, inspetor.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Inspetor{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                '}';
    }
}
