import type { Customers } from 'src/domain/usecases';
import { makeAuthorizeHttpClientDecorator } from '../decorators';
import { RemoteCustomer } from '../../../data/usecases/remote-customers';

export const makeRemoteCustomer = (): Customers =>
  new RemoteCustomer(
    'https://group-habits.herokuapp.com/api/v1/customers',
    makeAuthorizeHttpClientDecorator()
  );
