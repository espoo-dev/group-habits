class DailyHabitPolicy < ApplicationPolicy
  def index?
    true
  end

  class Scope
    attr_reader :user, :scope

    def initialize(user, scope)
      @user = user
      @scope = scope
    end

    def resolve
      DailyHabit
        .by_today
        .where(user:)
        .includes([:habit])
    end
  end
end
