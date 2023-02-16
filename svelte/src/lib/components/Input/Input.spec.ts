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
