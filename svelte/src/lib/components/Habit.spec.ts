import { render, screen } from '@testing-library/svelte';
import Habit from './Habit.svelte';
import '@testing-library/jest-dom';

const props = {
  name: 'Drink water',
  icon: 'heart',
  selected: true,
}

const sut = () => {
  render(Habit, {
    props
  });
}

describe('Habit component', () => {
  beforeEach(() => {
    sut();
  });

  it('should render habit with correct name', () => {
    expect(screen.getByText(props.name)).toBeInTheDocument();
  });

  it('should render checkbox', () => {
    expect(screen.getByRole('checkbox')).toBeInTheDocument();
  });

  it('should render icon with correct class', () => {
    const icon = screen.getByTestId('icon-habit');
    expect(icon).toHaveClass(`fa-${props.icon}`)
    expect(icon).toBeInTheDocument();
  });

  it('should render habit selected', () => {
    expect(screen.getByRole('checkbox')).toBeChecked();
  });
});
