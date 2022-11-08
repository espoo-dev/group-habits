export class DailyHabit{
  public day!: number
  public checked: boolean = false
constructor(day:number, checked:boolean){
  this.day = day;
  this.checked = checked
}
}
