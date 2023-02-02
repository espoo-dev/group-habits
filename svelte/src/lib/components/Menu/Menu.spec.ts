import { render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import Menu from './Menu.svelte';

const props = {
  options: [
    {
      name: 'Início',
    },
    {
      name: 'Categorias',
    },
  ],
};

const sut = () => {
  render(Menu, {
    props,
  });
};

describe('Day component', () => {
  beforeEach(() => {
    sut();
  });

  it.each(props.options)('should render %j menu option', (option) => {
    expect(screen.getByText(option.name)).toBeInTheDocument();
  });
});
