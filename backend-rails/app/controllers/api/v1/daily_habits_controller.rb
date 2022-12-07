module Api
  module V1
    class DailyHabitsController < Api::V1::ApiController
      after_action :verify_policy_scoped, only: []

      def index
        daily_habits = DailyHabitsFinderService.new(user: current_user).call
        render json: formatted_daily_habits(daily_habits)
      end

      private

      def formatted_daily_habits(daily_habits)
        daily_habits.map { DailyHabitPresenter.new(_1) }.map(&:payload)
      end
    end
  end
end
