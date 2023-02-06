
import { faker } from '@faker-js/faker';
import type { RemoteCategory } from '../../../../src/data/usecases/remote-categories'

export const mockRemoteCategoryModel = (): RemoteCategory.Model => ({
  id: faker.datatype.number(),
  name: faker.name.jobArea()
})

<<<<<<< HEAD
export const mockRemoteCategoryListModel = (): RemoteCategory.Model[] => ([
=======
export const mockRemoteSurveyListModel = (): RemoteCategory.Model[] => ([
>>>>>>> e31287bb2b3389269151a443148e74839a4aa95c
  mockRemoteCategoryModel(),
  mockRemoteCategoryModel(),
  mockRemoteCategoryModel()
])
