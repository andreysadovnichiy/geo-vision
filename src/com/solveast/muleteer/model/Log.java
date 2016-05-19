package com.solveast.muleteer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Log {
	@Id
	@GeneratedValue
	private int id;

	private String message;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = new StringBuilder()
				.append(new Date())
				.append(" : ")
				.append(message)
				.toString();
	}

	@Override
	public String toString() {
		return "Log: [message=" + message + "]";
	}
}
