package com.twogether.exercise.crypto.repository;

import com.twogether.exercise.crypto.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Long> {

    /**
     * Function to find crypto by acronym
     *
     * @param acronym attribute to search
     *
     * @return Object {Crypto} optional
     */
    Optional<Crypto> findByAcronym(String acronym);

}
