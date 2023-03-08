import { render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import Select from './Select.svelte';

const propsDefault = {
  label: 'Selecione um veículo',
  value: 'nome',
  valueKey: 'value',
  options: [
    { name: 'Carros', value: 'carros' },
    { name: 'Motos', value: 'motos' },
  ],
};

let selectElement: HTMLElement;
const user = userEvent.setup();

const myMockFn = jest.fn((cb) => cb(null, true));

const sut = (props = propsDefault) => {
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
      expect(screen.getByText(propsDefault.label)).toBeInTheDocument();
    });

    it.each(propsDefault.options)('should render %j option', (option) => {
      expect(screen.getByText(option.name)).toBeInTheDocument();
      expect(screen.getByText(option.name)).toHaveAttribute(
        'value',
        option.value
      );
    });

    it('should select a option', async () => {
      const optionToSelect = propsDefault.options[0].name;
      await user.selectOptions(selectElement, optionToSelect);
      expect(
        screen.getByRole('option', { name: optionToSelect })
      ).toBeInTheDocument();
    });
  });

  describe('Load by factory', () => {
    it('should load data by factory', () => {
      const propsWithFactory = {
        ...propsDefault,
        service: myMockFn(() => {
          return {
            factory: {
              list: async () => {
                return propsDefault.options;
              },
            },
          };
        }),
      };

      sut(propsWithFactory);
      expect(myMockFn).toHaveBeenCalled();
    });
  });
});
