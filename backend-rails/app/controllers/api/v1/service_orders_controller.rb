module Api
  module V1
    class ServiceOrdersController < Api::V1::ApiController
      def index
        service_orders = ServiceOrders::ServiceOrdersFinderService.new(user: current_user, params: index_params).call
        render json: ServiceOrderPresenter.payload_for_list(service_orders)
      end

      def create
        service_order = ServiceOrders::ServiceOrderCreatorService.new(user: current_user,
                                                                      params: create_service_order_params).call
        render json: ServiceOrderPresenter.payload_for_item(service_order), status: :created
      end

      def update
        service_order = ServiceOrders::ServiceOrderUpdaterService.new(user: current_user,
                                                                      params: update_service_order_params).call
        render json: ServiceOrderPresenter.payload_for_item(service_order), status: :ok
      end

      def destroy
        ServiceOrders::ServiceOrderDestroyerService.new(user: current_user, params: destroy_params).call
        render json: {}, status: :no_content
      end

      private

      def index_params
        params.permit(:name)
      end

      def create_service_order_params
        params.permit(:extra_info, :status, :creation_date, :conclusion_date, :discount, :customer_id,
                      items_ids: [])
      end

      def update_service_order_params
        params.permit(:id, :extra_info, :status, :creation_date, :conclusion_date, :discount, :customer_id,
                      items_ids: [])
      end
    end
  end
end
