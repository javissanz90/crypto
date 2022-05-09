package com.twogether.exercise.crypto.controller.impl;

import com.twogether.exercise.crypto.controller.CryptoController;
import com.twogether.exercise.crypto.dto.CryptoDTO;
import com.twogether.exercise.crypto.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CryptoControllerImpl implements CryptoController {

    private final CryptoService cryptoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<CryptoDTO> searchCrypto(@PathVariable final String acronym) {
        return ResponseEntity.ok(this.cryptoService.searchCrypto(acronym));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> createCrypto(@RequestBody @Valid final CryptoDTO cryptoDTO) {
        this.cryptoService.createCrypto(cryptoDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Void> deleteCrypto(@PathVariable final String acronym) {
        this.cryptoService.deleteCrypto(acronym);
        return ResponseEntity.noContent().build();
    }
}
