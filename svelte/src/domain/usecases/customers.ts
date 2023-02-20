import type { CustomerModel } from '../models/customer-model';

export interface Customers {
  list: (params?: Customers.Params) => Promise<Customers.Model[]>;
  create: (payload: Customers.New) => Promise<Customers.Model>;
  delete: (customer_id: number) => Promise<void>;
  edit: (
    customer_id: number,
    customer: Customers.Edit
  ) => Promise<Customers.Model>;
}

export namespace Customers {
  export type Params = {
    name?: string;
    customer_type?: string;
  };

  export type New = {
    name: string;
    document_number: number;
    phone: number;
    state_inscription: string;
    customer_type: string;
  };

  export type Edit = {
    name: string;
    document_number: number;
    phone: number;
    state_inscription: string;
    customer_type: string;
  };

  export type Model = CustomerModel;
}
