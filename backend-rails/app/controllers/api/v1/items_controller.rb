module Api
  module V1
    class ItemsController < Api::V1::ApiController
      def index
        items = Items::ItemsFinderService.new(user: current_user, params: index_params).call
        render json: ItemPresenter.payload_for_list(items)
      end

      def create
        item = Items::ItemCreatorService.new(user: current_user, params: create_item_params).call
        render json: ItemPresenter.payload_for_item(item), status: :created
      end

      def update
        item = Items::ItemUpdaterService.new(user: current_user, params: update_item_params).call
        render json: ItemPresenter.payload_for_item(item), status: :ok
      end

      def destroy
        Items::ItemDestroyerService.new(user: current_user, params: destroy_params).call
        render json: {}, status: :no_content
      end

      private

      def index_params
        params.permit(:name, :item_type)
      end

      def create_item_params
        params.permit(:name, :extra_info, :sale_price, :purchase_price, :item_type, :category_id, :sales_unit_id)
      end

      def update_item_params
        params.permit(:id, :name, :extra_info, :sale_price, :purchase_price, :item_type, :category_id, :sales_unit_id)
      end
    end
  end
end
