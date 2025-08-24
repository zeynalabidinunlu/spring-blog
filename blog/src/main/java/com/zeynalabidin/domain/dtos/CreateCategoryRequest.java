package com.zeynalabidin.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

	@NotBlank(message = "Category name is required")
	@Size(min = 2, max = 50, message = "Category name must be between {min} and {max} characters")
	@Pattern(regexp = "^[\\w\\s]+$",message = "Category name can only letters , numbers,spaces and hypnes")
	private String name;

}
