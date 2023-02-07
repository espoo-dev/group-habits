import { render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import Modal from './Modal.svelte';

interface ModalProps {
  title: string,
  cancelText?: string
  confirmText?: string
};

const defaultProps = {
  title: 'New Modal'
}
const cancelText = 'Cancelar';
const confirmText = 'Confirmar';

let modalComponent: Modal;
const user = userEvent.setup();

const sut = (props?: ModalProps) => {
  const { component } = render(Modal, {
    props: props || defaultProps
  });
  modalComponent = component;
};

describe('Modal Component', () => {
  describe('Default', () => {
    beforeEach(() => {
      sut();
    })
  
    it('should render title', () => {
      expect(screen.getByText(defaultProps.title)).toBeInTheDocument()
    });
  
    it('should render close button', () => {
      expect(screen.getByText(cancelText)).toBeInTheDocument()
    });
  
    it('should render close button', () => {
      expect(screen.getByText(confirmText)).toBeInTheDocument()
    });
  
    it('should call close action when click in close button', async () => {
      const mock = jest.fn();
      modalComponent.$on('close', mock);
      await user.click(screen.getByText(cancelText));
      expect(mock).toHaveBeenCalled();
    });
  
    it('should call confirm action when click in confirm button', async () => {
      const mock = jest.fn();
      modalComponent.$on('confirm', mock);
      await user.click(screen.getByText(confirmText));
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
