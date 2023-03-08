import { faker } from '@faker-js/faker';
import type { RemoteItem } from '../../../../src/data/usecases/remote-items';

export const mockRemoteItemModel = (): RemoteItem.Model => ({
  id: faker.datatype.number(),
  name: faker.commerce.product(),
  extra_info: faker.name.jobArea(),
  sale_price: Number(faker.commerce.price()),
  purchase_price: Number(faker.commerce.price()),
  item_type: faker.commerce.productAdjective(),
  category: {
    id: faker.datatype.number(),
    name: faker.commerce.product(),
  },
  sales_unit: {
    id: faker.datatype.number(),
    name: faker.commerce.product(),
  },
});

export const mockRemoteItemListModel = (): RemoteItem.Model[] => [
  mockRemoteItemModel(),
  mockRemoteItemModel(),
];
