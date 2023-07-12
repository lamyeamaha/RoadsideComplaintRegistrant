package ewubd.roadsidecomplaintregistrant;

public class Complaint {
    public String complaint_id;
    public String issue = "";
    public String address = "";
    public String problem_faced = "";
    public String urgency = "";
    public String review = "";

    public Complaint(String complaint_id, String issue, String address, String problem_faced, String urgency, String review){
        this.complaint_id = complaint_id;
        this.issue = issue;
        this.address = address;
        this.problem_faced = problem_faced;
        this.urgency = urgency;
        this.review = review;
    }

}
