import { faker } from '@faker-js/faker';
import {
  HttpStatusCode,
  type HttpClient,
  type HttpRequest,
  type HttpResponse,
} from '../../../data/protocols/http';

export const mockHttpRequest = (): HttpRequest => ({
  url: faker.internet.url(),
  method: 'get',
  body: {},
  headers: {},
});

export class HttpClientSpy<R = any> implements HttpClient<R> {
  url?: string;
  method?: string;
  body?: any;
  headers?: any;
  response: HttpResponse<R> = {
    statusCode: HttpStatusCode.ok,
  };

  async request(data: HttpRequest): Promise<HttpResponse<R>> {
    this.url = data.url;
    this.method = data.method;
    this.body = data.body;
    this.headers = data.headers;
    return this.response;
  }
}
