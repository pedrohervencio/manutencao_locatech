package br.com.locacar.api.manutencao_locatech.entities;

import br.com.locacar.api.manutencao_locatech.valueobject.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_manutencao")
public class Manutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    private LocalDate dataInicio;
    private Integer duracao;
    private String concessionaria;
    @ManyToOne
    @JoinColumn(name = "inspetor_numero")
    Inspetor inspetor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo_id")
    Veiculo veiculo;
    Status status;

    public Manutencao() {}

    public Manutencao(Long numero, LocalDate dataInicio, Integer duracao, String concessionaria,
                      Inspetor inspetor, Veiculo veiculo, Status status) {
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.duracao = duracao;
        this.concessionaria = concessionaria;
        this.inspetor = inspetor;
        this.veiculo = veiculo;
        this.status = status;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getConcessionaria() {
        return concessionaria;
    }

    public void setConcessionaria(String concessionaria) {
        this.concessionaria = concessionaria;
    }

    public Inspetor getInspetor() {
        return inspetor;
    }

    public void setInspetor(Inspetor inspetor) {
        this.inspetor = inspetor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manutencao that = (Manutencao) o;
        return Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Manutencao{" +
                "numero=" + numero +
                ", dataInicio=" + dataInicio +
                ", duracao=" + duracao +
                ", concessionaria='" + concessionaria + '\'' +
                ", inspetor=" + inspetor +
                ", veiculo=" + veiculo +
                ", status=" + status +
                '}';
    }
}
