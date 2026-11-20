import {Accounts} from '../accounts/accounts';

export class Account {
  id?: string;
  createdAt?: Date;
  balance?: number;
  type?: string;
  customerId?: number;
}

export enum RequestStatus {
  SUCCESS = 0, ERROR = 1
}

export interface AccountListState {
  accounts?: Accounts[];
  status?: RequestStatus;
  errorMessage?: string;
}
