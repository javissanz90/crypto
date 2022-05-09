package com.twogether.exercise.crypto.service;

import com.twogether.exercise.crypto.entity.Crypto;
import com.twogether.exercise.crypto.dto.CryptoDTO;
import com.twogether.exercise.crypto.exception.ConstantError;
import com.twogether.exercise.crypto.exception.InvalidDataException;
import com.twogether.exercise.crypto.repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    /**
     * Function search a crypto
     *
     * @param acronym attribute to make the search
     * @return Object {Crypto}
     */
    public CryptoDTO searchCrypto(final String acronym) {

        Crypto crypto = this.cryptoRepository.findByAcronym(acronym).orElseThrow(() ->
                new InvalidDataException(ConstantError.NOT_EXIST.getMsg()));

        return mapToCryptoDTO(crypto);
    }

    /**
     * Function to create a crypto
     *
     * @param cryptoDTO object to be created
     */
    public void createCrypto(final CryptoDTO cryptoDTO) {

        this.cryptoRepository.findByAcronym(cryptoDTO.getAcronym()).ifPresentOrElse(
                crypto -> {
                    throw new InvalidDataException(ConstantError.ALREADY_EXIST.getMsg());
                },
                () -> cryptoRepository.save(mapToCrypto(cryptoDTO))
        );
    }

    /**
     * Function to delete a crypto
     *
     * @param acronym attribute to search the deleted crypto
     */
    public void deleteCrypto(final String acronym) {

        Crypto crypto = this.cryptoRepository.findByAcronym(acronym).orElseThrow(() ->
                new InvalidDataException(ConstantError.NOT_EXIST.getMsg()));

        cryptoRepository.delete(crypto);
    }

    /**
     * Function to map from DTO to entity
     *
     * @param cryptoDTO
     * @return Object {Crypto}
     */
    private Crypto mapToCrypto(CryptoDTO cryptoDTO) {
        return new DozerBeanMapper().map(cryptoDTO, Crypto.class);
    }

    /**
     * Function to map from entity to DTO
     *
     * @param crypto
     * @return Object {CryptoDTO}
     */
    private CryptoDTO mapToCryptoDTO(Crypto crypto) {
        return new DozerBeanMapper().map(crypto, CryptoDTO.class);
    }
}
