import { RemoteCategory } from '../../../data/usecases/remote-categories';
import type { Categories } from 'src/domain/usecases';
import { makeAuthorizeHttpClientDecorator } from '../decorators';

export const makeRemoteCategory = (): Categories =>
  new RemoteCategory(
    'https://group-habits.herokuapp.com/api/v1/categories',
    makeAuthorizeHttpClientDecorator()
  );

// export class makeHttpDecorator implements HttpClient {
//   constructor(private readonly httpClient: HttpClient) {}

//   async request(data: any): Promise<HttpResponse> {
//     data.headers = {
//       ...data.headers,
//       Authorization:
//         'Bearer eyJhY2Nlc3MtdG9rZW4iOiJ0Skx3YnNHaXIyRmZQSFdjN29OSVlnIiwidG9rZW4tdHlwZSI6IkJlYXJlciIsImNsaWVudCI6Il9DdTVRWDhmczhYbFlweTZCWW03bXciLCJleHBpcnkiOiIxNzM3OTQxNDg2IiwidWlkIjoidXNlckBlbWFpbC5jb20ifQ==',
//     };
//     return await this.httpClient.request(data);
//   }
// }
