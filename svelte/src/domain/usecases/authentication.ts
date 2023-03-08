import type { AccountModel } from '../models/account-model';

export interface Authentication {
  auth: (params: Authentication.Params) => Promise<Authentication.Model>;
}

export namespace Authentication {
  export type Params = {
    user: {
      email: string;
      password: string;
    };
  };

  export type Model = AccountModel;
}
