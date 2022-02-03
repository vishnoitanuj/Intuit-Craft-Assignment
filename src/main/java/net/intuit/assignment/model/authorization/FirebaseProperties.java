package net.intuit.assignment.model.authorization;

import lombok.Data;

@Data
public class FirebaseProperties {

	int sessionExpiryInDays;
	String databaseUrl;
	boolean enableStrictServerSession;
	boolean enableCheckSessionRevoked;
	boolean enableLogoutEverywhere;

}
