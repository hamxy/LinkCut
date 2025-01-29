import axiosInstance from "../config/axiosConfig";

// Request
interface ApiRequest {
    originalUrl: string;
}

// Response
interface ApiResponse {
    shortUrl: string;
};

export const createLink = async (data: ApiRequest) => {
    try {
        const response = await axiosInstance.post<ApiResponse>("/shorten", data, {
            headers: {
              "Content-Type": "application/json",
            },
          });
        return response;
    } catch (error) {
        console.error("Error fetching data:", error);
        throw error;
    }
};