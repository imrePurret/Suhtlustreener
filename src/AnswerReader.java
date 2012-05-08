import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AnswerReader {
	List<Answer> answerList = new ArrayList<Answer>();
	
	public AnswerReader(String answersSource) {
		InputStream file = getClass().getResourceAsStream(answersSource);
		StringBuffer contents = new StringBuffer();
		BufferedReader reader = null;
	    InputStreamReader isr = new InputStreamReader(file);
		try {
			reader = new BufferedReader(isr);
			String text = null;
			// repeat until all lines is read
			while ((text = reader.readLine()) != null) {
				contents.append(text).append(
						System.getProperty("line.separator"));
				String[] info = text.split(";");
				Answer answer = new Answer(info[0], Integer.parseInt(info[1]));
				answerList.add(answer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
	

}