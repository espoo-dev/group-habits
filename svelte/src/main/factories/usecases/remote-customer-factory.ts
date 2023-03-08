import type { Customers } from 'src/domain/usecases';
import { makeAuthorizeHttpClientDecorator } from '../decorators';
import { RemoteCustomer } from '../../../data/usecases/remote-customers';

export const makeRemoteCustomer = (): Customers =>
  new RemoteCustomer(
    `${import.meta.env.VITE_API}/customers`,
    makeAuthorizeHttpClientDecorator()
  );
