class DailyHabitCreatorService
  attr_reader :user, :habit_id, :check

  def initialize(user:, create_daily_habit_params:)
    @user = user
    @habit_id = create_daily_habit_params[:habit_id]
    @check = create_daily_habit_params[:check]
  end

  def call
    habit = Habit.find(habit_id)
    daily_habit = DailyHabit.build_daily_habit_from_habit(habit:, user:, check:)

    authorize!(user:, daily_habit:)

    daily_habit.save!
    daily_habit
  end

  private

  def authorize!(user:, daily_habit:)
    raise Pundit::NotAuthorizedError unless DailyHabitPolicy.new(user, daily_habit).create?
  end
end
