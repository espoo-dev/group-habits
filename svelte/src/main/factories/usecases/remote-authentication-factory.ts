import { RemoteAuthentication } from '../../../data/usecases/remote-authentication';
import type { Authentication } from 'src/domain/usecases';
import { makeAxiosHttpClient } from '../http';

export const makeRemoteAuthentication = (): Authentication =>
  new RemoteAuthentication(
    'https://group-habits.herokuapp.com/api/v1/users/sign_in',
    makeAxiosHttpClient()
  );
