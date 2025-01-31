import axiosInstance from "../config/axiosConfig";
import { LinkRequest, LinkResponse } from "../dto/linkDto";
import { handleApiError } from "../exceptions/apiErrorHandler";

export const createLinkService = async (data: LinkRequest): Promise<LinkResponse> => {
    try {
        const response = await axiosInstance.post<LinkResponse>("/shorten", data);
        return response.data;
    } catch (error) {
        return handleApiError(error);
    }
};
