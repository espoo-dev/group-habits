import { render, screen } from '@testing-library/react-native';
import { Text } from 'react-native';
import { UIContainer } from './index';

function setup() {
  return render(<UIContainer>
    <Text>
      Content Here
    </Text>
  </UIContainer>)
}

describe('src > components > UI > UIContainer', () => {
  it(`match snapshot`, () => {
    const tree = setup().toJSON();

    expect(tree).toMatchSnapshot();
  });

  it(`renders correctly`, () => {
    setup()

    const text = screen.getByText(/content here/i)

    expect(text).toBeOnTheScreen()
  })
});
