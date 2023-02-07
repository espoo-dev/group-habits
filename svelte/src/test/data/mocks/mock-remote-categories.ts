
import { faker } from '@faker-js/faker';
import type { RemoteCategory } from '../../../../src/data/usecases/remote-categories'

export const mockRemoteCategoryModel = (): RemoteCategory.Model => ({
  id: faker.datatype.number(),
  name: faker.name.jobArea()
})

export const mockRemoteCategoryListModel = (): RemoteCategory.Model[] => ([
  mockRemoteCategoryModel(),
  mockRemoteCategoryModel(),
  mockRemoteCategoryModel()
])
