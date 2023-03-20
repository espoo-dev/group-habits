import { RemoteItem } from '../../../data/usecases/remote-items';
import type { Items } from 'src/domain/usecases';
import { makeAuthorizeHttpClientDecorator } from '../decorators';

export const makeRemoteItem = (): Items =>
  new RemoteItem(
    `${import.meta.env.VITE_API}/items`,
    makeAuthorizeHttpClientDecorator()
  );
