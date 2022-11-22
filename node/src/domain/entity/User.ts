import { DailyHabit } from "./DailyHabit"

export class User {
    public email!: string
    public password!: string
    public dailiesHabits:DailyHabit[] = []
    constructor(email: string, password: string){
        this.email = email
        this.password = password
    }

    addDailyHabit(dailyHabit: DailyHabit){
        this.dailiesHabits.push(dailyHabit)
        dailyHabit.user = this
    }
}
