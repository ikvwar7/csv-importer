package csv.importer.domain.report;

public class TopFiveReport {
    private String formId;
    private Long amount;

    public TopFiveReport(String formId, Long amount) {
        this.formId = formId;
        this.amount = amount;
    }

    public String getFormId() {
        return formId;
    }

    public Long getAmount() {
        return amount;
    }
}
