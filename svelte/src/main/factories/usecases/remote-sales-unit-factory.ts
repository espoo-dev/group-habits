import { RemoteSalesUnits } from '../../../data/usecases/remote-sales-units';
import type { SalesUnits } from 'src/domain/usecases';
import { makeAuthorizeHttpClientDecorator } from '../decorators';

export const makeRemoteSalesUnits = (): SalesUnits =>
  new RemoteSalesUnits(
    `${import.meta.env.VITE_API}/sales_units`,
    makeAuthorizeHttpClientDecorator()
  );
