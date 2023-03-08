import type { AccountModel } from '../../domain/models/account-model';
import { makeLocalStorageAdapter } from '../factories/cache';

export const setCurrentAccountAdapter = (account: AccountModel): void => {
  makeLocalStorageAdapter().set('account', account);
};

export const getCurrentAccountAdapter = (): AccountModel => {
  return makeLocalStorageAdapter().get('account');
};
