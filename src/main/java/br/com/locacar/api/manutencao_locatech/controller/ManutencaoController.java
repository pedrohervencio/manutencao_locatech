package br.com.locacar.api.manutencao_locatech.controller;

import br.com.locacar.api.manutencao_locatech.dto.ManutencaoDTO;
import br.com.locacar.api.manutencao_locatech.service.ManutencaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manutencao/manutencao")
public class ManutencaoController {
    private final ManutencaoService manutencaoService;

    @Autowired
    public ManutencaoController(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    @GetMapping
    public ResponseEntity<Page<ManutencaoDTO>> findAll(
            @PageableDefault(size = 10, page = 0, sort = "numero")Pageable pageable) {
        Page<ManutencaoDTO> manutencoesDTO = manutencaoService.findAll(pageable);
        return ResponseEntity.ok(manutencoesDTO);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<ManutencaoDTO> findById(@PathVariable Long numero) {
        ManutencaoDTO manutencaoDTO = manutencaoService.findById(numero);
        return ResponseEntity.ok(manutencaoDTO);
    }

    @PostMapping
    public ResponseEntity<ManutencaoDTO> save(@RequestBody ManutencaoDTO manutencaoDTO) {
        ManutencaoDTO savedManutencaoDTO = manutencaoService.save(manutencaoDTO);
        return new ResponseEntity<>(savedManutencaoDTO, HttpStatus.CREATED);
    }

    @PutMapping("/concluimanutencao/{numero}")
    public ResponseEntity<ManutencaoDTO> update(
            @PathVariable Long numero,
            @RequestBody ManutencaoDTO manutencaoDTO
    ) {
        ManutencaoDTO updatedManutencaoDTO = manutencaoService.concluiManutencao(numero, manutencaoDTO);
        return ResponseEntity.ok(updatedManutencaoDTO);
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> delete(@PathVariable Long numero) {
        manutencaoService.delete(numero);
        return  ResponseEntity.noContent().build();
    }

}
