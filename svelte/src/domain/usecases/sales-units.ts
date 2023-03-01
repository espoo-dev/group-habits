import type { SaleUnit } from '../models/sale-unit';

export interface SalesUnits {
  list: (params?: SalesUnits.Params) => Promise<SalesUnits.Model[]>;
}

export namespace SalesUnits {
  export type Params = {
    name?: string;
  };

  export type Model = SaleUnit;
}
