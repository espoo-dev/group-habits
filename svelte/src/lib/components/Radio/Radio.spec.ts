import { render } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import Radio from './Radio.svelte';

const props = {
  label: 'Nome',
  value: 'nome',
};

let radioElement: HTMLElement;
const user = userEvent.setup();

const sut = () => {
  const { getByTestId } = render(Radio, {
    props,
  });
  radioElement = getByTestId(`${props.label}-radio-id`);
};

describe('Radio component', () => {
  describe('Defaults props', () => {
    beforeEach(() => {
      sut();
    });

    it('should render radio', () => {
      expect(radioElement).toBeInTheDocument();
    });

    it('should render radio not checked by default', () => {
      expect(radioElement).not.toBeChecked();
    });

    it('should select a radio', async () => {
      await user.click(radioElement);
      expect(radioElement).toBeChecked();
    });
  });
});
