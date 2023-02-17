module Api
  module V1
    class CategoriesController < Api::V1::ApiController
      def index
        categories = Categories::CategoriesFinderService.new(user: current_user, params: find_params).call
        render json: CategoryPresenter.payload_for_list(categories)
      end

      def create
        category = Categories::CategoryCreatorService.new(user: current_user, create_category_params:).call
        render json: CategoryPresenter.payload_for_item(category), status: :created
      end

      def update
        category = Categories::CategoryUpdaterService.new(user: current_user, update_category_params:).call
        render json: CategoryPresenter.payload_for_item(category), status: :ok
      end

      def destroy
        Categories::CategoryDestroyerService.new(user: current_user, destroy_params:).call
        render json: {}, status: :no_content
      end

      private

      def find_params
        params.permit(:name)
      end

      def create_category_params
        params.permit(:name)
      end

      def update_category_params
        params.permit(:id, :name)
      end
    end
  end
end
