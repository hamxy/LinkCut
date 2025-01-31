/**
 * DTO
 */
export type LinkRequest = {
    originalUrl: string;
}

export type LinkSuccessResponse = {
    shortUrl: string;
}

export type LinkErrorResponse ={
    status: number;
    error: string;
    message: string;
}

export type LinkResponse = LinkSuccessResponse | LinkErrorResponse;


/**
 * Type guards
 */

export const isSuccessResponse = (response: LinkResponse): response is LinkSuccessResponse => {
    return "shortUrl" in response; 
};

export const isErrorResponse = (response: LinkResponse): response is LinkErrorResponse => {
    return "status" in response && "error" in response && "message" in response;
};

