import {Service, signal} from '@angular/core';

@Service()
export class LoadingService {
  loading = signal<boolean>(false);
  public isLoading = this.loading.asReadonly();   // "asReadonly" c'est pour retourner juste la valeur de loading

  public setLoading(value: boolean) {
    this.loading.set(value);
  }
}
