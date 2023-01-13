module Api
  module V1
    class CategoriesController < Api::V1::ApiController
      after_action :verify_policy_scoped, only: []
      after_action :verify_authorized, only: []

      def index
        categories = CategoriesFinderService.new(user: current_user).call
        render json: format_categories(categories)
      end

      private

      def format_categories(categories)
        categories.map { format_category(_1) }
      end

      def format_category(daily_habit)
        CategoryPresenter.new(daily_habit).payload
      end
    end
  end
end
