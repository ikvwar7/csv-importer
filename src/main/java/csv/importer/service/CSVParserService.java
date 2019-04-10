package csv.importer.service;

import csv.importer.dao.TestCaseDao;
import csv.importer.domain.TestCase;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CSVParserService {

    private final Logger logger = LoggerFactory.getLogger(CSVParserService.class);

    private final TestCaseDao testCaseDao;

    @Autowired
    public CSVParserService(TestCaseDao testCaseDao) {
        this.testCaseDao = testCaseDao;
    }

    public void parse() {
        logger.info("Loading data...");
        Resource cp = new ClassPathResource("static/test_case.csv");
        try (Reader in = new BufferedReader(new InputStreamReader(cp.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String row = record.get(0);
                parseRow(row);
            }

            logger.info("End loading data");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private TestCase parseRow(String row) {
        TestCase testCase;

        if (row.contains("\"")) {
            String[] splittedOnThreeParts = row.split("\"");
            String[] rowBeforeUrl = splittedOnThreeParts[0].split(";");
            String[] rowAfterUrl = splittedOnThreeParts[2].split(";");

            testCase = new TestCase(
                    rowBeforeUrl[0],
                    Instant.ofEpochSecond(Long.parseLong(rowBeforeUrl[1])),
                    rowBeforeUrl[2],
                    rowBeforeUrl[3],
                    rowBeforeUrl[4],
                    splittedOnThreeParts[1],
                    rowAfterUrl[1],
                    rowAfterUrl[2],
                    rowAfterUrl[3],
                    rowAfterUrl[4],
                    rowAfterUrl[5],
                    LocalDateTime.parse(rowAfterUrl[6], DateTimeFormatter.ofPattern("yyyy-MM-dd-HH")));
        } else {
            String[] splitedRow = row.split(";");
            testCase = new TestCase(
                    splitedRow[0],
                    Instant.ofEpochSecond(Long.parseLong(splitedRow[1])),
                    splitedRow[2],
                    splitedRow[3],
                    splitedRow[4],
                    splitedRow[5],
                    splitedRow[6],
                    splitedRow[7],
                    splitedRow[8],
                    splitedRow[9],
                    splitedRow[10],
                    LocalDateTime.parse(splitedRow[11], DateTimeFormatter.ofPattern("yyyy-MM-dd-HH")));
        }

        testCaseDao.save(testCase);
        return testCase;
    }
}