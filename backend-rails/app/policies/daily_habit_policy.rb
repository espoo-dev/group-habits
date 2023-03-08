class DailyHabitPolicy < ApplicationPolicy
  def index?
    true
  end

  def create?
    record.user == user
  end

  class Scope < ApplicationPolicy::Scope
    def resolve
      DailyHabit
        .by_today
        .where(user:)
        .includes([:habit])
    end
  end
end
