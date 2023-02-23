import type { ItemModel } from '../models/item-model';

export interface Items {
  list: (params?: Items.Params) => Promise<Items.Model[]>;
}

export namespace Items {
  export type Params = {
    name?: string;
  };

  export type Model = ItemModel;
}
