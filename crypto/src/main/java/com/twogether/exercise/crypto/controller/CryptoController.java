package com.twogether.exercise.crypto.controller;


import com.twogether.exercise.crypto.dto.CryptoDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = CryptoController.ROOT_CRYPTO_ENDPOINT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public interface CryptoController {

    String ROOT_CRYPTO_ENDPOINT_URL = "/crypto";
    String CRYPTO_URL = "/{acronym}";

    /**
     * Obtains the detail of a crypto based on its acronym
     *
     * @param acronym of the crypto you want to search
     *
     * @return Object {CryptoDTO}
     */
    @GetMapping(CryptoController.CRYPTO_URL)
    ResponseEntity<CryptoDTO> searchCrypto(final String acronym);

    /**
     * Allows you to register a new crypto
     *
     * @param cryptoDTO Object {CryptoDTO}
     *
     * @return 201
     */
    @PostMapping
    ResponseEntity<Void> createCrypto(final CryptoDTO cryptoDTO);

    /**
     * Delete a crypto
     *
     * @param acronym of the crypto you want to delete
     *
     * @return 201
     */
    @DeleteMapping(CryptoController.CRYPTO_URL)
    ResponseEntity<Void> deleteCrypto(final String acronym);

}
