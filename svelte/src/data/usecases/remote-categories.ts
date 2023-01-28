import { InvalidCredentialsError, UnexpectedError } from '../../domain/errors';
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
}

export namespace RemoteCategory {
  export type Model = Categories.Model;
}
