package csv.importer.controller;

import csv.importer.domain.report.LastHourActivityReport;
import csv.importer.domain.report.NotEndedUserActivityReport;
import csv.importer.domain.report.TopFiveReport;
import csv.importer.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/lastHour")
    public Map<String, List<LastHourActivityReport>> get() {
        return reportService.getUserActivityForLastHour();
    }

    @GetMapping(value = "/topFive")
    public List<TopFiveReport> getTopFiveForms() {
        return reportService.getTopFiveForm();
    }

    @GetMapping(value = "/notEndedActivity")
    public Map<String, List<NotEndedUserActivityReport>> getFormsNotEnded() {
        return reportService.getNotEndedUserActivity();
    }
}