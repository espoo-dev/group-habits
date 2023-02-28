import { render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import Select from './Select.svelte';

const props = {
  label: 'Selecione um veículo',
  value: 'nome',
  options: [
    {name: 'Carros', value: 'carros'},
    {name: 'Motos', value: 'motos'},
  ]
};

let selectElement: HTMLElement;
const user = userEvent.setup();

const sut = () => {
  const { getByTestId } = render(Select, {
    props,
  });
  selectElement = getByTestId(`${props.label}-select-id`);
};

describe('Select component', () => {
  describe('Defaults props', () => {
    beforeEach(() => {
      sut();
    });

    it('should render select', () => {
      expect(selectElement).toBeInTheDocument();
    });

    it('should render label', () => {
      expect(screen.getByText(props.label)).toBeInTheDocument();
    });

    it.each(props.options)('should render %j option', (option) => {
      expect(screen.getByText(option.name)).toBeInTheDocument();
      expect(screen.getByText(option.name)).toHaveAttribute('value', option.value);
    });

    it('should select a option', async () => {
      await user.selectOptions(selectElement, props.options[0].name);
      // screen.debug();
      // expect(sele)
    });
  });
});
