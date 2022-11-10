import { DailyHabit } from "../src/domain/entity/DailyHabit"

describe('daily Habit', () => {
  let newDailyHabit!: DailyHabit
  let date = new Date()

  beforeEach(() => {
    newDailyHabit = new DailyHabit(date)
  })

  it('should have a date', () => {
    expect(newDailyHabit.date).toBe(date)
    expect(newDailyHabit.date).toBeInstanceOf(Date)
  })

  it('should habit start with check false', () => {
    expect(newDailyHabit.checked).toBe(false)
  })

  it('should have checked equals to true when toggle once', () => {
    newDailyHabit.toggleCheck()
    expect(newDailyHabit.checked).toBe(true)
  } )

  it('should have checked equals to false when toggle twice', () => {
    newDailyHabit.toggleCheck()
    newDailyHabit.toggleCheck()
    expect(newDailyHabit.checked).toBe(false)
  })
})
