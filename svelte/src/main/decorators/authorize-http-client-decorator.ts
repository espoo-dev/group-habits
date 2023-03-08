import type { GetStorage } from 'src/data/protocols/cache';
import type {
  HttpClient,
  HttpRequest,
  HttpResponse,
} from 'src/data/protocols/http';
import type { AccountModel } from 'src/domain/models/account-model';

export class AuthorizeHttpClientDecorator implements HttpClient {
  constructor(
    private readonly getStorage: GetStorage,
    private readonly httpClient: HttpClient
  ) {}

  async request(data: HttpRequest): Promise<HttpResponse> {
    const account: AccountModel = this.getStorage.get('account');
    if (account?.user?.authorization) {
      Object.assign(data, {
        headers: Object.assign(data.headers || {}, {
          Authorization: account.user.authorization,
        }),
      });
    }
    const httpResponse = await this.httpClient.request(data);
    return httpResponse;
  }
}
