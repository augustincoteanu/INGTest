package ro.ing.store.db.services;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDetails {
	private Date timestamp;
	private String message;
	private String details;

	public MessageDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
}
