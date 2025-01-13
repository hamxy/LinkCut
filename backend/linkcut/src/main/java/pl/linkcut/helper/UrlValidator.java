package pl.linkcut.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.linkcut.exception.InvalidUrlException;
import pl.linkcut.exception.UnsafeUrlException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for validating and checking the safety of URLs.
 * <p>
 * This class validates if a given string is a valid URL format
 * and ensures the URL is safe using the Google Safe Browsing API.
 * </p>
 */
@Component
public class UrlValidator {

    private final GoogleSafeBrowsingService safeBrowsingService;

    /**
     * Constructor for dependency injection of {@link GoogleSafeBrowsingService}.
     *
     * @param safeBrowsingService the service used to check URL safety
     */
    @Autowired
    public UrlValidator(GoogleSafeBrowsingService safeBrowsingService) {
        this.safeBrowsingService = safeBrowsingService;
    }

    /**
     * Checks if the given string is a valid URL format.
     * <p>
     * This method uses a regex pattern to validate the URL format,
     * ensuring it matches common URL structures with optional schemes, subdomains, and paths.
     * </p>
     *
     * @param url the string to validate
     * @return true if the string matches a valid URL format, false otherwise
     */
    private boolean isValidUrl(String url) {
        // Regex pattern to validate URL format
        String regex = "^(https?://)?(www\\.)?([\\w-]+\\.)*[\\w-]+\\.\\w{2,3}(/.*)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    /**
     * Checks if the given URL is safe using the Google Safe Browsing API.
     * <p>
     * Delegates the safety check to {@link GoogleSafeBrowsingService}.
     * </p>
     *
     * @param url the URL to check
     * @return true if the URL is deemed safe, false otherwise
     */
    private boolean isSafeUrl(String url) {
        return safeBrowsingService.isSafeUrl(url);
    }

    /**
     * Validates the given URL by checking its format and safety.
     * <p>
     * This method first validates the URL format using {@link #isValidUrl(String)}.
     * If the format is invalid, an {@link InvalidUrlException} is thrown.
     * Next, it checks the URL's safety using {@link #isSafeUrl(String)}.
     * If the URL is unsafe, an {@link UnsafeUrlException} is thrown.
     * </p>
     *
     * @param url the URL to validate
     * @throws InvalidUrlException if the URL format is invalid
     * @throws UnsafeUrlException  if the URL is deemed unsafe
     */
    public void validateUrl(String url) {
        // Validate URL format
        if (!isValidUrl(url)) {
            throw new InvalidUrlException("Provided String is not a valid URL address");
        }

        // Validate URL safety
        if (!isSafeUrl(url)) {
            throw new UnsafeUrlException("Provided URL is not safe");
        }
    }
}