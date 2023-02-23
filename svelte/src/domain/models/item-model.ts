export type ItemModel = {
  id: number;
  name: string;
  extra_info: string;
  sale_price: number;
  purchase_price: number;
  item_type: string;
  category: {
    id: number;
    name: string;
  };
  sales_unit: {
    id: number;
    name: string;
  };
};
