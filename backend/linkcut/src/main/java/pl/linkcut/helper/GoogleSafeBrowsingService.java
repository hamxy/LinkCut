package pl.linkcut.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service to interact with the Google Safe Browsing API.
 * <p>
 * This service checks if a given URL is safe by sending requests
 * to the Google Safe Browsing API and analyzing the response.
 * </p>
 */
@Service
public class GoogleSafeBrowsingService {

    // The base URL for the Google Safe Browsing API
    private static final String API_URL = "https://safebrowsing.googleapis.com/v4/threatMatches:find";

    // The API key for accessing the Google Safe Browsing API, injected from application properties
    @Value("${google.safe.browsing.api.key}")
    private String apiKey;

    // RestTemplate is used to send HTTP requests to external APIs
    private final RestTemplate restTemplate;

    /**
     * Constructor to inject RestTemplate.
     *
     * @param restTemplate the RestTemplate bean to perform HTTP requests
     */
    public GoogleSafeBrowsingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Checks if the given URL is safe using the Google Safe Browsing API.
     *
     * @param url the URL to be checked for safety
     * @return true if the URL is safe, false if it is unsafe or if the API call fails
     */
    public boolean isSafeUrl(String url) {
        // Construct the API endpoint with the API key as a query parameter
        String uri = API_URL + "?key=" + apiKey;

        // Build the JSON payload for the API request
        // The payload specifies the client details and the URL to be checked
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

        try {
            // Send a POST request to the Google Safe Browsing API
            ResponseEntity<String> response = restTemplate.exchange(
                    uri,                          // API endpoint
                    HttpMethod.POST,              // HTTP method
                    new HttpEntity<>(requestBody, new HttpHeaders()), // Request body and headers
                    String.class                  // Expected response type
            );

            // Check if the response status is in the 2xx range (successful response)
            if (response.getStatusCode().is2xxSuccessful()) {
                // Determine if the response contains "matches", indicating an unsafe URL
                boolean isUnsafe = response.getBody().contains("matches");
                return !isUnsafe; // Return true if no "matches" are found
            }
        } catch (Exception e) {
            // Log the exception to the console for debugging purposes
            System.err.println("Error checking URL safety: " + e.getMessage());
        }

        // If there is an error or unexpected response, consider the URL unsafe
        return false;
    }
}