import { Component, EventEmitter, Input, OnDestroy, Output } from '@angular/core';
import { distinctUntilChanged, map, Observable, Subject } from 'rxjs';
import { User } from './user';
import { UserSearchResponse } from './user-search-response';
import { UserSearchService } from './user-search.service';

@Component({
  selector: 'app-user-search',
  templateUrl: './user-search.component.html',
  styleUrls: ['./user-search.component.scss']
})
export class UserSearchComponent implements OnDestroy {

  users$: Observable<UserSearchResponse>; // Angular automaticall handles subscribtions through HTML async pipe
  searchTerm$ = new Subject<string>();
  @Input() loading: boolean = false;
  @Output() selectUser: EventEmitter<any> = new EventEmitter();

  constructor(private searchService: UserSearchService) {
    this.users$ = this.searchService.fetch(this.searchTerm$).pipe(
      distinctUntilChanged(),
      map((response: UserSearchResponse) => {
        // Custom logic can be put here
        // Transform the data for HTML
        console.log('component',response)
        return response;
      })
    );
  }

  ngOnDestroy(): void {
    this.searchTerm$.unsubscribe();
  }

  onInput($event: any): void {
    let input = $event.target?.value
    this.searchTerm$.next(input);
  }

  onSelect($event: User) {
    if (!$event) {
      return;
    }
    this.selectUser.emit($event);
  }

  trackByFn(_index: number, user: User): string {
    return user.login;
  }

}
