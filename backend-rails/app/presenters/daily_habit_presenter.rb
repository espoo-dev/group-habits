class DailyHabitPresenter
  attr_accessor :daily_habit

  def initialize(daily_habit:)
    @daily_habit = daily_habit

    super()
  end

  def payload
    {
      id: daily_habit.id,
      date: daily_habit.date,
      check: daily_habit.check,
      habit_name: daily_habit.habit.name,
      habit_icon: daily_habit.habit.icon
    }
  end
end
