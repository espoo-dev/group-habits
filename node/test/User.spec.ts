import { DailyHabit } from "../src/domain/entity/DailyHabit"
import { User } from "../src/domain/entity/User"

describe('User', () => {
    let newUser: User
    let email = "chris@gmail.com"
    let password = "123456"
    let dailyHabit!: DailyHabit
    let date = new Date()

    beforeEach(() => {
      newUser = new User(email, password)
      dailyHabit = new DailyHabit(date)
    })

    it('should have a user with correct e-mail', () => {
      expect(newUser.email).toBe(email)
    })

    it('should have a user with correct password', () => {
      expect(newUser.password).toBe(password)
    })
      
    it('should contain the new daily habit added in User', () => {
      newUser.addDailyHabit(dailyHabit)
      expect(newUser.dailiesHabits).toContain(dailyHabit)
    })
    
    it('should daily habit has a user', () => {
      newUser.addDailyHabit(dailyHabit)
      expect(dailyHabit.user).toBe(newUser)
    })
})
