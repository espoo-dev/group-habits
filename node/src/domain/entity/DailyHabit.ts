export class DailyHabit{
  public date!: Date
  public checked: boolean = false

  constructor(date: Date) {
    this.date = date
  }

  toggleCheck() {
    this.checked = !this.checked
  }
}
