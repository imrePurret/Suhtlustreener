
public class Information {
	String keyWord;
	String sphereClass;
	int answerIndex;
	
	public Information(String keyWord, String sphereClass, int answerIndex){
		this.keyWord = keyWord;
		this.sphereClass = sphereClass;
		this.answerIndex = answerIndex;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getSphereClass() {
		return sphereClass;
	}

	public void setSphereClass(String sphereClass) {
		this.sphereClass = sphereClass;
	}

	public int getAnswerIndex() {
		return answerIndex;
	}

	public void setAnswerIndex(int answerIndex) {
		this.answerIndex = answerIndex;
	}

	@Override
	public String toString() {
		return "Information [keyWord=" + keyWord + ", sphereClass="
				+ sphereClass + ", answerIndex=" + answerIndex + "]";
	}
}
