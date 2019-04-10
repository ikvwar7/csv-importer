package csv.importer.domain;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "TEST_CASE")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String ssoid;
    private Instant ts;
    private String grp;
    private String type;

    @Column(name = "sub_type")
    private String step;
    private String url;
    private String orgId;
    private String formId;
    private String code;
    private String ltpa;
    private String sudirresponse;
    private LocalDateTime ymdh;

    public TestCase() {
    }

    public TestCase(String ssoid,
                    Instant ts,
                    String grp,
                    String type,
                    String step,
                    String url,
                    String orgId,
                    String formId,
                    String code,
                    String ltpa,
                    String sudirresponse,
                    LocalDateTime ymdh) {
        this.ssoid = ssoid;
        this.ts = ts;
        this.grp = grp;
        this.type = type;
        this.step = step;
        this.url = url;
        this.orgId = orgId;
        this.formId = formId;
        this.code = code;
        this.ltpa = ltpa;
        this.sudirresponse = sudirresponse;
        this.ymdh = ymdh;
    }

    public Long getId() {
        return id;
    }

    public String getSsoid() {
        return ssoid;
    }

    public Instant getTs() {
        return ts;
    }

    public String getGrp() {
        return grp;
    }

    public String getType() {
        return type;
    }

    public String getStep() {
        return step;
    }

    public String getUrl() {
        return url;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getFormId() {
        return formId;
    }

    public String getCode() {
        return code;
    }

    public String getLtpa() {
        return ltpa;
    }

    public String getSudirresponse() {
        return sudirresponse;
    }

    public LocalDateTime getYmdh() {
        return ymdh;
    }
}