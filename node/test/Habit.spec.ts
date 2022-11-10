import { Habit } from "../src/domain/entity/Habit";

describe('Habit', () => {
  it('should have a name', () => {
    let newHabit = new Habit('drink water')
    expect(newHabit.name).toBe('drink water')
  });
});