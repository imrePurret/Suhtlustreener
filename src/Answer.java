
public class Answer {
	String answer;
	int answerIndex;
	
	public Answer(String answer, int answerIndex){
		this.answer = answer;
		this.answerIndex = answerIndex;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getAnswerIndex() {
		return answerIndex;
	}

	public void setAnswerIndex(int answerIndex) {
		this.answerIndex = answerIndex;
	}

	@Override
	public String toString() {
		return "Answer [answer=" + answer + ", answerIndex=" + answerIndex
				+ "]";
	}
	
}
