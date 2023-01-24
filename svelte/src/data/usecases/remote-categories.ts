import { InvalidCredentialsError, UnexpectedError } from '../../domain/errors';
import type { Categories } from 'src/domain/usecases';
import { HttpStatusCode, type HttpClient } from '../protocols/http';

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
    switch (httpResponse.statusCode) {
      case HttpStatusCode.ok:
        return httpResponse.body;
      case HttpStatusCode.unauthorized:
        throw new InvalidCredentialsError();
      default:
        throw new UnexpectedError();
    }
  }
}

export namespace RemoteCategory {
  export type Model = Categories.Model;
}
