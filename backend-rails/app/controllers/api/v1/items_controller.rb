module Api
  module V1
    class ItemsController < Api::V1::ApiController
      def index
        items = ItemsFinderService.new(user: current_user, index_params:).call
        render json: ItemPresenter.payload_for_list(items)
      end

      private

      def index_params
        params.permit(:name, :item_type)
      end
    end
  end
end
