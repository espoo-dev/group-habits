import type { HttpClient } from '../../../data/protocols/http';
import { AuthorizeHttpClientDecorator } from '../../../main/decorators';
import { makeLocalStorageAdapter } from '../cache';
import { makeAxiosHttpClient } from '../http';

export const makeAuthorizeHttpClientDecorator = (): HttpClient =>
  new AuthorizeHttpClientDecorator(
    makeLocalStorageAdapter(),
    makeAxiosHttpClient()
  );
