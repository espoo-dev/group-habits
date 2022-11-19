import { act, fireEvent, render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import Checkbox from './Checkbox.svelte';

const props = {
  name: 'Drink water',
  selected: false,
};

let checkboxElement: HTMLElement;
let checkboxComponent: Checkbox;

const sut = () => {
  const { getByRole, component } = render(Checkbox, {
    props,
  });
  checkboxComponent = component;
  checkboxElement = getByRole('checkbox');
};

describe('Checkbox component', () => {
  beforeEach(() => {
    sut();
  });

  it('should render checkbox', () => {
    expect(checkboxElement).toBeInTheDocument();
  });

  it('should render checkbox unchecked', () => {
    expect(checkboxElement).not.toBeChecked();
  });

  it('should be checked when clicked', () => {
    fireEvent.click(checkboxElement);
    expect(checkboxElement).toBeChecked();
  });

  it('should have selected class when is checked', async () => {
    await act(() => {
      fireEvent.click(checkboxElement);
    });
    expect(checkboxElement).toHaveClass('selected');
  });

  it('should call event when select checkbox', async () => {
    let mockEvent = jest.fn();

    checkboxComponent.$on('change_selected', function (event) {
      mockEvent(event.detail);
    });
    await act(() => {
      fireEvent.click(checkboxElement);
    });

    expect(mockEvent).toHaveBeenLastCalledWith({
      selected: true,
    });
  });
});
