package br.com.locacar.api.manutencao_locatech.service;

import br.com.locacar.api.manutencao_locatech.controller.exception.ControllerNotFoundException;
import br.com.locacar.api.manutencao_locatech.dto.ManutencaoDTO;
import br.com.locacar.api.manutencao_locatech.entities.Manutencao;
import br.com.locacar.api.manutencao_locatech.repository.ManutencaoRepository;
import br.com.locacar.api.manutencao_locatech.valueobject.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManutencaoService {
    private final ManutencaoRepository manutencaoRepository;

    @Autowired
    public ManutencaoService(ManutencaoRepository manutencaoRepository) {
        this.manutencaoRepository = manutencaoRepository;
    }

    public Page<ManutencaoDTO> findAll(Pageable pageable) {
        Page<Manutencao> manutencoes = manutencaoRepository.findAll(pageable);
        return manutencoes.map(this::toDTO);
    }

    public ManutencaoDTO findById(Long numero) {
        Manutencao manutencao = manutencaoRepository.findById(numero).
                orElseThrow(() -> new ControllerNotFoundException("Manutenção não encontrada"));
        return toDTO(manutencao);
    }

    public ManutencaoDTO save(ManutencaoDTO manutencaoDTO) {
        Manutencao manutencao = toEntity(manutencaoDTO);
        manutencao.setStatus(Status.MANUTENCAO_INICIADA);
        manutencao = manutencaoRepository.save(manutencao);
        return toDTO(manutencao);
    }

    public ManutencaoDTO concluiManutencao(Long numero, ManutencaoDTO manutencaoDTO) {
        return update(numero, manutencaoDTO, Status.MANUTENCAO_CONCLUIDA);
    }

    public ManutencaoDTO update(Long numero, ManutencaoDTO manutencaoDTO, Status status) {
        try {
            Manutencao manutencao = manutencaoRepository.getReferenceById(numero);
            manutencao.setDataInicio(manutencaoDTO.dataInicio());
            manutencao.setDuracao(manutencaoDTO.duracao());
            manutencao.setConcessionaria(manutencaoDTO.concessionaria());
            manutencao.setInspetor(manutencaoDTO.inspetor());
            manutencao.setVeiculo(manutencaoDTO.veiculo());
            manutencao.setStatus(status);
            manutencao = manutencaoRepository.save(manutencao);
            return toDTO(manutencao);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Manutenção não encontrada");
        }
    }

    public void delete(Long numero) {
        manutencaoRepository.deleteById(numero);
    }

    private Manutencao toEntity(ManutencaoDTO manutencaoDTO) {
        return new Manutencao(
                manutencaoDTO.numero(),
                manutencaoDTO.dataInicio(),
                manutencaoDTO.duracao(),
                manutencaoDTO.concessionaria(),
                manutencaoDTO.inspetor(),
                manutencaoDTO.veiculo(),
                manutencaoDTO.status()
        );
    }


    private ManutencaoDTO toDTO(Manutencao manutencao) {
        return new ManutencaoDTO(
                manutencao.getNumero(),
                manutencao.getDataInicio(),
                manutencao.getDuracao(),
                manutencao.getConcessionaria(),
                manutencao.getInspetor(),
                manutencao.getVeiculo(),
                manutencao.getStatus()
        );
    }


}
