package ewubd.roadsidecomplaintregistrant;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


@SuppressWarnings("ALL")
public class UnreviewedComplaintUtil {

    private UnreviewedComplaintUtil() {}

    private static UnreviewedComplaintUtil instance = new UnreviewedComplaintUtil();

    public static UnreviewedComplaintUtil getInstance() {

        return instance;
    }
    //









    @SuppressWarnings("deprecation")
    public String readFromfile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
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

    public void setComplaintKeyValue(Context context, String key, String value) {
        try{
            UnreviewedComplaintTable db = new UnreviewedComplaintTable(context);
            db.updateValueByComplaintKey(key, value);
            db.close();
        } catch (Exception e) {}
    }

    public void deleteByComplaintKey(Context context, String key){
        try{
            UnreviewedComplaintTable db = new UnreviewedComplaintTable(context);
            db.deleteDataByComplaintKey(key);
            db.close();
        } catch (Exception e) {}
    }

    public String getValueByComplaintKey(Context context, String key) {
        UnreviewedComplaintTable db = new UnreviewedComplaintTable(context);
        String value = db.getValueByComplaintKey(key);
        db.close();
        return value;
    }
}