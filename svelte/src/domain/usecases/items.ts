import type { ItemModel } from '../models/item-model';

export interface Items {
  list: (params?: Items.Params) => Promise<Items.Model[]>;
  create: (payload: Items.New) => Promise<Items.Model>;
}

export namespace Items {
  export type Params = {
    name?: string;
  };

  export type New = {
    name: string;
    extra_info: string;
    sale_price: number;
    purchase_price: number;
    item_type: string;
    category_id: number;
    sales_unit_id: number;
  };

  export type Model = ItemModel;
}
