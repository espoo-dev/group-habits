module Api
  module V1
    class CategoriesController < Api::V1::ApiController
      after_action :verify_policy_scoped, only: []
      after_action :verify_authorized, only: []

      def index
        categories = CategoriesFinderService.new(user: current_user).call
        render json: CategoryPresenter.payload_for_list(categories)
      end

      def create
        category = CategoryCreatorService.new(user: current_user, create_category_params:).call
        render json: CategoryPresenter.payload_for_item(category), status: :created
      end

      private

      def create_category_params
        params.permit(:name)
      end
    end
  end
end
