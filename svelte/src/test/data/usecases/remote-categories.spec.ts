import { RemoteCategory } from '../../../../src/data/usecases/remote-categories'
import { HttpClientSpy, mockRemoteCategoryListModel } from '../../data/mocks';


import { faker } from '@faker-js/faker';
import { HttpStatusCode } from '../../../../src/data/protocols/http';
import { AccessDeniedError, UnexpectedError } from '../../../../src/domain/errors';

type SutTypes = {
  sut: RemoteCategory
  httpClientSpy: HttpClientSpy<RemoteCategory.Model[]>
}

const makeSut = (url = faker.internet.url()): SutTypes => {
  const httpClientSpy = new HttpClientSpy<RemoteCategory.Model[]>()
  const sut = new RemoteCategory(url, httpClientSpy)
  return {
    sut,
    httpClientSpy
  }
}

describe('RemoteCategory', () => {
  test('Should call HttpClient with correct URL and Method', async () => {
    const url = faker.internet.url()
    const { sut, httpClientSpy } = makeSut(url)

    await sut.list({})

    expect(httpClientSpy.url).toBe(url)
    expect(httpClientSpy.method).toBe('get')
  })

  test('Should throw AccessDeniedError if HttpClient returns 403', async () => {
    const { sut, httpClientSpy } = makeSut()
    httpClientSpy.response = {
      statusCode: HttpStatusCode.forbidden
    }

    const promise = sut.list({})

    await expect(promise).rejects.toThrow(new AccessDeniedError())
  })

  test('Should throw UnexpectedError if HttpClient returns 404', async () => {
    const { sut, httpClientSpy } = makeSut()
    httpClientSpy.response = {
      statusCode: HttpStatusCode.notFound
    }

    const promise = sut.list({})

    await expect(promise).rejects.toThrow(new UnexpectedError())
  })

  test('Should throw UnexpectedError if HttpClient returns 500', async () => {
    const { sut, httpClientSpy } = makeSut()
    httpClientSpy.response = {
      statusCode: HttpStatusCode.serverError
    }

    const promise = sut.list({})

    await expect(promise).rejects.toThrow(new UnexpectedError())
  })

  test('Should return a list of SurveyModels if HttpClient returns 200', async () => {
    const { sut, httpClientSpy } = makeSut()
    const httpResult = mockRemoteCategoryListModel()
    httpClientSpy.response = {
      statusCode: HttpStatusCode.ok,
      body: httpResult
    }

    const surveyList = await sut.list({})

    expect(surveyList).toEqual([{
      id: httpResult[0].id,
      name: httpResult[0].name,
    }, {
      id: httpResult[1].id,
      name: httpResult[1].name,
    }, {
      id: httpResult[2].id,
      name: httpResult[2].name,
    }])
  })

  test('Should return an empty list if HttpClient returns 204', async () => {
    const { sut, httpClientSpy } = makeSut()
    httpClientSpy.response = {
      statusCode: HttpStatusCode.noContent
    }

    const surveyList = await sut.list({})

    expect(surveyList).toEqual([])
  })

  test('Should return an empty list when delete a register', async () => {
    const { sut, httpClientSpy } = makeSut()
    httpClientSpy.response = {
      statusCode: HttpStatusCode.noContent
    }

    const surveyList = await sut.delete(mockRemoteCategoryListModel()[0].id)

    expect(surveyList).toEqual([])
  })
})
