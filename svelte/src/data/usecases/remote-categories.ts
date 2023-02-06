import type { CategoryModel } from 'src/domain/models/category-model';
import type { Categories } from 'src/domain/usecases';
import { HttpResponseHandler } from '../../infra/http/';
import type { HttpClient } from '../protocols/http';

export class RemoteCategory implements Categories {
  constructor(
    private readonly url: string,
    private readonly httpClient: HttpClient<RemoteCategory.Model[]>
  ) {}

  async list(params: Categories.Params): Promise<Categories.Model[]> {
    const httpResponse = await this.httpClient.request({
      url: this.url,
      method: 'get',
      body: params,
    });
    return HttpResponseHandler.handleResponse(httpResponse);
  }

  async create(payload: Categories.New): Promise<CategoryModel> {
    const httpResponse = await this.httpClient.request({
      url: this.url,
      method: 'post',
      body: payload
    })
    return HttpResponseHandler.handleResponse(httpResponse);
  }

  async delete(category_id: number): Promise<void> {
    const httpResponse = await this.httpClient.request({
      url: `${this.url}/${category_id}`,
      method: 'delete'
    })
    return HttpResponseHandler.handleResponse(httpResponse);
  }
}

export namespace RemoteCategory {
  export type Model = Categories.Model;
}
