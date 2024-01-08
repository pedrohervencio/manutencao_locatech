package br.com.locacar.api.manutencao_locatech.repository;

import br.com.locacar.api.manutencao_locatech.entities.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
}
