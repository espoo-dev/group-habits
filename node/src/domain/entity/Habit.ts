import { DailyHabit } from "./DailyHabit"

export class Habit {
  public name!: string
  public dailiesHabits: DailyHabit[] = []

  constructor(name: string) {
    this.name = name
  }

  addDailyHabit(dailyHabit: DailyHabit) {
    this.dailiesHabits.push(dailyHabit)
    dailyHabit.habit = this
  }
}
