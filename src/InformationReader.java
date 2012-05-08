import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InformationReader {
	List<Information> informationList = new ArrayList<Information>();
	
	public InformationReader(String resourceName) {
		InputStream file = getClass().getResourceAsStream(resourceName);
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
				Information information = new Information(info[0], info[1], Integer.parseInt(info[2]));
				informationList.add(information);
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

	public List<Information> getInformationList() {
		return informationList;
	}

	public void setInformationList(List<Information> informationList) {
		this.informationList = informationList;
	}
	

}