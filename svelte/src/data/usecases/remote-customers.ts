import type { CustomerModel } from 'src/domain/models/customer-model';
import type { Customers } from 'src/domain/usecases';
import { HttpResponseHandler } from '../../infra/http/';
import type { HttpClient } from '../protocols/http';

export class RemoteCustomer implements Customers {
  constructor(
    private readonly url: string,
    private readonly httpClient: HttpClient<RemoteCustomer.Model[]>
  ) {}

  async list(params: Customers.Params): Promise<Customers.Model[]> {
    const httpResponse = await this.httpClient.request({
      url: this.url,
      method: 'get',
      body: params,
    });
    return HttpResponseHandler.handleResponse(httpResponse);
  }

  async create(payload: Customers.New): Promise<CustomerModel> {
    const httpResponse = await this.httpClient.request({
      url: this.url,
      method: 'post',
      body: payload,
    });
    return HttpResponseHandler.handleResponse(httpResponse);
  }

  async delete(customer_id: number): Promise<void> {
    const httpResponse = await this.httpClient.request({
      url: `${this.url}/${customer_id}`,
      method: 'delete',
    });
    return HttpResponseHandler.handleResponse(httpResponse);
  }

  async edit(
    customer_id: number,
    customer: Customers.Edit
  ): Promise<Customers.Model> {
    const httpResponse = await this.httpClient.request({
      url: `${this.url}/${customer_id}`,
      method: 'put',
      body: customer,
    });
    return HttpResponseHandler.handleResponse(httpResponse);
  }
}

export namespace RemoteCustomer {
  export type Model = Customers.Model;
}
