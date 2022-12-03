class DailyHabitPresenter
  attr_accessor :daily_habit

  def initialize(daily_habit:)
    @daily_habit = daily_habit

    super()
  end

  def payload
    habit = daily_habit.habit
    {
      id: daily_habit.id,
      date: daily_habit.date,
      check: daily_habit.check,
      habit_icon: habit.icon,
      habit_name: habit.name
    }
  end
end
