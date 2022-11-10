import { Habit } from "./Habit"

export class DailyHabit{
  public date!: Date
  public checked: boolean = false
  public habit!: Habit

  constructor(date: Date, habit: Habit) {
    this.date = date
    this.habit = habit
  }

  toggleCheck() {
    this.checked = !this.checked
  }
}
