import { render } from '@testing-library/react-native';
import * as React from 'react';

import { MonoText } from '../StyledText';

function setup() {
  return render(<MonoText>Snapshot</MonoText>)
}

it(`renders correctly`, () => {
  const tree = setup().toJSON()

  expect(tree).toMatchSnapshot();
});
