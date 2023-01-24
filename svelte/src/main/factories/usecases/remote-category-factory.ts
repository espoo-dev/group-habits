import { RemoteCategory } from '../../../data/usecases/remote-categories';
import type { Categories } from 'src/domain/usecases';
import { makeAxiosHttpClient } from '../http';
import type { HttpClient, HttpResponse } from 'src/data/protocols/http';

export const makeRemoteCategory = (): Categories =>
  new RemoteCategory(
    'https://group-habits.herokuapp.com/api/v1/categories',
    new makeHttpDecorator(makeAxiosHttpClient())
  );

export class makeHttpDecorator implements HttpClient {
  constructor(private readonly httpClient: HttpClient) {}

  async request(data: any): Promise<HttpResponse> {
    data.headers = {
      ...data.headers,
      Authorization:
        'Bearer eyJhY2Nlc3MtdG9rZW4iOiJJckR0S1hJNUN1YlhnVU1Ldnh0ZG9RIiwidG9rZW4tdHlwZSI6IkJlYXJlciIsImNsaWVudCI6IllDZzA2NllsRkVsejlvcnhHMzhPTFEiLCJleHBpcnkiOiIxNzM3NzU4MTc2IiwidWlkIjoidXNlckBlbWFpbC5jb20ifQ==',
    };
    return await this.httpClient.request(data);
  }
}
