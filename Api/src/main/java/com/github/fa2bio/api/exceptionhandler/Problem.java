package com.github.fa2bio.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem{

	private Integer status;
	private LocalDateTime timestamp;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private Set<Object> objects;
	
	@Getter
	@Builder
	public static class Object {
		
		private String name;
		private String userMessage;
		
		@Override
		public int hashCode() {
			return Objects.hash(name, userMessage);
		}
		@Override
		public boolean equals(java.lang.Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Object other = (Object) obj;
			return Objects.equals(name, other.name) && Objects.equals(userMessage, other.userMessage);
		}

	}

}
