import { act, fireEvent, render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import Day from './Day.svelte';

const props = {
  dayName: 'Sab',
  dayNumber: 4,
};

const sut = () => {
  render(Day, {
    props,
  });
};

describe('Day component', () => {
  beforeEach(() => {
    sut();
  });

  it('should render day with correct day name', () => {
    expect(screen.getByText(props.dayName)).toBeInTheDocument();
  });

  it('should render day with correct day number', () => {
    expect(screen.getByText(props.dayNumber)).toBeInTheDocument();
  });

  it('should change style when select the day', async () => {
    const day = screen.getByTestId(`day-${props.dayNumber}`);
    await act(() => {
      fireEvent.click(day);
    });
    expect(day).toHaveClass('selected');
  });
});
