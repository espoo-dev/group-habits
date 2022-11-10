import { DailyHabit } from "../src/domain/entity/DailyHabit";
import { Habit } from "../src/domain/entity/Habit";

describe('Habit', () => {
  let newHabit!: Habit
  let nameHabit!: string
  let dailyHabit!: DailyHabit
  let date = new Date()	

  beforeEach(() => {
    newHabit = new Habit(nameHabit)
    dailyHabit = new DailyHabit(date, newHabit)
  })

  it('should have a name', () => {
    expect(newHabit.name).toBe(nameHabit)
  });

  it('should have daily habit', () => {
    newHabit.addDailyHabit(dailyHabit)
    expect(newHabit.dailiesHabits).toContain(dailyHabit)
  })
});
