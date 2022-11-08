import { DailyHabit } from "../src/domain/entity/DailyHabit"

describe('daily Habit', () => {
  let newDailyHabit!: DailyHabit
  beforeEach(() => {
    newDailyHabit = new DailyHabit(new Date().getDate(), true)
  })
  it('should have day', () => {
    expect(newDailyHabit.day).toBe(new Date().getDate())
  })
  it('should checked be true', () => {
    expect(newDailyHabit.checked).toBe(true)
  })
})
