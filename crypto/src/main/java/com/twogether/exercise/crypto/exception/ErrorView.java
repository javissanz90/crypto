package com.twogether.exercise.crypto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorView {

	/**
	 * Mensaje with the message to show
	 */
	private String errorMessage;
}
