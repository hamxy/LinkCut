import axiosInstance from "../config/axiosConfig";

// Response Format
type ApiResponse = {
    message: string;
};

export const getTestData = async (): Promise<ApiResponse> => {
    try {
        const response = await axiosInstance.get<ApiResponse>("/");
        return response.data;
    } catch (error) {
        console.error("Error fetching data:", error);
        throw error;
    }
};