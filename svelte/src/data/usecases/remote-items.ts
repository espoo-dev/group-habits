import type { ItemModel } from 'src/domain/models/item-model';
import type { Items } from 'src/domain/usecases';
import { HttpResponseHandler } from '../../infra/http/';
import type { HttpClient } from '../protocols/http';

export class RemoteItem implements Items {
  constructor(
    private readonly url: string,
    private readonly httpClient: HttpClient<RemoteItem.Model[]>
  ) {}

  async list(params: Items.Params): Promise<Items.Model[]> {
    const httpResponse = await this.httpClient.request({
      url: this.url,
      method: 'get',
      body: params,
    });
    return HttpResponseHandler.handleResponse(httpResponse);
  }

  async create(payload: Items.New): Promise<ItemModel> {
    const httpResponse = await this.httpClient.request({
      url: this.url,
      method: 'post',
      body: payload,
    });
    return HttpResponseHandler.handleResponse(httpResponse);
  }
}

export namespace RemoteItem {
  export type Model = Items.Model;
}
