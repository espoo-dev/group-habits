
import { faker } from '@faker-js/faker';
import type { RemoteSalesUnits } from '../../../../src/data/usecases/remote-sales-units';

export const mockRemoteSalesUnitModel = (): RemoteSalesUnits.Model => ({
  id: faker.datatype.number(),
  name: faker.name.jobArea()
})

export const mockRemoteSalesUnitListModel = (): RemoteSalesUnits.Model[] => ([
  mockRemoteSalesUnitModel(),
  mockRemoteSalesUnitModel(),
  mockRemoteSalesUnitModel()
])
