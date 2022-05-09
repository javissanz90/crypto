package com.twogether.exercise.crypto.service;

import com.twogether.exercise.crypto.entity.Crypto;
import com.twogether.exercise.crypto.dto.CryptoDTO;
import com.twogether.exercise.crypto.exception.ConstantError;
import com.twogether.exercise.crypto.exception.InvalidDataException;
import com.twogether.exercise.crypto.repository.CryptoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CryptoServiceTest {

    @Mock
    private CryptoRepository cryptoRepository;
    private CryptoService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CryptoService(cryptoRepository);
    }


    @Test
    void searchCrypto() {
        //given
        Crypto crypto = new Crypto(1L, "Bitcoin", "BTC", 36000L, true);
        given(cryptoRepository.findByAcronym(anyString())).willReturn(Optional.of(crypto));

        //when
        underTest.searchCrypto(anyString());

        //then
        verify(cryptoRepository).findByAcronym(anyString());
    }

    @Test
    void willThrowWhenSearchAndAcronymNotExist() {
        //given
        String acronym = "BTC";
        Crypto crypto = new Crypto(1L, "Bitcoin", acronym, 36000L, true);
        given(cryptoRepository.findByAcronym(anyString())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.searchCrypto(acronym))
                .isInstanceOf(InvalidDataException.class)
                .hasMessageContaining(ConstantError.NOT_EXIST.getMsg());
        verify(cryptoRepository, never()).save(any());

    }

    @Test
    void canCreateCrypto() {
        //given
        CryptoDTO cryptoDTO = new CryptoDTO(1L, "Bitcoin", "BTC", 36000L, true);

        //when
        underTest.createCrypto(cryptoDTO);

        //then
        ArgumentCaptor<Crypto> argumentCaptor = ArgumentCaptor.forClass(Crypto.class);
        verify(cryptoRepository).save(argumentCaptor.capture());

        Crypto capturedCrypto = argumentCaptor.getValue();
        assertThat(capturedCrypto.getAcronym()).isEqualTo(cryptoDTO.getAcronym());
    }

    @Test
    void willThrowWhenCreateAndAcronymAlreadyExist() {
        //given
        Crypto crypto = new Crypto(1L, "Bitcoin", "BTC", 36000L, true);
        CryptoDTO cryptoDTO = new CryptoDTO(1L, "Bitcoin", "BTC", 36000L, true);
        given(cryptoRepository.findByAcronym(anyString())).willReturn(Optional.of(crypto));

        //when
        //then
        assertThatThrownBy(() -> underTest.createCrypto(cryptoDTO))
                .isInstanceOf(InvalidDataException.class)
                .hasMessageContaining(ConstantError.ALREADY_EXIST.getMsg());
        verify(cryptoRepository, never()).save(any());

    }

    @Test
    @Disabled
    void deleteCrypto() {
    }
}