import { faker } from '@faker-js/faker';
import type { GetStorage } from 'src/data/protocols/cache';

export class GetStorageSpy implements GetStorage {
  key: string;
  value: any = {
    authorization: faker.random.word(),
  };

  get(key: string): any {
    this.key = key;
    return this.value;
  }
}
