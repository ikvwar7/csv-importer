package csv.importer.domain.report;


public class NotEndedUserActivityReport {
    private String ssoid;
    private String formId;
    private String subType;
    private String grp;

    public NotEndedUserActivityReport(String ssoid, String formId, String subType, String grp) {
        this.ssoid = ssoid;
        this.formId = formId;
        this.subType = subType;
        this.grp = grp;
    }

    public String getSsoid() {
        return ssoid;
    }

    public String getFormId() {
        return formId;
    }

    public String getSubType() {
        return subType;
    }

    public String getGrp() {
        return grp;
    }
}
