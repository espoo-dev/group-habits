import { render, screen } from '@testing-library/svelte';
import Habit from './Habit.svelte';
import '@testing-library/jest-dom';

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