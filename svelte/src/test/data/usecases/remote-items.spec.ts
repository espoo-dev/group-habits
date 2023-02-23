import { HttpClientSpy } from '../../data/mocks';
import { faker } from '@faker-js/faker';
import { HttpStatusCode } from '../../../../src/data/protocols/http';
import {
  AccessDeniedError,
  UnexpectedError,
} from '../../../../src/domain/errors';
import { RemoteItem } from '../../../../src/data/usecases/remote-items';
import { mockRemoteItemListModel } from '../mocks/mock-remote-items';

type SutTypes = {
  sut: RemoteItem;
  httpClientSpy: HttpClientSpy<RemoteItem.Model[]>;
};

const makeSut = (url = faker.internet.url()): SutTypes => {
  const httpClientSpy = new HttpClientSpy<RemoteItem.Model[]>();
  const sut = new RemoteItem(url, httpClientSpy);
  return {
    sut,
    httpClientSpy,
  };
};

describe('RemoteItem', () => {
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

  test('Should return a list of ItemModel if HttpClient returns 200', async () => {
    const { sut, httpClientSpy } = makeSut();
    const httpResult = mockRemoteItemListModel();
    httpClientSpy.response = {
      statusCode: HttpStatusCode.ok,
      body: httpResult,
    };

    const itemList = await sut.list({});
    expect(itemList).toEqual([
      {
        id: httpResult[0].id,
        name: httpResult[0].name,
        extra_info: httpResult[0].extra_info,
        sale_price: httpResult[0].sale_price,
        purchase_price: httpResult[0].purchase_price,
        item_type: httpResult[0].item_type,
        category: {
          id: httpResult[0].category.id,
          name: httpResult[0].category.name,
        },
        sales_unit: {
          id: httpResult[0].sales_unit.id,
          name: httpResult[0].sales_unit.name,
        },
      },
      {
        id: httpResult[1].id,
        name: httpResult[1].name,
        extra_info: httpResult[1].extra_info,
        sale_price: httpResult[1].sale_price,
        purchase_price: httpResult[1].purchase_price,
        item_type: httpResult[1].item_type,
        category: {
          id: httpResult[1].category.id,
          name: httpResult[1].category.name,
        },
        sales_unit: {
          id: httpResult[1].sales_unit.id,
          name: httpResult[1].sales_unit.name,
        },
      },
    ]);
  });

  test('Should return an empty list if HttpClient returns 204', async () => {
    const { sut, httpClientSpy } = makeSut();
    httpClientSpy.response = {
      statusCode: HttpStatusCode.noContent,
    };

    const itemList = await sut.list({});
    expect(itemList).toEqual([]);
  });
});
