import type { CategoryModel } from '../models/category-model';

export interface Categories {
  list: (params?: Categories.Params) => Promise<Categories.Model[]>;
  create: (payload: Categories.New) => Promise<Categories.Model>;
  delete: (category_id: number) => Promise<void>;
  edit: (
    category_id: number,
    category: Categories.Edit
  ) => Promise<Categories.Model>;
}

export namespace Categories {
  export type Params = {
    name?: string;
  };

  export type New = {
    name: string;
  };

  export type Edit = {
    name: string;
  };

  export type Model = CategoryModel;
}
