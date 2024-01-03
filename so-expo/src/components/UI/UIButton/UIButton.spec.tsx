import { render, screen } from '@testing-library/react-native';
import { IUIButtonProps, UIButton } from './index';

function setup(props: IUIButtonProps) {
  return render(<UIButton {...props} />)
}

describe('src > components > UI > UIButton', () => {
  it(`match snapshot`, () => {
    const tree = setup({ children: 'Button' }).toJSON();

    expect(tree).toMatchSnapshot();
  });

  it(`renders correctly`, () => {
    setup({ children: 'Button' })

    const buttonText = screen.getByText(/button/i)
    const activityIndicator = screen.queryByTestId('spec-ui-button-activity-indicator')

    expect(buttonText).toBeOnTheScreen()
    expect(activityIndicator).toBeNull()
  })

  describe('when is loading', () => {
    it(`should render ActivityIndicator`, () => {
      setup({ children: 'Button', loading: true });

      const buttonText = screen.queryByText(/button/i)
      const activityIndicator = screen.getByTestId('spec-ui-button-activity-indicator')

      expect(buttonText).toBeNull()
      expect(activityIndicator).toBeOnTheScreen()
    });
  })
});
