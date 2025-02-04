/**
 * Method that sanitize link
 * @param url - link provided by user
 * @returns trimmed link (for mvp)
 */
export const sanitizeLink = (url: string): string => {
  return url.trim();
};

/**
 *
 * @param url string
 * @returns normalised string
 */
export const normaliseLink = (url: string): string => {
  // Check if url is not empty
  if (url) {
    // Check if url starts with http or https protocol
    if (!/^https?:\/\//i.test(url)) {
      return `https://${url}`;
    }
  }

  return url;
};

/**
 *
 * @param url - link provided by user
 * @returns null if url is valid or string which is error message
 */
export const validateLink = (url: string): string | null => {
  // Check if link is empty
  if (!url) {
    return "Link cannot be empty";
  }

  // Check if url matches regex
  const pattern = new RegExp(
    "^(https?:\\/\\/)?" + // protocol
      "((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|" + // domain name
      "((\\d{1,3}\\.){3}\\d{1,3}))" + // OR ip (v4) address
      "(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*" + // port and path
      "(\\?[;&a-z\\d%_.~+=-]*)?" + // query string
      "(\\#[-a-z\\d_]*)?$", // fragment locator
    "i"
  );
  if (!pattern.test(url)) {
    return "Invalid URL";
  }

  return null;
};
