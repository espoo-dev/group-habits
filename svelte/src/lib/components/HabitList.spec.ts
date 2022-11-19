import { render, screen } from '@testing-library/svelte';
import '@testing-library/jest-dom';
import HabitList from './HabitList.svelte';

const habits = [
	{
		name: 'Drink water',
		icon: 'heart',
		selected: true,
	},
	{
		name: 'Go to the gym',
		icon: 'heart',
		selected: true,
	}
];

const props = {
  habits,
}

const sut = () => {
  render(HabitList, {
    props
  });
};

describe('Habit component', () => {
  beforeEach(() => {
    sut();
  });

  it.each(habits)('should render habit with name $name', ({ name }) => {
    expect(screen.getByText(name)).toBeInTheDocument();
  });
});
