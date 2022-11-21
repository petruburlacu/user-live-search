import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { catchError, debounceTime, distinctUntilChanged, filter, map, merge, Observable, of, switchMap } from 'rxjs';
import { UserSearchResponse } from './user-search-response';
import { ResponseAPI } from 'src/app/shared/models/response-api';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json;'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserSearchService {

  constructor(private http: HttpClient) {}

  fetch(input: Observable<string>): Observable<UserSearchResponse> {
    return input.pipe(
      filter((searchInput: string) => !searchInput || searchInput.length === 0 || searchInput === '' || searchInput.length > 2),
      debounceTime(400),
      distinctUntilChanged(),
      switchMap((term: string) => merge(
        of({loading: true, data: [], message: 'Loading...', noResults: false, key: Date.now()}),
        this.fetchUsers(term)
      ))
    );
  }

  fetchUsers(login: string): Observable<UserSearchResponse> {
    if (!login || login?.length === 0 || login?.trim() === '') {
      return of({
        loading: false,
        data: [],
        message: 'Empty input',
        noResults: false,
        key: Date.now()
      });
    }
    return this.http.get<ResponseAPI>(`/api/users/${login}`, httpOptions).pipe(
      map((response) => {
        if (response?.status === 'SUCCESS') {
          return {loading: false, data: response.responseObject, message: response.message, noResults: response?.responseObject?.length === 0, key: Date.now()};
        }
        return {loading: false, data: [], message: 'No response data found', noResults: true, key: Date.now()};
      }),
      catchError((error, cought) => {
        console.error(`Error: ${error}. Cought ${cought}`);
        return of({
          loading: false,
          data: [],
          message: 'Http request error has occured',
          noResults: true,
          key: Date.now()
        })
      })
    );
  }
}
