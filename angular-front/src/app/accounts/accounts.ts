import {Component, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AsyncPipe} from '@angular/common';
import {catchError, Observable} from 'rxjs';
import {Account, AccountListState, RequestStatus} from '../model/account.model';
import {map} from 'rxjs/operators';
import {of} from 'rxjs';

@Component({
  imports: [
    AsyncPipe
  ],
  selector: 'app-accounts',
  styleUrl: './accounts.css',
  templateUrl: './accounts.html',
})
export class Accounts {
  private http = inject(HttpClient);
  // Lorsqu'on fait le nom de variable suivi par un "$" c-a-d que c'est un objet de type observable
  accounts$ : Observable<AccountListState> = this.http.get<Account[]>
  ("http://localhost:9999/EBANK-SERVICE/accounts").pipe(
    map(resp => {
      return {accounts: resp, status: RequestStatus.SUCCESS};
    }),
    catchError((err, d) => {
      return of({
        status: RequestStatus.ERROR,
        errorMessage: err.statusText,
      })
    })
  );
  protected readonly RequestStatus = RequestStatus;
}
