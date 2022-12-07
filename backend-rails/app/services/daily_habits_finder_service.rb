class DailyHabitsFinderService
  attr_reader :user

  def initialize(user:)
    @user = user
  end

  def call
    existing_daily_habits = DailyHabitPolicy::Scope.new(user, DailyHabit).resolve

    authorize!(user:, existing_daily_habits:)

    daily_habits_for_user(existing_daily_habits:, user:)
  end

  private

  def authorize!(user:, existing_daily_habits:)
    raise Pundit::NotAuthorizedError unless DailyHabitPolicy.new(user, existing_daily_habits).index?
  end

  def daily_habits_for_user(existing_daily_habits:, user:)
    missing_daily_habits = user.group.missing_daily_habits(user:, existing_daily_habits:)
    existing_daily_habits + missing_daily_habits
  end
end
