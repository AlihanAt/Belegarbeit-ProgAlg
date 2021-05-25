public class LanguageResult {
	private Language language;
	private String topWord;

	public LanguageResult(Language language, String topWord) {
		this.language = language;
		this.topWord = topWord;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getTopWord() {
		return topWord;
	}

	public void setTopWord(String topWord) {
		this.topWord = topWord;
	}

	public int getLength(){
		return topWord.length();
	}

	@Override
	public String toString() {
		return getLanguage()+ "  " + getTopWord() + " " + getLength();
	}
}
