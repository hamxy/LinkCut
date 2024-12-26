package pl.linkcut.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleSafeBrowsingService {

    // The URL for the Google Safe Browsing API
    private static final String API_URL = "https://safebrowsing.googleapis.com/v4/threatMatches:find";

    // The API key for accessing the Google Safe Browsing API
    @Value("${google.safe.browsing.api.key}")
    private String apiKey;

    // RestTemplate is used to send HTTP requests
    private final RestTemplate restTemplate;

    // Constructor to inject RestTemplate
    public GoogleSafeBrowsingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Checks if the given URL is safe using Google Safe Browsing API.
     *
     * @param url the URL to be checked
     * @return true if the URL is safe, false if it is potentially unsafe
     */
    public boolean isSafeUrl(String url) {
        // Construct the API request URL with the API key as a query parameter
        String uri = API_URL + "?key=" + apiKey;

        // Create the request body as a JSON string
        String requestBody = "{"
                + "\"client\": {"
                + "\"clientId\": \"your-client-id\","
                + "\"clientVersion\": \"1.0\""
                + "},"
                + "\"threatInfo\": {"
                + "\"threatTypes\": [\"MALWARE\", \"SOCIAL_ENGINEERING\", \"UNWANTED_SOFTWARE\", \"POTENTIALLY_HARMFUL_APPLICATION\"],"
                + "\"platformTypes\": [\"ANY_PLATFORM\"],"
                + "\"threatEntryTypes\": [\"URL\"],"
                + "\"threatEntries\": [{\"url\": \"" + url + "\"}]"
                + "}"
                + "}";

        // Perform the HTTP POST request using RestTemplate
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(requestBody, new HttpHeaders()),
                String.class
        );

        // If the response is successful (status 2xx), check if it contains the "matches" field
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().contains("matches");
        }

        // If the response is not successful, return false (unsafe URL)
        return false;
    }
}