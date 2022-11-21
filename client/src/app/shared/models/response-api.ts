export interface ResponseAPI {
    status: 'SUCCESS' | 'WARNING' | 'ERROR';
    message: string;
    responseObject: any;
}