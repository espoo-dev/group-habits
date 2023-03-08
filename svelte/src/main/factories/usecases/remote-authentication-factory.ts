import { RemoteAuthentication } from '../../../data/usecases/remote-authentication';
import type { Authentication } from 'src/domain/usecases';
import { makeAxiosHttpClient } from '../http';

export const makeRemoteAuthentication = (): Authentication =>
  new RemoteAuthentication(
    `${import.meta.env.VITE_API}/users/sign_in`,
    makeAxiosHttpClient()
  );
