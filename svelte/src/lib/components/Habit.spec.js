import { render, screen } from '@testing-library/svelte';
import Habit from './Habit.svelte';

const sut = () => {
  render(Habit);
}

describe('Habit component', () => {
  beforeEach(() => {
    sut();
  });

  it('should render a habit', () => {
    expect(screen.getByText('Eat 2 fruits')).toBeInTheDocument();
  });
});