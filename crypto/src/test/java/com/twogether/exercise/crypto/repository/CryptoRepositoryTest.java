package com.twogether.exercise.crypto.repository;

import com.twogether.exercise.crypto.entity.Crypto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CryptoRepositoryTest {

    @Autowired
     private CryptoRepository underTest;

    @Test
    void itShouldFindByAcronym() {
        //given
        String acronym = "BTC";
        Crypto crypto = new Crypto(1L, "Bitcoin", acronym, 36000L, true);
        underTest.save(crypto);

        //when
        Optional<Crypto> btc = underTest.findByAcronym(acronym);

        //then
        assertThat(btc.get().getAcronym()).isEqualTo(acronym);
    }

    @Test
    void itShouldNotFindByAcronym() {
        //given
        String acronym = "BTC";

        //when
        Optional<Crypto> btc = underTest.findByAcronym(acronym);

        //then
        assertThat(btc).isEmpty();
    }
}