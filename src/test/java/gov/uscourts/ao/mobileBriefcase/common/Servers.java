package gov.uscourts.ao.mobileBriefcase.common;

public class Servers {

	private String Server;

	public String getServer() {
		return Server;
	}

	public void setServer(String server) {
		Server = server;

	}

	public enum servers {
		CMKA, CM5A, CMLA
	}

	public enum id {
		WILLIAMS_PE_ID, COLLOTONS_PE_ID
	}
}
