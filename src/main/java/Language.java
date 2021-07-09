enum Language {
	DUTCH("dutch"),
	ENGLISH("english"),
	FRENCH("french"),
	GERMAN("german"),
	ITALIAN("italian"),
	RUSSIAN("russian"),
	SPANISH("spanish"),
	UKRAINIAN("ukrainian");

	private final String path;

	Language(String path) {
		this.path = path;
	}

	public String getPath(){
		return "src/main/resources/data/" + this.path;
	}
}
