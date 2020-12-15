package com.rahul.Coronavirustracker.Services;

import com.rahul.Coronavirustracker.models.LocationsStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.HttpResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationsStats> allStats=new ArrayList<LocationsStats>();
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException,InterruptedException {

        List<LocationsStats> newStats=new ArrayList<>();
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse=client.send(request,HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader=new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationsStats locationsStats=new LocationsStats();
            locationsStats.setState(record.get("Province/State"));
            locationsStats.setCountry(record.get("Country/Region"));
            int latestCases=Integer.valueOf(record.get(record.size()-1));
            int prevDayCases=Integer.valueOf(record.get(record.size()-2));
            locationsStats.setDiffFromPrevDay(latestCases-prevDayCases);
            locationsStats.setLatestTotalCases(Integer.valueOf(record.get(record.size()-1)));
            newStats.add(locationsStats);
        }
        this.allStats=newStats;
    }

    public List<LocationsStats> getAllStats() {
        return allStats;
    }
}
