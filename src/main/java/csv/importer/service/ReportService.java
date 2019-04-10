package csv.importer.service;

import csv.importer.dao.TestCaseDao;
import csv.importer.domain.report.LastHourActivityReport;
import csv.importer.domain.report.NotEndedUserActivityReport;
import csv.importer.domain.report.TopFiveReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final TestCaseDao testCaseDao;

    @Autowired
    public ReportService(TestCaseDao testCaseDao) {
        this.testCaseDao = testCaseDao;
    }

    public Map<String, List<LastHourActivityReport>> getUserActivityForLastHour() {
        return testCaseDao.getUserActivityForLastHour();
    }

    public List<TopFiveReport> getTopFiveForm() {
        return testCaseDao.getTopFiveForms();
    }

    public Map<String, List<NotEndedUserActivityReport>> getNotEndedUserActivity() {
        return testCaseDao.getNotEndedUserActivity();
    }
}
