import { RemoteCategory } from '../../../data/usecases/remote-categories';
import type { Categories } from 'src/domain/usecases';
import { makeAuthorizeHttpClientDecorator } from '../decorators';

export const makeRemoteCategory = (): Categories =>
  new RemoteCategory(
    'https://group-habits.herokuapp.com/api/v1/categories',
    makeAuthorizeHttpClientDecorator()
  );
