package ewubd.roadsidecomplaintregistrant;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@SuppressWarnings("ALL")
public class UserFeedbackUtil {
	private static UserFeedbackUtil instance = new UserFeedbackUtil();
	private UserFeedbackUtil() {}
	public static UserFeedbackUtil getInstance() {
		return instance;
	}
	//

	@SuppressWarnings("deprecation")
	public String readFromfile(String fileName, Context c) {
		StringBuilder returnString = new StringBuilder();
		InputStream fIn = null;
		InputStreamReader isr = null;
		BufferedReader input = null;
		try {
			fIn = c.getResources().getAssets()
					.open(fileName, Context.MODE_WORLD_READABLE);
			isr = new InputStreamReader(fIn);
			input = new BufferedReader(isr);
			String line = "";
			while ((line = input.readLine()) != null) {
				returnString.append(line + "\n");
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			try {
				if (isr != null)
					isr.close();
				if (fIn != null)
					fIn.close();
				if (input != null)
					input.close();
			} catch (Exception e2) {
				//e2.printStackTrace();
			}
		}
		return returnString.toString().trim();
	}

	public void setKeyValue(Context c, String k, String v) {
		try{
			UserFeedbackTable db = new UserFeedbackTable(c);
			db.updateValueByKey(k, v);
			db.close();
		} catch (Exception e) {}
	}

	public void deleteByKey(Context c, String k){
		try{
			UserFeedbackTable db = new UserFeedbackTable(c);
			db.deleteDataByKey(k);
			db.close();
		} catch (Exception e) {}
	}

	public String getValueByKey(Context c, String k) {
		UserFeedbackTable db = new UserFeedbackTable(c);
		String v = db.getValueByKey(k);
		db.close();
		return v;
	}
}