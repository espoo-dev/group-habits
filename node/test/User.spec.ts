import { DailyHabit } from "../src/domain/entity/DailyHabit"
import { User } from "../src/domain/entity/User"

describe('User', () => {
    let newUser: User
    let email = "chris@gmail.com"
    let password = "123456"
    let dailyHabit1!: DailyHabit
    let date = new Date()

    beforeEach(() => {
      newUser = new User(email, password)
      dailyHabit1 = new DailyHabit(date)
      })

    it('should have a e-mail', () => {
      expect(newUser.email).toBe(email)
      })

    it('should have a password', () => {
      expect(newUser.password).toBe(password)
      })
    it('should contain the new daily habit added in User', () => {
      newUser.addDailyHabit(dailyHabit1)
      expect(newUser.dailiesHabits).toContain(dailyHabit1)
    })
})
