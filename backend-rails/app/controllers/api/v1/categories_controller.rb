module Api
  module V1
    class CategoriesController < Api::V1::ApiController
      after_action :verify_policy_scoped, only: []
      after_action :verify_authorized, only: []

      def index
        categories = CategoriesFinderService.new(user: current_user).call
        render json: CategoryPresenter.payload_for_list(categories)
      end
    end
  end
end
