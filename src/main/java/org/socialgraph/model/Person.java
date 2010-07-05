package org.socialgraph.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Details from Open Social Spec
 * 
 * Each person returned MUST include the "id", "name", and "thumbnailUrl" fields
 * with non-empty values, but all other fields are optional, and it is
 * recognized that not all Service Providers are able to provide data for all
 * the supported fields. The field list below is broad so that there is a
 * standard field name available among Service Providers that do support any of
 * these fields.
 * 
 * There are two special Person objects that can be requested directly: the
 * VIEWER and the OWNER. To understand the distinction, imagine you're checking
 * out a coworker's profile on Orkut. In this case, you are the VIEWER and your
 * coworker is the OWNER. It's also common to view your own profile, in which
 * case you are both the VIEWER and the OWNER, and some applications may choose
 * to handle this case differently. OpenSocial also provides for the case of
 * anonymous viewing, where the gadget will not be able to access the VIEWER's
 * information.
 * 
 * 
 * 
 * @author driedtoast
 * 
 */
public class Person extends AbstractModel  {

	public static final String DISPLAY_NAME="displayName";
	
	
	private List<Account> accounts; // Plural-Field <Account> An online account
									// held by this Person.

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	private String aboutMe; // string A general statement about the person.
	private List<String> activities; // Plural-Field <string> Person's favorite
										// activities.
	private List<Address> addresses; // Plural-Field <Address> A physical
										// mailing address for this Person.
	private Integer age; // number The age of this person. Sometimes sites might
							// want to show age without revealing the specific
							// birthday.
	private Map<String, String> appData; // Plural-Field <AppData> A collection
											// of AppData keys and values.
	private Date birthday; // Date The birthday of this person. The value MUST
							// be a valid Date. The year value MAY be set to
							// 0000 when the age of the Person is private or the
							// year is not available.

	// TODO make this a relationship model
	// connected Boolean Boolean value indicating whether the user and this
	// Person have established a bi-directionally asserted connection of some
	// kind on the Service Provider's service. The value MUST be either true or
	// false. The value MUST be true if and only if there is at least one value
	// for the relationship field, described below, and is thus intended as a
	// summary value indicating that some type of bi-directional relationship
	// exists, for Consumers that aren't interested in the specific nature of
	// that relationship. For traditional address books, in which a user stores
	// information about other contacts without their explicit acknowledgment,
	// or for services in which users choose to "follow" other users without
	// requiring mutual consent, this value will always be false.
	private String displayName; // string Required. The name of this Person,
								// suitable for display to end-users. Each
								// Person returned MUST include a non-empty
								// displayName value. The name SHOULD be the
								// full name of the Person being described if
								// known (e.g. Cassandra Doll or Mrs. Cassandra
								// Lynn Doll, Esq.), but MAY be a username or
								// handle, if that is all that is available
								// (e.g. doll). The value provided SHOULD be the
								// primary textual label by which this Person is
								// normally displayed by the Service Provider
								// when presenting it to end-users.
	private List<String> emails; // Plural-Field <string> E-mail address for
									// this Person. The value SHOULD be
									// canonicalized by the Service Provider,
									// e.g.joseph@plaxo.com instead of
									// joseph@PLAXO.COM.
	private String ethnicity; // string Person's ethnicity.
	private String gender; // string The gender of this person. Service
							// Providers SHOULD return one of the following
							// Canonical Values, if appropriate:male, female, or
							// undisclosed, and MAY return a different value if
							// it is not covered by one of these Canonical
							// Values.
	private Boolean hasApp; // Boolean Indicating whether the user has
							// application installed.
	
	private List<String> ims; // Plural-Field <string> Instant messaging address
								// for this Person. No official canonicalization
								// rules exist for all instant messaging
								// addresses, but Service Providers SHOULD
								// remove all whitespace and convert the address
								// to lowercase, if this is appropriate for the
								// service this IM address is used for. Instead
								// of the standard Canonical Values for type,
								// this field defines the following Canonical
								// Values to represent currently popular IM
								// services: aim, gtalk, icq, xmpp,msn, skype,
								// qq, and yahoo.
	private List<String> interests; // Plural-Field <string> Person's interests,
									// hobbies or passions.
	private List<String> languagesSpoken; // Plural-Field <string> List of the
											// languages that the person speaks
											// as ISO 639-1 codes.
	private String location; // string
	private String name; // Name The broken-out components and fully formatted
							// version of the person's real name.
	private String networkPresence; // Plural-Field <string> Person's current
									// network status. Specified as one of:
									// AWAY, CHAT, DND, OFFLINE, ONLINE OR XA.
	private String nickname; // string The casual way to address this Person in
								// real life, e.g. "Bob" or "Bobby" instead of
								// "Robert". This field SHOULD NOT be used to
								// represent a user's username (e.g. jsmarr or
								// daveman692); the latter should be represented
								// by the preferredUsername field.
	private String note; // string Notes about this person, with an unspecified
							// meaning or usage (normally notes by the user
							// about this person). This field MAY contain
							// newlines.
	private List<Organization> organizations; // Plural-Field <Organization> A
												// current or past
												// organizational affiliation of
												// this Person.
	private List<String> phoneNumbers; // Plural-Field <string> Phone number for
										// this Person. No canonical value is
										// assumed here. In addition to the
										// standard Canonical Values for type,
										// this field also defines the
										// additional Canonical Values mobile,
										// fax, and pager.

