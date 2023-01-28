import { faker } from '@faker-js/faker';
import type { AccountModel } from 'src/domain/models/account-model';

export const mockAccountModel = (): AccountModel => ({
  user: {
    allow_password_change: false,
    authorization: faker.datatype.uuid(),
    email: faker.internet.email(),
    first_name: faker.name.firstName(),
    group_id: 1,
    id: 1,
    uid: faker.datatype.uuid(),
    last_name: faker.name.lastName(),
    provider: faker.name.firstName(),
    username: faker.name.middleName(),
  },
});
