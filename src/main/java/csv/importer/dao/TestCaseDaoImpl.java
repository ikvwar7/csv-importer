package csv.importer.dao;

import csv.importer.domain.TestCase;
import csv.importer.domain.report.LastHourActivityReport;
import csv.importer.domain.report.NotEndedUserActivityReport;
import csv.importer.domain.report.TopFiveReport;
import csv.importer.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Repository
public class TestCaseDaoImpl implements TestCaseDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final String ACTIVITY_FOR_LAST_HOUR = "SELECT ssoid, form_id FROM TEST_CASE " +
            "WHERE EXTRACT(EPOCH FROM now()) - EXTRACT(EPOCH FROM ts) < 60";

    private final String TOP_FIVE = "SELECT form_id, COUNT(*) From TEST_CASE " +
            "GROUP BY form_id ORDER BY COUNT(*) DESC LIMIT 5";

    private final String NOT_FINISHED_FORMS_1 = "SELECT ssoid, form_id, sub_type, grp FROM TEST_CASE " +
            "WHERE grp ";

    private final String NOT_FINISHED_FORMS_2 = " AND sub_type != ? ";

    private final List<Pair<String, String>> eventTypeAndResult = Arrays.asList(//не для всех видов форм, так как
            new Pair<>("LIKE 'dgi%' ", "send"), // непонятно какой шаг является конечным для различных форм
            new Pair<>("LIKE 'dszn%' ", "send")
    );

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TestCaseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(TestCase testCase) {
        entityManager.persist(testCase);
    }

    @Override
    public Map<String, List<LastHourActivityReport>> getUserActivityForLastHour() {
        List<LastHourActivityReport> report = jdbcTemplate.query(ACTIVITY_FOR_LAST_HOUR,
                (rs, i) -> new LastHourActivityReport(rs.getString(1), rs.getString(2)));

        return report.stream()
                .collect(Collectors.groupingBy(LastHourActivityReport::getSsoId));
    }

    @Override
    public Map<String, List<NotEndedUserActivityReport>> getNotEndedUserActivity() {
        List<NotEndedUserActivityReport> report = new ArrayList<>();
        eventTypeAndResult.forEach(pair -> {
            List<NotEndedUserActivityReport> notEndedUserActivities =
                    jdbcTemplate.query(
                            NOT_FINISHED_FORMS_1 + pair.getKey() + NOT_FINISHED_FORMS_2,
                            new Object[]{pair.getValue()},
                            (rs, i) -> new NotEndedUserActivityReport(
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4)));

            report.addAll(notEndedUserActivities);
        });

        return report.stream()
                .collect(Collectors.groupingBy(NotEndedUserActivityReport::getSsoid));
    }

    @Override
    public List<TopFiveReport> getTopFiveForms() {
        return jdbcTemplate.query(TOP_FIVE, (rs, i) -> new TopFiveReport(
                rs.getString(1),
                rs.getLong(2)));
    }
}
