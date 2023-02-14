import { faker } from '@faker-js/faker';
import type { RemoteCustomer } from '../../../../src/data/usecases/remote-customers';

export const mockRemoteCustomerModel = (): RemoteCustomer.Model => ({
  id: faker.datatype.number(),
  customer_type: faker.name.jobArea(),
  document_number: faker.datatype.number(),
  phone: faker.datatype.number(),
  name: faker.name.fullName(),
  state_inscription: 'CE',
});

export const mockRemoteCustomerListModel = (): RemoteCustomer.Model[] => [
  mockRemoteCustomerModel(),
  mockRemoteCustomerModel(),
];
