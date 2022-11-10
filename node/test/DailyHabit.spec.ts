import { DailyHabit } from "../src/domain/entity/DailyHabit"
import { Habit } from "../src/domain/entity/Habit"

describe('daily Habit', () => {
  let newDailyHabit!: DailyHabit
  let date = new Date()
  const habitName = 'drink water'
  let habit = new Habit(habitName)

  beforeEach(() => {
    newDailyHabit = new DailyHabit(date, habit)
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

  it('should have a habit parent', () => {
    expect(newDailyHabit.habit).toBe(habit)
  });

  it('should have the name of habit parent', () => {
    expect(newDailyHabit.habit.name).toBe(habitName)
  });
})
