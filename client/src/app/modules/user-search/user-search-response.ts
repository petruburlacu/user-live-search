import { User } from "./user";

export interface UserSearchResponse {
    loading: boolean,
    data: User[],
    message: string,
    noResults: boolean,
    key: number
}