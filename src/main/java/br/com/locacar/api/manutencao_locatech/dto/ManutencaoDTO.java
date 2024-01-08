package br.com.locacar.api.manutencao_locatech.dto;

import br.com.locacar.api.manutencao_locatech.entities.Inspetor;
import br.com.locacar.api.manutencao_locatech.entities.Veiculo;
import br.com.locacar.api.manutencao_locatech.valueobject.Status;

import java.time.LocalDate;

public record ManutencaoDTO(
        Long numero,
        LocalDate dataInicio,
        Integer duracao,
        String concessionaria,
        Inspetor inspetor,
        Veiculo veiculo,
        Status status
) {
}
