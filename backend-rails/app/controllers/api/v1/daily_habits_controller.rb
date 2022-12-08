module Api
  module V1
    class DailyHabitsController < Api::V1::ApiController
      after_action :verify_policy_scoped, only: []
      after_action :verify_authorized, only: []

      def index
        daily_habits = DailyHabitsFinderService.new(user: current_user).call
        render json: format_daily_habits(daily_habits)
      end

      def create
        daily_habit = DailyHabitCreatorService.new(user: current_user,
                                                   create_daily_habit_params:).call
        render json: format_daily_habit(daily_habit), status: :created
      end

      private

      def create_daily_habit_params
        params.permit(:habit_id, :check)
      end

      def format_daily_habits(daily_habits)
        daily_habits.map { format_daily_habit(_1) }
      end

      def format_daily_habit(daily_habit)
        DailyHabitPresenter.new(daily_habit).payload
      end
    end
  end
end
