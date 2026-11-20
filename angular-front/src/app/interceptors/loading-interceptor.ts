import { HttpInterceptorFn } from '@angular/common/http';
import {LoadingService} from '../services/loading';
import {inject} from '@angular/core';
import {finalize} from 'rxjs';

export const loadingInterceptor: HttpInterceptorFn = (req, next) => {
  const loadingService = inject(LoadingService);
  loadingService.setLoading(true);
  console.log('START', req.url);
  return next(req).pipe(
    finalize(() => {
      console.log('FINALIZE', req.url);
      loadingService.setLoading(false);
    })
  );
};
