import { render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import Input from './Input.svelte';

const props = {
  label: 'Nome',
};

let inputElement: HTMLElement;
const user = userEvent.setup();

const sut = () => {
  const { getByRole } = render(Input, {
    props,
  });
  inputElement = getByRole('textbox');
};

describe('Input component', () => {
  describe('Defaults props', () => {
    beforeEach(() => {
      sut();
    });

    it('should render input', () => {
      expect(inputElement).toBeInTheDocument();
    });

    it('should render label', () => {
      expect(screen.getByText(props.label)).toBeInTheDocument();
    });

    it('should render label', () => {
      expect(screen.getByText(props.label)).toBeInTheDocument();
    });

    it('should change the input value', async () => {
      const newValue = 'Linkin Park';
      await user.type(inputElement, newValue);
      expect(inputElement).toHaveValue(newValue);
    });
  });

  describe.only('Check Types', () => {
    it.each(['text', 'number'])(
      'should render input with %s',
      (type: string) => {
        render(Input, {
          ...props,
          type,
        });
        expect(screen.getByTestId(`input-${props.label}`)).toHaveAttribute(
          'type',
          type
        );
      }
    );
  });
});
