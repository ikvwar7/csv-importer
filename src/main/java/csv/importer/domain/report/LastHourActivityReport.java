package csv.importer.domain.report;

public class LastHourActivityReport {
    private String ssoId;
    private String formId;

    public LastHourActivityReport(String ssoId, String formId) {
        this.ssoId = ssoId;
        this.formId = formId;
    }

    public String getSsoId() {
        return ssoId;
    }

    public String getFormId() {
        return formId;
    }
}
