import { render, screen, waitFor } from '@testing-library/react-native';
import App from './App';

function setup() {
  return render(<App />)
}

describe('<App />', () => {
  it('should render header correctly', () => {
    setup()

    waitFor(() => {
      expect(
        screen.getByRole('header', { name: 'Tab One' })
      ).toBeOnTheScreen();
    })
  });
});
