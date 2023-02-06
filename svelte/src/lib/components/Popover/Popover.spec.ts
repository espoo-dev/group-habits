import { act, fireEvent, render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import Popover from './Popover.svelte';

interface PopoverProps {
  message: string,
  cancelText?: string
  confirmText?: string
};

const defaultProps = {
  message: 'New Popover'
}
const cancelText = 'Não, cancelar';
const confirmText = 'Sim, prosseguir';

let popoverComponent: Popover;

const sut = (props?: PopoverProps) => {
  const { component } = render(Popover, {
    props: props || defaultProps
  });
  popoverComponent = component;
};

describe('Modal Component', () => {
  describe('Default', () => {
    beforeEach(() => {
      sut();
    })
  
    it('should render message', () => {
      expect(screen.getByText(defaultProps.message)).toBeInTheDocument()
    });
  
    it('should render cancel text button', () => {
      expect(screen.getByText(cancelText)).toBeInTheDocument()
    });
  
    it('should render confirm text button', () => {
      expect(screen.getByText(confirmText)).toBeInTheDocument()
    });
  
    it('should call close action when click in close button', async () => {
      const mock = jest.fn();
      popoverComponent.$on('close', mock);
      await act(() => {
        fireEvent.click(screen.getByText(cancelText));
      });
      expect(mock).toHaveBeenCalled();
    });
  
    it('should call confirm action when click in confirm button', async () => {
      const mock = jest.fn();
      popoverComponent.$on('confirm', mock);
      await act(() => {
        fireEvent.click(screen.getByText(confirmText));
      });
      expect(mock).toHaveBeenCalled();
    });
  });
  
  describe('Custom fields', () => {
    it('should render custom cancel button text', () => {
      const cancelText = 'Fechar'
      sut({
        ...defaultProps,
        cancelText
      });

      expect(screen.getByText(cancelText)).toBeInTheDocument()
    });

    it('should render custom confirm button text', () => {
      const confirmText = 'Concluir'
      sut({
        ...defaultProps,
        confirmText
      });

      expect(screen.getByText(cancelText)).toBeInTheDocument()
    });
  });
});
