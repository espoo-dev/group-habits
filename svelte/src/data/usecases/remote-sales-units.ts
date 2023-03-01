import type { SalesUnits } from 'src/domain/usecases';
import { HttpResponseHandler } from '../../infra/http/';
import type { HttpClient } from '../protocols/http';

export class RemoteSalesUnits implements SalesUnits {
  constructor(
    private readonly url: string,
    private readonly httpClient: HttpClient<RemoteSalesUnits.Model[]>
  ) {}

  async list(params: SalesUnits.Params): Promise<SalesUnits.Model[]> {
    const httpResponse = await this.httpClient.request({
      url: this.url,
      method: 'get',
      body: params,
    });
    return HttpResponseHandler.handleResponse(httpResponse);
  }
}

export namespace RemoteSalesUnits {
  export type Model = SalesUnits.Model;
}
