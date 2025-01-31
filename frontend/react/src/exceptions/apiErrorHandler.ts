import { LinkErrorResponse, isErrorResponse } from "../dto/linkDto";
import axios from "axios";

/**
 * Handles API errors and returns a standardized error response.
 * This function checks the type of error and maps it to a `LinkErrorResponse`.
 * 
 * Possible error types:
 * - Server responded with an error (HTTP status code is not 2xx)
 * - No response was received from the server
 * - An unexpected error occurred (e.g., a client-side issue)
 * 
 * @param error - The error object caught during an API request.
 * @returns A standardized `LinkErrorResponse` object.
 */
export const handleApiError = (error: unknown): LinkErrorResponse => {
    // Check if error is Axios Error
    if (axios.isAxiosError(error)) {
        //  Server responded with an error (HTTP status code is not 2xx)
        if (error.response) {
            const { status, data } = error.response;

            // Check if the response follows the expected `ErrorResponse` format
            if (isErrorResponse(data)) {
                return data; // Return the error response from the server
            }

            // If the response does not match the expected format, return a generic error
            return {
                status,
                error: "Unknown error",
                message: "Unexpected response from server",
            };

        } else if (error.request) {
            // The request was made but no response was received (e.g., network issues, server downtime)
            return {
                status: 503,
                error: "No response",
                message: "Server did not respond",
            };
        }
    }

    // If the error type is completely unknown, return a generic error
    return {
        status: 500,
        error: "Unknown error",
        message: "An unexpected error occurred",
    };
};