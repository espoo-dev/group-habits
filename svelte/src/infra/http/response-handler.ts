import { HttpStatusCode, type HttpResponse } from '../../data/protocols/http';
import { AccessDeniedError, UnexpectedError } from '../../domain/errors';

export class HttpResponseHandler {
  static handleResponse(httpResponse: HttpResponse) {
    const defaultResponse = [];

    switch (httpResponse.statusCode) {
      case HttpStatusCode.ok:
        return httpResponse.body || defaultResponse;
      case HttpStatusCode.forbidden:
        throw new AccessDeniedError();
      case HttpStatusCode.noContent:
        return defaultResponse;

      default:
        throw new UnexpectedError();
    }
  }
}
