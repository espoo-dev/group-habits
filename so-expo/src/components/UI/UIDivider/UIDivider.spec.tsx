import { render, screen } from '@testing-library/react-native';
import { UIDivider } from './index';

function setup() {
  return render(<UIDivider />)
}

describe('src > components > UI > UIDivider', () => {
  it(`match snapshot`, () => {
    const tree = setup().toJSON();

    expect(tree).toMatchSnapshot();
  });

  it(`renders correctly`, () => {
    setup()

    const text = screen.getByText(/ou/i)

    expect(text).toBeOnTheScreen()
  })
});
