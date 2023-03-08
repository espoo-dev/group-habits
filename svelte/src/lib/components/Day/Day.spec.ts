import { act, render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
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

const user = userEvent.setup();

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
    await user.click(day);
    expect(day).toHaveClass('selected');
  });
});
