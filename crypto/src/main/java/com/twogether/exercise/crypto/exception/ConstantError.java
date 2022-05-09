package com.twogether.exercise.crypto.exception;

import lombok.Getter;

@Getter
public enum ConstantError {
    ALREADY_EXIST("The acronym already exist"),
    NOT_EXIST("The acronym does not exist in the DB");

    /**
     * Messahe with the error to validate
     */
    private String msg;

    ConstantError(final String msg){
        this.msg = msg;
    }

}
