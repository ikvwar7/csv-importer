package csv.importer.dao;

import csv.importer.domain.TestCase;
import csv.importer.domain.report.LastHourActivityReport;
import csv.importer.domain.report.NotEndedUserActivityReport;
import csv.importer.domain.report.TopFiveReport;

import java.util.List;
import java.util.Map;

public interface TestCaseDao {
    void save(TestCase testCase);

    Map<String, List<LastHourActivityReport>> getUserActivityForLastHour();

    Map<String, List<NotEndedUserActivityReport>> getNotEndedUserActivity();

    List<TopFiveReport> getTopFiveForms();
}
