import { render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import Menu from './Menu.svelte';

const props = {
  options: [
    {
      name: 'Início',
      link: '/home',
    },
    {
      name: 'Categorias',
      link: '/categories',
    },
  ],
};

const sut = () => {
  render(Menu, {
    props,
  });
};

describe('Menu component', () => {
  describe('Default props', () => {
    beforeEach(() => {
      sut();
    });

    it.each(props.options)('should render %j menu option', (option) => {
      expect(screen.getByText(option.name)).toBeInTheDocument();
    });

    it('should render user profile', () => {
      expect(screen.getByTestId('menu-user-profile')).toBeInTheDocument();
    });

    it('should render hamburguer menu', () => {
      expect(screen.queryAllByTestId('menu-hamburguer')).toHaveLength(1);
    });
  });
});
