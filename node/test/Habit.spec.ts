import { DailyHabit } from "../src/domain/entity/DailyHabit";
import { Habit } from "../src/domain/entity/Habit";

describe('Habit', () => {
  let newHabit!: Habit
  let nameHabit!: string
  let dailyHabit!: DailyHabit
  let date = new Date()	

  beforeEach(() => {
    newHabit = new Habit(nameHabit)
    dailyHabit = new DailyHabit(date)
  })

  it('should have a name', () => {
    expect(newHabit.name).toBe(nameHabit)
  })

  it('should contain the new daily habit added in habit', () => {
    newHabit.addDailyHabit(dailyHabit)
    expect(newHabit.dailiesHabits).toContain(dailyHabit)
  })

  it('should daily habit has a habit', () => {
    newHabit.addDailyHabit(dailyHabit)
    expect(dailyHabit.habit).toBe(newHabit)
  })
})
