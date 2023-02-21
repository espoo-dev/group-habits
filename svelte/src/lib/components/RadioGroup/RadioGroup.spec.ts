import { render, fireEvent, screen } from '@testing-library/svelte';
import RadioGroup from './RadioGroup.svelte';
import userEvent from '@testing-library/user-event';
import '@testing-library/jest-dom';

const options = [
  { label: 'Option 1', value: 'option1' },
  { label: 'Option 2', value: 'option2' },
  { label: 'Option 3', value: 'option3' },
];
const props = {
  options,
};
const user = userEvent.setup();

let radioGroupComponent: RadioGroup;

const sut = () => {
  const { component } = render(RadioGroup, {
    props,
  });
  radioGroupComponent = component;
};

describe('RadioGroup', () => {
  beforeEach(() => {
    sut();
  });

  it('should render all options correctly', () => {
    const radiosElements = screen.queryAllByRole('radio');
    expect(radiosElements).toHaveLength(options.length);
  });

  it.each(options)('should render $label option correctly', ({ label }) => {
    expect(screen.getByText(label)).toBeInTheDocument();
  });
});
