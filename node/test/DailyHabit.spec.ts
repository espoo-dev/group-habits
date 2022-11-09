import { DailyHabit } from "../src/domain/entity/DailyHabit"

describe('daily Habit', () => {
  let newDailyHabit!: DailyHabit
  beforeEach(() => {
    newDailyHabit = new DailyHabit(new Date())
  })

  it('should have day', () => {
    expect(newDailyHabit.date.getDate()).toBe(new Date().getDate())
    expect(newDailyHabit.date.getMonth()).toBe(new Date().getMonth())
    expect(newDailyHabit.date.getFullYear()).toBe(new Date().getFullYear())
  })

  it('should habit start with check false', () => {
    expect(newDailyHabit.checked).toBe(false)
  })

  it('should check the habit', () => {
    newDailyHabit.toggleCheck()
    expect(newDailyHabit.checked).toBe(true)
  } )

  it('should be check false', () => {
    newDailyHabit.toggleCheck()
    newDailyHabit.toggleCheck()
    expect(newDailyHabit.checked).toBe(false)
  })
})
