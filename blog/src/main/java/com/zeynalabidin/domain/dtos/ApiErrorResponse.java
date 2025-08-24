package com.zeynalabidin.domain.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

	private int status;

	private String message;

	private List<FailedError> errors;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FailedError {
		private String field;

		private String message;
	}

}
