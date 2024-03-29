import { RemoteSalesUnits } from '../../../../src/data/usecases/remote-sales-units'
import { HttpClientSpy } from '../../data/mocks';


import { faker } from '@faker-js/faker';
import { HttpStatusCode } from '../../../../src/data/protocols/http';
import { AccessDeniedError, UnexpectedError } from '../../../../src/domain/errors';
import { mockRemoteSalesUnitListModel } from '../mocks/mock-remote-sales-units';

type SutTypes = {
  sut: RemoteSalesUnits
  httpClientSpy: HttpClientSpy<RemoteSalesUnits.Model[]>
}

const makeSut = (url = faker.internet.url()): SutTypes => {
  const httpClientSpy = new HttpClientSpy<RemoteSalesUnits.Model[]>()
  const sut = new RemoteSalesUnits(url, httpClientSpy)
  return {
    sut,
    httpClientSpy
  }
}

describe('RemoteSalesUnits', () => {
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
    const httpResult = mockRemoteSalesUnitListModel()
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
})
