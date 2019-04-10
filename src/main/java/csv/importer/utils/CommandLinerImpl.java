package csv.importer.utils;


import csv.importer.service.CSVParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommandLinerImpl implements CommandLineRunner {

    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS TEST_CASE (" +
            "id serial PRIMARY KEY,\n" +
            "ssoid varchar (50),\n" +
            "ts TIMESTAMP,\n" +
            "grp varchar(50),\n" +
            "type varchar(50),\n" +
            "sub_type varchar(50),\n" +
            "url varchar (200),\n" +
            "org_id varchar (200),\n" +
            "form_id varchar (200),\n" +
            "code varchar (200),\n" +
            "ltpa varchar (200),\n" +
            "sudirresponse varchar (200),\n" +
            "ymdh TIMESTAMP)";

    private final String DELETE_DATA = "DELETE FROM TEST_CASE";

    private final JdbcTemplate jdbcTemplate;
    private final CSVParserService csvParserService;

    @Autowired
    public CommandLinerImpl(JdbcTemplate jdbcTemplate, CSVParserService csvParserService) {
        this.jdbcTemplate = jdbcTemplate;
        this.csvParserService = csvParserService;
    }

    private void prepareTable() {
        jdbcTemplate.execute(CREATE_TABLE);
        jdbcTemplate.execute(DELETE_DATA);
    }

    @Override
    public void run(String... strings) {
        prepareTable();
        csvParserService.parse();
    }
}