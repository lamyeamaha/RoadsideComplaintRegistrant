package ewubd.roadsidecomplaintregistrant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class FeedbackListAdapter extends ArrayAdapter<Feedback> {
    private LayoutInflater inflater;    // inflater used to access the views of a layout
    private int resourceId;
public FeedbackListAdapter(@NonNull Context c, int r, @NonNull List<Feedback> i){
    super(c,r,i);

        this.resourceId = r;
    inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


}


    // getView is a member function of ArrayAdapter. gets called as many times as the number of rows in items


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {     // gets called for every row
//        return super.getView(position, convertView, parent);


        View rowView = inflater.inflate(resourceId, parent, false);  // firstly, we access the empty template(access layout-work of inflater)
        TextView fdbk = rowView.findViewById(R.id.tvFeedback);
        TextView comment = rowView.findViewById(R.id.tvComment);   // now access the elments inside that layout


        //
        Feedback x = this.getItem(position);
        fdbk.setText(x.fdbk);
        comment.setText(x.comment);



        return rowView;

    }
}