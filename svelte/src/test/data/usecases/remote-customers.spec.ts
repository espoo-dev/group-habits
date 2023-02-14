import { HttpClientSpy } from '../../data/mocks';
import { faker } from '@faker-js/faker';
import { HttpStatusCode } from '../../../../src/data/protocols/http';
import {
  AccessDeniedError,
  UnexpectedError,
} from '../../../../src/domain/errors';
import { RemoteCustomer } from '../../../../src/data/usecases/remote-customers';
import { mockRemoteCustomerListModel } from '../mocks/mock-remote-customers';

type SutTypes = {
  sut: RemoteCustomer;
  httpClientSpy: HttpClientSpy<RemoteCustomer.Model[]>;
};

const makeSut = (url = faker.internet.url()): SutTypes => {
  const httpClientSpy = new HttpClientSpy<RemoteCustomer.Model[]>();
  const sut = new RemoteCustomer(url, httpClientSpy);
  return {
    sut,
    httpClientSpy,
  };
};

describe('RemoteCustomer', () => {
  test('Should call HttpClient with correct URL and Method', async () => {
    const url = faker.internet.url();
    const { sut, httpClientSpy } = makeSut(url);

    await sut.list({});

    expect(httpClientSpy.url).toBe(url);
    expect(httpClientSpy.method).toBe('get');
  });

  test('Should throw AccessDeniedError if HttpClient returns 403', async () => {
    const { sut, httpClientSpy } = makeSut();
    httpClientSpy.response = {
      statusCode: HttpStatusCode.forbidden,
    };

    const promise = sut.list({});

    await expect(promise).rejects.toThrow(new AccessDeniedError());
  });

  test('Should throw UnexpectedError if HttpClient returns 404', async () => {
    const { sut, httpClientSpy } = makeSut();
    httpClientSpy.response = {
      statusCode: HttpStatusCode.notFound,
    };

    const promise = sut.list({});

    await expect(promise).rejects.toThrow(new UnexpectedError());
  });

  test('Should throw UnexpectedError if HttpClient returns 500', async () => {
    const { sut, httpClientSpy } = makeSut();
    httpClientSpy.response = {
      statusCode: HttpStatusCode.serverError,
    };

    const promise = sut.list({});

    await expect(promise).rejects.toThrow(new UnexpectedError());
  });

  test('Should return a list of CustomerModel if HttpClient returns 200', async () => {
    const { sut, httpClientSpy } = makeSut();
    const httpResult = mockRemoteCustomerListModel();
    httpClientSpy.response = {
      statusCode: HttpStatusCode.ok,
      body: httpResult,
    };

    const customerList = await sut.list({});
    expect(customerList).toEqual([
      {
        id: httpResult[0].id,
        name: httpResult[0].name,
        customer_type: httpResult[0].customer_type,
        document_number: httpResult[0].document_number,
        phone: httpResult[0].phone,
        state_inscription: httpResult[0].state_inscription,
      },
      {
        id: httpResult[1].id,
        name: httpResult[1].name,
        customer_type: httpResult[1].customer_type,
        document_number: httpResult[1].document_number,
        phone: httpResult[1].phone,
        state_inscription: httpResult[1].state_inscription,
      },
    ]);
  });

  test('Should return an empty list if HttpClient returns 204', async () => {
    const { sut, httpClientSpy } = makeSut();
    httpClientSpy.response = {
      statusCode: HttpStatusCode.noContent,
    };

    const customerList = await sut.list({});
    expect(customerList).toEqual([]);
  });
});