	// TODO break out separately
	// / photos Plural-Field <string> URL of a photo of this person. The value
	// SHOULD be a canonicalized URL, and MUST point to an actual image file
	// (e.g. a GIF, JPEG, or PNG image file) rather than to a web page
	// containing an image. Service Providers MAY return the same image at
	// different sizes, though it is recognized that no standard for describing
	// images of various sizes currently exists. Note that this field SHOULD NOT
	// be used to send down arbitrary photos taken by this user, but
	// specifically profile photos of the contact suitable for display when
	// describing the contact.
	private String profileUrl; // string Person's profile URL, specified as a
								// string. This URL must be fully qualified.
								// Relative URLs will not work in gadgets.
	private Long published; // Date The date this Person was first added to the
							// user's address book or friends list (i.e. the
							// creation date of this entry). The value MUST be a
							// valid Date.
	// TODO relationship based on the graph
	// relationships Plural-Field <string> A bi-directionally asserted
	// relationship type that was established between the user and this person
	// by the Service Provider. The value SHOULD conform to one of the XFN
	// relationship values (e.g. kin, friend, contact, etc.) if appropriate, but
	// MAY be an alternative value if needed. Note this field is a parallel set
	// of category labels to the tags field, but relationships MUST have been
	// bi-directionally confirmed, whereas tags are asserted by the user without
	// acknowledgment by this Person. Note that this field consists only of a
	// string value.
	private String relationshipStatus; // string Person's relationship status.

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public List<String> getActivities() {
		return activities;
	}

	public void setActivities(List<String> activities) {
		this.activities = activities;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Map<String, String> getAppData() {
		return appData;
	}

	public void setAppData(Map<String, String> appData) {
		this.appData = appData;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getHasApp() {
		return hasApp;
	}

	public void setHasApp(Boolean hasApp) {
		this.hasApp = hasApp;
	}



	public List<String> getIms() {
		return ims;
	}

	public void setIms(List<String> ims) {
		this.ims = ims;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public List<String> getLanguagesSpoken() {
		return languagesSpoken;
	}

	public void setLanguagesSpoken(List<String> languagesSpoken) {
		this.languagesSpoken = languagesSpoken;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetworkPresence() {
		return networkPresence;
	}

	public void setNetworkPresence(String networkPresence) {
		this.networkPresence = networkPresence;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public Long getPublished() {
		return published;
	}

	public void setPublished(Long published) {
		this.published = published;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String getUtcOffset() {
		return utcOffset;
	}

	public void setUtcOffset(String utcOffset) {
		this.utcOffset = utcOffset;
	}

	private String status; // string Person's status, headline or shoutout.
	private List<String> tags; // Plural-Field <string> A user-defined category
								// label for this person, e.g. "favorite" or
								// "web20". These values SHOULD be
								// case-insensitive, and there SHOULD NOT be
								// multiple tags provided for a given person
								// that differ only in case. Note that this
								// field consists only of a string value.
	private String thumbnailUrl; // string Person's photo thumbnail URL,
									// specified as a string. This URL must be
									// fully qualified. Relative URLs will not
									// work in gadgets.
	private Long updated; // Date The most recent date the details of this
							// Person were updated (i.e. the modified date of
							// this entry). The value MUST be a valid Date. If
							// this Person has never been modified since its
							// initial creation, the value MUST be the same as
							// the value of published. Note the updatedSince
							// Query Parameter can be used to select only people
							// whose updated value is equal to or more recent
							// than a given Date. This enables Consumers to
							// repeatedly access a user's data and only request
							// newly added or updated contacts since the last
							// access time.
	private List<String> urls; // Plural-Field <string> URL of a web page
								// relating to this Person. The value SHOULD be
								// canonicalized by the Service Provider,
								// e.g.http://josephsmarr.com/about/ instead of
								// JOSEPHSMARR.COM/about/. In addition to the
								// standard Canonical Values for type, this
								// field also defines the additional Canonical
								// Values blog and profile.
	private String utcOffset; // Date-UTC-Offset The offset from UTC of this
								// Person's current time zone, as of the time
								// this response was returned. The value MUST
								// conform to the Date-UTC-Offset. Note that
								// this value MAY change over time due to
								// daylight saving time, and is thus meant to
								// signify only the current value of the user's
								// timezone offset.

}
