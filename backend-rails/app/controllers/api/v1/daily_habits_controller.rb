module Api
  module V1
    class DailyHabitsController < Api::V1::ApiController
      def index
        existing_daily_habits = policy_scope(DailyHabit)
        authorize existing_daily_habits
        daily_habits = existing_daily_habits + current_user.group.missing_daily_habits(
          user: current_user, existing_daily_habits:
        )
        render json: formatted_daily_habits(daily_habits)
      end

      private

      def formatted_daily_habits(daily_habits)
        daily_habits.map { DailyHabitPresenter.new(_1) }.map(&:payload)
      end
    end
  end
end
