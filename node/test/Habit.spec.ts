import { Habit } from "../src/domain/entity/Habit";

describe('Habit', () => {
  it('should have a name', () => {
    const habitName = 'drink water'
    let newHabit = new Habit(habitName)
    expect(newHabit.name).toBe(habitName)
  });
});
