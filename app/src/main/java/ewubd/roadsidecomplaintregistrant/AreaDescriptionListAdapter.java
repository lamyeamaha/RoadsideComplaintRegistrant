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

public class AreaDescriptionListAdapter extends ArrayAdapter<Complaint> {

    private  LayoutInflater inflater;    // inflater used to access the views of a layout
    private int resourceId;




    public AreaDescriptionListAdapter(@NonNull Context context, int resource, @NonNull List<Complaint> items) {
        super(context, resource, items);
        this.resourceId = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    // getView is a member function of ArrayAdapter. gets called as many times as the number of rows in items


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {     // gets called for every row
//        return super.getView(position, convertView, parent);


        View rowView = inflater.inflate(resourceId, parent, false);  // firstly, we access the empty template(access layout-work of inflater)
        TextView issue = rowView.findViewById(R.id.tvIssue);
        TextView problemDescription = rowView.findViewById(R.id.tvProblemDescription);   // now access the elments inside that layout
//        TextView address = rowView.findViewById(R.id.tvAddress);
        TextView urgency = rowView.findViewById(R.id.tvUrgency);
        TextView review = rowView.findViewById(R.id.tvReview);



        Complaint c = this.getItem(position);
        issue.setText(c.issue);
        problemDescription.setText(c.problem_faced);
//        address.setText(c.address);
        urgency.setText(c.urgency);
        review.setText(c.review);


        return rowView;

    }
}