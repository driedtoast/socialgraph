package org.socialgraph.model;

public class Account extends AbstractModel {

	private String domain;	// string	The top-most authoritative domain for this account, e.g. "twitter.com". This is the Primary Sub-Field for this field, for the purposes of sorting and filtering.
	private String username; //	string	An alphanumeric user name, usually chosen by the user, e.g. "jsmarr".
	private String userId;	// Object-Id	A user ID associated with this account.
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
