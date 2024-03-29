// import { HttpStatusCode } from 'src/data/protocols/http';
import { HttpStatusCode } from '../../../data/protocols/http';
import { AccessDeniedError, UnexpectedError } from '../../../domain/errors';
import { HttpResponseHandler } from '../../../infra/http';

describe('HttpResponseHandler', () => {
  describe('handleResponse', () => {
    it('should return the response body when the status code is OK', () => {
      const body = [{ name: 'category 1', id: 1 }];
      const httpResponse = {
        statusCode: HttpStatusCode.ok,
        body,
      };
      expect(HttpResponseHandler.handleResponse(httpResponse)).toEqual(body);
    });

    it('should return the response body when the status code is created', () => {
      const body = [{ name: 'new category', id: 4 }];
      const httpResponse = {
        statusCode: HttpStatusCode.created,
        body,
      };
      expect(HttpResponseHandler.handleResponse(httpResponse)).toEqual(body);
    });

    it('should return an empty array when the status code is No Content', () => {
      const httpResponse = { statusCode: HttpStatusCode.noContent };
      expect(HttpResponseHandler.handleResponse(httpResponse)).toEqual([]);
    });

    it('should throw an AccessDeniedError when the status code is Forbidden', () => {
      const httpResponse = { statusCode: HttpStatusCode.forbidden };
      expect(() => {
        HttpResponseHandler.handleResponse(httpResponse);
      }).toThrow(AccessDeniedError);
    });

    it('should throw an UnexpectedError when the status code is not handled', () => {
      const httpResponse = { statusCode: HttpStatusCode.notFound };
      expect(() => {
        HttpResponseHandler.handleResponse(httpResponse);
      }).toThrow(UnexpectedError);
    });
  });
});
