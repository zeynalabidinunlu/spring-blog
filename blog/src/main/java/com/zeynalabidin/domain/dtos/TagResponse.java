package com.zeynalabidin.domain.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagResponse {

	private UUID id;
	
	private String name;
	
	private Integer postCount;
	
	
}
