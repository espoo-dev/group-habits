import type { CategoryModel } from '../models/category-model';

export interface Categories {
  list: (params?: Categories.Params) => Promise<Categories.Model[]>;
}

export namespace Categories {
  export type Params = {
    name?: string;
  };

  export type Model = CategoryModel;
}
